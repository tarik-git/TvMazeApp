package com.tarik.tvmaze.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.tarik.tvmaze.R;
import com.tarik.tvmaze.databinding.ActivitySingleShowBinding;

public class SingleShowActivity extends AppCompatActivity {

    private ActivitySingleShowBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySingleShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        long showId = -1;
        if (intent != null) {
            showId = intent.getLongExtra(SingleShowFragment.ARG_SHOW_ID, -1);
        }

        setupFragment(SingleShowFragment.newInstance(showId));
    }

    private void setupFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.single_show_frame_layout, fragment);
        fragmentTransaction.commit();
    }

}