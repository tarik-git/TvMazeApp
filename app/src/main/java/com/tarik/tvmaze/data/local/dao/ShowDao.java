package com.tarik.tvmaze.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.tarik.tvmaze.domain.model.Show;

import java.util.List;

@Dao
public interface ShowDao {

    @Insert
    public void insertShow(Show show);

    @Query("SELECT show_id FROM show_table WHERE show_id = :showId LIMIT 1")
    public long isShowAlreadySaved(long showId);

    @Query("SELECT * FROM show_table")
    public List<Show> getAllShows();

    @Delete
    public void deleteShow(Show show);

}
