package com.tarik.tvmaze.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tarik.tvmaze.R;
import com.tarik.tvmaze.data.local.AppDatabase;
import com.tarik.tvmaze.data.local.dao.ShowDao;
import com.tarik.tvmaze.databinding.FragmentShowsBinding;
import com.tarik.tvmaze.domain.model.Show;
import com.tarik.tvmaze.presentation.discover.ShowAdapter;

import java.util.List;
import java.util.Objects;

public class ShowsFragment extends Fragment implements ShowAdapter.ShowClickListener {

    private FragmentShowsBinding binding;
    private ShowAdapter adapter = new ShowAdapter(this);

    private AppDatabase appDatabase = null;
    private ShowDao showDao = null;

    public ShowsFragment() {
        // Required empty public constructor
    }

    public static ShowsFragment newInstance() {
        ShowsFragment fragment = new ShowsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appDatabase = AppDatabase.getDatabase(requireActivity().getApplicationContext());
        showDao = appDatabase.showDao();

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentShowsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        binding.recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (showDao != null) {
            List<Show> showList = showDao.getAllShows();
            if (showList.size() > 0) {
                adapter.setShowList(showList);
                binding.recyclerView.setVisibility(View.VISIBLE);
                binding.emptyListTextView.setVisibility(View.GONE);
            } else {
                binding.recyclerView.setVisibility(View.GONE);
                binding.emptyListTextView.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(Show show) {
        Intent intent = new Intent(requireContext(), SingleShowActivity.class);
        intent.putExtra(SingleShowFragment.ARG_SHOW_ID, show.showId);
        startActivity(intent);
    }
}