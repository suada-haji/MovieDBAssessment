package com.example.suadahaji.themovieapplication.mvp_movie_listing;

import com.example.suadahaji.themovieapplication.models.Movie;

import java.util.ArrayList;

/**
 * listens for the data access layer callback
 */

public interface MovieListener {
    void onMovieResponse(ArrayList<Movie> movies);

    void displayErrorState();

    void displayEmptyState();
}
