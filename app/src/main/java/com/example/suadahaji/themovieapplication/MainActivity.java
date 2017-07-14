package com.example.suadahaji.themovieapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suadahaji.themovieapplication.api.ApiClient;
import com.example.suadahaji.themovieapplication.models.Movie;
import com.example.suadahaji.themovieapplication.mvp_movie_listing.MovieListAdapter;
import com.example.suadahaji.themovieapplication.mvp_movie_listing.MoviePresenterImpl;
import com.example.suadahaji.themovieapplication.mvp_movie_listing.MovieView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MovieView {

    public static final String MOVIE = "movie";

    private MoviePresenterImpl presenter;

    private RecyclerView.Adapter adapter;

    private ArrayList<Movie> moviesArray = new ArrayList<>();

    private ApiClient apiClient;

    @BindView(R.id.movies_recycler_view)
    RecyclerView moviesListing;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.empty_state)
    TextView emptyTextView;
    @BindView(R.id.error_state)
    TextView errorTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();

        ButterKnife.bind(this);


        apiClient = new ApiClient();
        presenter = new MoviePresenterImpl(apiClient, Schedulers.io(), AndroidSchedulers.mainThread());
        presenter.setView(this);
        presenter.displayMovies();

        setupRecyclerView();
    }

    private void setUpToolbar() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Popular Movies");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    private void setupRecyclerView() {
        moviesListing.setHasFixedSize(true);

        moviesListing.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MovieListAdapter(moviesArray, this);
        moviesListing.setAdapter(adapter);
    }

    @Override
    public void showMovies(ArrayList<Movie> movies) {
        this.moviesArray.clear();
        this.moviesArray.addAll(movies);
        moviesListing.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        moviesListing.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        moviesListing.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showEmptyStateMsg() {
        emptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorStateMst() {
        errorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMovieClicked(Movie movie) {
        Toast.makeText(this, "Movie clicked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.destroyView();
    }
}
