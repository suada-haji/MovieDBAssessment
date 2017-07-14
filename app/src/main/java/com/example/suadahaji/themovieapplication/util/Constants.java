package com.example.suadahaji.themovieapplication.util;

import com.example.suadahaji.themovieapplication.BuildConfig;

/**
 * Created by suadahaji
 */

public class Constants {
    public static final String API_KEY = BuildConfig.TMDB_KEY;
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String GET_POPULAR_MOVIES = "discover/movie?language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&api_key=" +  API_KEY;
    public static final String POSTER_PATH = "http://image.tmdb.org/t/p/w500";
    public static final String GET_VIDEOS = "http://api.themoviedb.org/3/movie/{id}/videos?api_key=" + API_KEY;
    public static final String GET_REVIEWS = "http://api.themoviedb.org/3/movie/{id}/reviews?api_key=" + API_KEY;
    public static final String YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=";
    public static final String YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/";
    public static final String MOVIE = "movie";
}
