package com.example.suadahaji.themovieapplication.mvp_movie_listing;

import com.example.suadahaji.themovieapplication.api.ApiClient;
import com.example.suadahaji.themovieapplication.api.ApiManager;
import com.example.suadahaji.themovieapplication.models.MovieResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by suadahaji
 */
public class MovieInteractorImplTest {

    private Response<MovieResponse> movieResponse;

    @Mock
    ApiManager apiManager;

    @Mock
    MovieListener listener;

    private MovieInteractorImpl interactor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ApiClient apiClient = new ApiClient();
        interactor = new MovieInteractorImpl(apiClient.provideApiManager(), listener, Schedulers.io(), Schedulers.io());
    }

    @Test
    public void loadMovies() throws Exception {

        Observable<MovieResponse> booksResponseObservable = interactor.loadMovies();

        booksResponseObservable
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(@NonNull MovieResponse movieResponse) throws Exception {

                        assertNotNull(movieResponse);
                        assertTrue(movieResponse.getMovies().size() > 0);
                    }
                });

    }
}