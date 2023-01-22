package com.tarik.tvmaze.presentation.discover;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tarik.tvmaze.databinding.ItemShowBinding;
import com.tarik.tvmaze.domain.model.Show;

import java.util.ArrayList;
import java.util.List;

public class ShowAdapter extends RecyclerView.Adapter<ShowViewHolder> {

    public interface ShowClickListener {
        void onClick(Show show);
    }

    List<Show> showList = new ArrayList<>();
    ShowClickListener listener = null;

    public ShowAdapter(@NonNull ShowClickListener listener) {
        this.listener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setShowList(List<Show> showList) {
        this.showList = showList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemShowBinding binding = ItemShowBinding.inflate(inflater, parent, false);
        return new ShowViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowViewHolder holder, int position) {
        holder.onBind(showList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }
}
