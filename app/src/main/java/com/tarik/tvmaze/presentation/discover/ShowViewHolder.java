package com.tarik.tvmaze.presentation.discover;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tarik.tvmaze.databinding.ItemShowBinding;
import com.tarik.tvmaze.domain.model.Show;

public class ShowViewHolder extends RecyclerView.ViewHolder {

    private final ItemShowBinding binding;

    public ShowViewHolder(@NonNull ItemShowBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void onBind(Show show, ShowAdapter.ShowClickListener listener) {
        if (show == null) {
            return;
        }

        if (show.showName != null) {
            binding.showNameTextView.setText(show.showName);
        }

        if (show.posterUrl != null) {
            Glide.with(binding.getRoot()).load(show.posterUrl).into(binding.showPosterImageView);
        }

        if (listener != null) {
            binding.getRoot().setOnClickListener(v -> {
                listener.onClick(show);
            });
        }

    }

}
