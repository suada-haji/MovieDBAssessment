package com.example.suadahaji.themovieapplication.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by suadahaji
 */

public class Movie {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("vote_count")
    private int voteCount;
    @SerializedName("vote_average")
    private int voteAverage;
    @SerializedName("poster_path")
    private int posterPath;
    @SerializedName("original_title")
    private int originalTitle;
    @SerializedName("backdrop_path")
    private int backdropPath;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;

    public Movie() {
    }

    public Movie(int id, String title, int voteCount, int voteAverage, int posterPath, int originalTitle, int backdropPath, String overview, String releaseDate) {
        this.id = id;
        this.title = title;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(int posterPath) {
        this.posterPath = posterPath;
    }

    public int getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(int originalTitle) {
        this.originalTitle = originalTitle;
    }

    public int getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(int backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
