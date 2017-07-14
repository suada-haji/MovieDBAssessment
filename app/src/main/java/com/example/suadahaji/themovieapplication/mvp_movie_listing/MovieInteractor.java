package com.example.suadahaji.themovieapplication.mvp_movie_listing;

import com.example.suadahaji.themovieapplication.models.MovieResponse;

import io.reactivex.Observable;

/**
 * Bridges between the presentation layer and the data layer
 */

public interface MovieInteractor {
    Observable<MovieResponse> loadMovies();
    void unbind();
}
