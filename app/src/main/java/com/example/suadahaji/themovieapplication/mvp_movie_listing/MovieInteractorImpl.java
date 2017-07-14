package com.example.suadahaji.themovieapplication.mvp_movie_listing;

import com.example.suadahaji.themovieapplication.api.ApiManager;
import com.example.suadahaji.themovieapplication.models.Movie;
import com.example.suadahaji.themovieapplication.models.MovieResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by suadahaji
 */

public class MovieInteractorImpl implements MovieInteractor {

    private ApiManager apiManager;

    private MovieListener listener;

    private Scheduler subscribeOn;

    private Scheduler observeOn;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MovieInteractorImpl(ApiManager apiManager, MovieListener listener, Scheduler subscribeOn, Scheduler observeOn) {
        this.apiManager = apiManager;
        this.listener = listener;
        this.subscribeOn = subscribeOn;
        this.observeOn = observeOn;
    }

    @Override
    public void loadMovies() {
        Observable<MovieResponse> movieResponseObservable = apiManager.getPopularMovies();

        compositeDisposable.add(
                movieResponseObservable
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(
                        new Consumer<MovieResponse>() {
                            @Override
                            public void accept(@NonNull MovieResponse movieResponse) throws Exception {
                                if (movieResponse == null || movieResponse.getMovies() == null || movieResponse.getMovies().size() == 0) {
                                    listener.displayEmptyState();
                                }
                                ArrayList<Movie> movies = movieResponse.getMovies();
                                listener.onMovieResponse(movies);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                listener.displayErrorState();
                            }
                        }
                )
        );

    }

    @Override
    public void unbind() {
        compositeDisposable.dispose();
    }
}
