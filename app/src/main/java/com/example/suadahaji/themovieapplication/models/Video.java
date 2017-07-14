package com.example.suadahaji.themovieapplication.models;

import com.example.suadahaji.themovieapplication.util.Constants;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suadahaji
 */

public class Video {

    public static final String SITE_YOUTUBE = "Youtube";

    @SerializedName("id")
    private String id;
    @SerializedName("key")
    private String key;
    @SerializedName("name")
    private String name;
    @SerializedName("site")
    private String site;
    @SerializedName("size")
    private int size;
    @SerializedName("type")
    private String type;

    public Video() {
    }

    public static String getUrl(Video video) {
        if (SITE_YOUTUBE.equalsIgnoreCase(video.getSite())) {
            return String.format(Constants.YOUTUBE_VIDEO_URL, video.getId());
        } else {
            return "";
        }
    }

    public static String getThumbnailUrl(Video video) {
        if (SITE_YOUTUBE.equalsIgnoreCase(video.getSite())) {
            return String.format(Constants.YOUTUBE_THUMBNAIL_URL, video.getId());
        } else {
            return "";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
