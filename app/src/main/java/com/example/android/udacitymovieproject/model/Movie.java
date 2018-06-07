package com.example.android.udacitymovieproject.model;

import android.net.Uri;
import android.util.JsonReader;

import com.example.android.udacitymovieproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Movie implements Serializable{

    int id;
    double userRating;
    String title, posterPath, sinopse, releaseDate, originalTitle;

    public Movie(int id, String posterPath, String title, String originalTitle, String releaseDate, String sinopse,
                 double userRating){
        setId(id);
        setTitle(title);
        setPosterPath(posterPath);
        setOriginalTitle(originalTitle);
        setReleaseDate(releaseDate);
        setSinopse(sinopse);
        setUserRating(userRating);
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

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }
}
