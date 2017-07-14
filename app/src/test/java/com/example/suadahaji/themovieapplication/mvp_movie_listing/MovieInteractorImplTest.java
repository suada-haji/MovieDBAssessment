package com.example.suadahaji.themovieapplication.mvp_movie_listing;

import com.example.suadahaji.themovieapplication.api.ApiManager;
import com.example.suadahaji.themovieapplication.models.MovieResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.subscribers.TestSubscriber;

/**
 * Created by suadahaji
 */
public class MovieInteractorImplTest {

    @Mock
    ApiManager apiManager;

    @Mock
    MovieListener listener;

    @Mock MovieInteractorImpl interactor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void loadMovies() throws Exception {
        TestSubscriber<MovieResponse> subscriber = new TestSubscriber<>();

        Observable<MovieResponse> movieResponseObservable;

       // when(apiManager.getPopularMovies()).thenReturn();


    }

}