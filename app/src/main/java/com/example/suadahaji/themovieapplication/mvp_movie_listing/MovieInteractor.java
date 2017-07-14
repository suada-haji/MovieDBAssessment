package com.example.suadahaji.themovieapplication.mvp_movie_listing;

/**
 * Bridges between the presentation layer and the data layer
 */

public interface MovieInteractor {
    void loadMovies();
    void unbind();
}
