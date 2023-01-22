package com.tarik.tvmaze.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tarik.tvmaze.R;
import com.tarik.tvmaze.data.local.AppDatabase;
import com.tarik.tvmaze.data.local.dao.ShowDao;
import com.tarik.tvmaze.data.remote.RetrofitProvider;
import com.tarik.tvmaze.data.remote.RetrofitService;
import com.tarik.tvmaze.data.remote.dto.ShowDto;
import com.tarik.tvmaze.databinding.FragmentSingleShowBinding;
import com.tarik.tvmaze.domain.model.Show;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleShowFragment extends Fragment {

    private static final String TAG = "SingleShowFragment";
    public static final String ARG_SHOW_ID = "ARG_SHOW_ID";

    private FragmentSingleShowBinding binding;
    private AppDatabase appDatabase = null;
    ShowDao showDao = null;
    private long showId;

    private Callback<ShowDto> callback = new Callback<ShowDto>() {
        @Override
        public void onResponse(@NonNull Call<ShowDto> call, @NonNull Response<ShowDto> response) {
            if (response.isSuccessful()) {
                ShowDto showDto = response.body();
                if (showDto != null) {
                    Log.d(TAG, "onResponse: " + showDto);
                    Show show = showDto.toShow();
                    renderShow(show);
                }
            }
        }

        @Override
        public void onFailure(@NonNull Call<ShowDto> call, @NonNull Throwable t) {
            Toast.makeText(requireContext(), "Cannot obtain show", Toast.LENGTH_SHORT).show();
        }
    };

    public SingleShowFragment() {
        // Required empty public constructor
    }

    public static SingleShowFragment newInstance(long showId) {
        SingleShowFragment fragment = new SingleShowFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_SHOW_ID, showId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            showId = getArguments().getLong(ARG_SHOW_ID);
            if (showId == -1) {
                Toast.makeText(requireContext(), "Show not found", Toast.LENGTH_SHORT).show();
                requireActivity().finish();
            }
        }
        appDatabase = AppDatabase.getDatabase(requireActivity().getApplicationContext());
        showDao = appDatabase.showDao();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSingleShowBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RetrofitService service = RetrofitProvider.getRetrofit().create(RetrofitService.class);
        service.getShowById(showId).enqueue(callback);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void renderShow(Show show) {
        if (show.originalPosterUrl != null) {
            Glide.with(binding.getRoot()).load(show.originalPosterUrl).into(binding.showImageImageView);
        }
        binding.nameTextView.setText(show.showName);
        binding.ratingBar.setRating((float)show.showRating/2f);
        binding.premieredTextView.setText("Premiered: " + show.showPremiered);
        if (show.showSite != null && !show.showSite.isEmpty()) {
            binding.officialSiteTextView.setText("official site: " + Html.fromHtml(show.showSite));
        }
        binding.summaryTextArea.setText(Html.fromHtml(show.showSummary));

        // Make it possible to scroll through the text view
        binding.summaryTextArea.setMovementMethod(new ScrollingMovementMethod());

        renderFloatingActionButton(show);
    }

    private void renderFloatingActionButton(Show show) {
        if (showDao != null) {
            boolean isAdded = showDao.isShowAlreadySaved(show.showId) == show.showId;
            if (!isAdded) {
                binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_add);
                binding.floatingActionButton.setOnClickListener(v -> onAddShowClick(v, show));
            } else {
                binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_remove);
                binding.floatingActionButton.setOnClickListener(v -> onRemoveShowClick(v, show));
            }
        }
    }

    private void onAddShowClick(View view, Show show) {
        if (showDao != null) {
            showDao.insertShow(show);
            renderFloatingActionButton(show);
        }
    }

    private void onRemoveShowClick(View view, Show show) {
        if (showDao != null) {
            showDao.deleteShow(show);
            renderShow(show);
        }
    }

}