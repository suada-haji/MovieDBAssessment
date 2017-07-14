package com.example.suadahaji.themovieapplication.mvp_movie_detail;


import com.example.suadahaji.themovieapplication.models.Review;
import com.example.suadahaji.themovieapplication.models.Video;

import java.util.ArrayList;

/**
 * Created by suadahaji
 */

public interface MovieDetailsListener {

    void onReviewsLoaded(ArrayList<Review> reviews);
    void onVideosLoaded(ArrayList<Video> videos);
    void displayErrorState();
}
