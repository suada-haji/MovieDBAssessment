package com.example.suadahaji.themovieapplication.mvp_movie_listing;

import com.example.suadahaji.themovieapplication.api.ApiClient;
import com.example.suadahaji.themovieapplication.models.Movie;

import java.util.ArrayList;

import io.reactivex.Scheduler;

/**
 * Created by suadahaji
 */

public class MoviePresenterImpl implements MoviePresenter<MovieView>, MovieListener {

    private MovieView movieView;
    private MovieInteractorImpl interactor;

    private ApiClient apiClient = new ApiClient();

    public MoviePresenterImpl(ApiClient apiClient, Scheduler subscribeOn, Scheduler observeOn) {
        interactor = new MovieInteractorImpl(apiClient.provideApiManager(), this, subscribeOn, observeOn);
    }

    @Override
    public void setView(MovieView view) {
        this.movieView = view;
    }

    @Override
    public void displayMovies() {
        if (isViewAttached()) {
            movieView.showProgressBar();
        }
        interactor.loadMovies();
    }

    @Override
    public void destroyView() {
        movieView = null;
        interactor.unbind();
    }

    @Override
    public void onMovieResponse(ArrayList<Movie> movies) {
        movieView.showMovies(movies);
        movieView.hideProgressBar();
    }

    @Override
    public void displayErrorState() {
        movieView.showErrorStateMst();

    }

    @Override
    public void displayEmptyState() {
        movieView.showEmptyStateMsg();

    }

    private boolean isViewAttached() {
        return movieView != null;
    }

}
