package com.example.suadahaji.themovieapplication.mvp_movie_listing;

import com.example.suadahaji.themovieapplication.models.Movie;

import java.util.ArrayList;

/**
 * Created by suadahaji
 */

public interface MovieView {

    void showMovies(ArrayList<Movie> movies);

    void showProgressBar();

    void hideProgressBar();

    void showEmptyStateMsg();

    void showErrorStateMst();

    void onMovieClicked(Movie movie);
}
