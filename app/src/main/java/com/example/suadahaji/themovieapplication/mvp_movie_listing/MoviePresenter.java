package com.example.suadahaji.themovieapplication.mvp_movie_listing;

/**
 * Created by suadahaji
 */

public interface MoviePresenter<V> {
    void setView(V view);

    void displayMovies();

    void destroyView();
}
