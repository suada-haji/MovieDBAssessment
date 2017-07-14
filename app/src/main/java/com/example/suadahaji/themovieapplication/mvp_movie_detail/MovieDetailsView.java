package com.example.suadahaji.themovieapplication.mvp_movie_detail;


import com.example.suadahaji.themovieapplication.models.Review;
import com.example.suadahaji.themovieapplication.models.Video;

import java.util.ArrayList;

/**
 * Created by suadahaji
 */

public interface MovieDetailsView {

    void showVideos(ArrayList<Video> videos);

    void showReviews(ArrayList<Review> reviews);

    void showDetailErrorMessage();

}
