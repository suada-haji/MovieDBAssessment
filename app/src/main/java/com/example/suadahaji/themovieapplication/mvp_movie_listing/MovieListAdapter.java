package com.example.suadahaji.themovieapplication.mvp_movie_listing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.suadahaji.themovieapplication.R;
import com.example.suadahaji.themovieapplication.models.Movie;
import com.example.suadahaji.themovieapplication.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by suadahaji
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private ArrayList<Movie> movies;

    private Context context;

    private MovieView movieView;

    private int lastPosition = -1;

    public MovieListAdapter(ArrayList<Movie> movies, MovieView movieView) {
        this.movies = movies;
        this.movieView = movieView;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.movie_poster)
        ImageView movieImage;
        @BindView(R.id.movie_name)
        TextView movieName;

        private Movie movie;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            MovieListAdapter.this.movieView.onMovieClicked(movie);

        }
    }

    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View root = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(root);
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.MovieViewHolder holder, int position) {

        setAnimation(holder.itemView, position);
        holder.itemView.setOnClickListener(holder);
        holder.movie = movies.get(position);
        Picasso.with(context).load(Constants.POSTER_PATH + holder.movie.getPosterPath()).into(holder.movieImage);
        holder.movieName.setText(holder.movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(1000);
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }


}
