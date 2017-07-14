package com.example.suadahaji.themovieapplication.mvp_movie_detail;

import com.example.suadahaji.themovieapplication.api.ApiClient;
import com.example.suadahaji.themovieapplication.models.Movie;
import com.example.suadahaji.themovieapplication.models.Review;
import com.example.suadahaji.themovieapplication.models.Video;

import java.util.ArrayList;

import io.reactivex.Scheduler;

/**
 * Created by suadahaji
 */

public class MovieDetailsPresenterImpl implements MovieDetailsPresenter<MovieDetailsView>, MovieDetailsListener {

    private MovieDetailsView view;
    private MovieDetailsInteractorImp interactor;


    public MovieDetailsPresenterImpl(ApiClient apiClient, Movie movie, Scheduler subscribeOn, Scheduler observeOn) {
        interactor = new MovieDetailsInteractorImp(apiClient.provideApiManager(), this, movie, subscribeOn, observeOn);
    }

    @Override
    public void showVideos() {
        interactor.loadVideos();
    }

    @Override
    public void showReviews() {
        interactor.loadReviews();
    }

    @Override
    public void setDetailsView(MovieDetailsView view) {
        this.view = view;
        showVideos();
        showReviews();
    }

    @Override
    public void destroy() {
        view = null;
        interactor.unbind();
    }

    @Override
    public void onReviewsLoaded(ArrayList<Review> reviews) {
            view.showReviews(reviews);
    }

    @Override
    public void onVideosLoaded(ArrayList<Video> videos) {
            view.showVideos(videos);
    }

    @Override
    public void displayErrorState() {
            view.showDetailErrorMessage();
    }
}
