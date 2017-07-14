package com.example.suadahaji.themovieapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by suadahaji
 */

public class VideoResponse {
    @SerializedName("results")
    ArrayList<Video> videos;

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }
}
