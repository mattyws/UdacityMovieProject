package com.example.android.udacitymovieproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        //Picasso.with(context).load("http://image.tmdb.org/t/p/w185//path_to_image").into(imageView);
    }
}
