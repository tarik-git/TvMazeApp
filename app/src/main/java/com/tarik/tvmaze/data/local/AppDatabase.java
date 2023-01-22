package com.tarik.tvmaze.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tarik.tvmaze.data.local.dao.ShowDao;
import com.tarik.tvmaze.domain.model.Show;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Database(entities = {Show.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "show_database";
    private static AppDatabase instance;


    public abstract ShowDao showDao();


    public static AppDatabase getDatabase(Context applicationContext) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase.class,
                    AppDatabase.DATABASE_NAME
            ).allowMainThreadQueries().build();
        }
        return instance;
    }

}
