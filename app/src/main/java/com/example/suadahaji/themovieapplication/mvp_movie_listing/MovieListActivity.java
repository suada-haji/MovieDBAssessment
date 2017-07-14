package com.example.suadahaji.themovieapplication.mvp_movie_listing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.suadahaji.themovieapplication.R;
import com.example.suadahaji.themovieapplication.api.ApiClient;
import com.example.suadahaji.themovieapplication.models.Movie;
import com.example.suadahaji.themovieapplication.mvp_movie_detail.MovieDetailActivity;
import com.example.suadahaji.themovieapplication.util.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieListActivity extends AppCompatActivity implements MovieView {

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

        setupRecyclerView();
    }

    private void setUpToolbar() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Popular Movies");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.displayMovies();
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
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(Constants.MOVIE, movie);
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.destroyView();
    }
}
