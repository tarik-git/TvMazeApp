package com.tarik.tvmaze.presentation.discover;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.tarik.tvmaze.data.remote.RetrofitProvider;
import com.tarik.tvmaze.data.remote.RetrofitService;
import com.tarik.tvmaze.data.remote.dto.ShowResponse;
import com.tarik.tvmaze.databinding.FragmentDiscoverBinding;
import com.tarik.tvmaze.domain.model.Show;
import com.tarik.tvmaze.presentation.SingleShowFragment;
import com.tarik.tvmaze.presentation.SingleShowActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverFragment extends Fragment implements ShowAdapter.ShowClickListener {

    private static final String TAG = "DiscoverFragment";
    private FragmentDiscoverBinding binding;
    private ShowAdapter adapter = new ShowAdapter(this);

    private Callback<List<ShowResponse>> queryCallback = new Callback<List<ShowResponse>>() {
        @Override
        public void onResponse(
                @NonNull Call<List<ShowResponse>> call,
                @NonNull Response<List<ShowResponse>> response
        ) {
            Log.d(TAG, "onResponse: " + response);
            if (response.isSuccessful()) {
                List<ShowResponse> responseBody = response.body();
                if (responseBody != null) {
                    Log.d(TAG, "onResponse: " + responseBody);
                    List<Show> showList = new ArrayList<Show>();
                    for(ShowResponse showResponse : responseBody) {
                        showList.add(showResponse.getShow().toShow());
                    }
                    adapter.setShowList(showList);
                }
            }
        }

        @Override
        public void onFailure(
                @NonNull Call<List<ShowResponse>> call,
                @NonNull Throwable t
        ) {
            Log.d(TAG, "onFailure: " + t);
        }
    };

    public DiscoverFragment() {
        // Required empty public constructor
    }

    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentDiscoverBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.searchButton.setOnClickListener(this::onSearchButtonClick);

        LinearLayoutManager manager = new LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        );

        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onSearchButtonClick(View view) {
        String query = binding.queryEditText.getText().toString();

        RetrofitService service = RetrofitProvider.getRetrofit().create(RetrofitService.class);
        service.searchForShow(query).enqueue(queryCallback);
        hideSoftKeyboard();
    }

    public void hideSoftKeyboard() {
        // Check if no view has focus:
        View view = requireActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onClick(Show show) {
        Intent intent = new Intent(requireContext(), SingleShowActivity.class);
        intent.putExtra(SingleShowFragment.ARG_SHOW_ID, show.showId);
        startActivity(intent);
    }
}