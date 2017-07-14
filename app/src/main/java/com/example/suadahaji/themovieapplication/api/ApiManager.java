package com.example.suadahaji.themovieapplication.api;

import com.example.suadahaji.themovieapplication.models.MovieResponse;
import com.example.suadahaji.themovieapplication.models.ReviewResponse;
import com.example.suadahaji.themovieapplication.models.VideoResponse;
import com.example.suadahaji.themovieapplication.util.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by suadahaji
 */

public interface ApiManager {
    @GET(Constants.GET_POPULAR_MOVIES)
    Observable<MovieResponse> getPopularMovies();

    @GET(Constants.GET_REVIEWS)
    Observable<ReviewResponse> getReviews(@Path("id") int id);

    @GET(Constants.GET_VIDEOS)
    Observable<VideoResponse> getVideos(@Path("id") int id);

}
