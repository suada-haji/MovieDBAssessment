package com.example.suadahaji.themovieapplication.mvp_movie_detail;

/**
 * Created by suadahaji
 */

public interface MovieDetailsPresenter<V>  {

    void showVideos();

    void showReviews();

    void setDetailsView(V view);

    void destroy();
}
