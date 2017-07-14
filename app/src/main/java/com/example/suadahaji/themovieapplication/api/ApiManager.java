package com.example.suadahaji.themovieapplication.api;

import com.example.suadahaji.themovieapplication.models.MovieResponse;
import com.example.suadahaji.themovieapplication.util.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by suadahaji
 */

public interface ApiManager {
    @GET(Constants.GET_POPULAR_MOVIES)
    Observable<MovieResponse> getPopularMovies();
}
