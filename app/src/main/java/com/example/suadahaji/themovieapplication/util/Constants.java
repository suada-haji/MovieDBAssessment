package com.example.suadahaji.themovieapplication.util;

import com.example.suadahaji.themovieapplication.BuildConfig;

/**
 * Created by suadahaji
 */

public class Constants {
    public static final String API_KEY = BuildConfig.TMDB_KEY;
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String GET_POPULAR_MOVIES = "discover/movie?language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&api_key=" +  API_KEY;
}
