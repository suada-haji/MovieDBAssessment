package com.example.suadahaji.themovieapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by suadahaji
 */

public class ReviewResponse {
    @SerializedName("results")
    private ArrayList<Review> reviews;

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
}
