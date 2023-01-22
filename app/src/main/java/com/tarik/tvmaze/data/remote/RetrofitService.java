package com.tarik.tvmaze.data.remote;

import com.tarik.tvmaze.data.remote.dto.ShowDto;
import com.tarik.tvmaze.data.remote.dto.ShowResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("search/shows")
    Call<List<ShowResponse>> searchForShow(@Query("q") String query);

    @GET("shows/{id}")
    Call<ShowDto> getShowById(@Path("id") long id);

}
