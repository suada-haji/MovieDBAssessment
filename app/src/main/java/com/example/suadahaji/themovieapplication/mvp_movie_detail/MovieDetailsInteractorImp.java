package com.example.suadahaji.themovieapplication.mvp_movie_detail;


import com.example.suadahaji.themovieapplication.api.ApiManager;
import com.example.suadahaji.themovieapplication.models.Movie;
import com.example.suadahaji.themovieapplication.models.Review;
import com.example.suadahaji.themovieapplication.models.ReviewResponse;
import com.example.suadahaji.themovieapplication.models.Video;
import com.example.suadahaji.themovieapplication.models.VideoResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by suadahaji
 */

public class MovieDetailsInteractorImp implements MovieDetailsInteractor {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MovieDetailsListener listener;

    private Movie mMovie;

    private final Scheduler subscribeOn;

    private final Scheduler observeOn;

    private ApiManager apiManager;


    public MovieDetailsInteractorImp(ApiManager apiManager, MovieDetailsListener listener, Movie movie, Scheduler subscribeOn, Scheduler observeOn) {
        this.apiManager = apiManager;
        this.listener = listener;
        this.mMovie = movie;
        this.subscribeOn = subscribeOn;
        this.observeOn = observeOn;
    }


    @Override
    public void loadReviews() {

        final Observable<ReviewResponse> reviewResponse = apiManager.getReviews(mMovie.getId());

        compositeDisposable.add(reviewResponse
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(new Consumer<ReviewResponse>() {
                               @Override
                               public void accept(ReviewResponse reviewResponse) {
                                   ArrayList<Review> reviews = reviewResponse.getReviews();
                                   listener.onReviewsLoaded(reviews);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) {
                                   listener.displayErrorState();
                               }
                           }
                )
        );
    }

    @Override
    public void loadVideos() {

        final Observable<VideoResponse> videoObservable = apiManager.getVideos(mMovie.getId());

        compositeDisposable.add(videoObservable
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(new Consumer<VideoResponse>() {
                    @Override
                    public void accept(VideoResponse videoResponse) {
                        ArrayList<Video> videos = videoResponse.getVideos();
                        listener.onVideosLoaded(videos);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        listener.displayErrorState();
                    }
                })
        );
    }

    @Override
    public void unbind() {
        compositeDisposable.dispose();
    }
}
