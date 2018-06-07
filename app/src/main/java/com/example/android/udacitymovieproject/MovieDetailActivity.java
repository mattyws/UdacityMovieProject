package com.example.android.udacitymovieproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.udacitymovieproject.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    ImageView detailImageView;
    TextView titleTextView, releaseDateTextView, originalTitleTextView, synopsisTextView, userRatingTextView;
    ProgressBar detailProgressBar;
    RelativeLayout informationRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        detailImageView = (ImageView) findViewById(R.id.detail_image_view);
        titleTextView = (TextView) findViewById(R.id.detail_movie_title);
        originalTitleTextView = (TextView) findViewById(R.id.detail_movie_original_title);
        releaseDateTextView = (TextView) findViewById(R.id.detail_release_date);
        synopsisTextView = (TextView) findViewById(R.id.detail_movie_synopsis);
        userRatingTextView = (TextView) findViewById(R.id.detail_movie_user_rating);


        detailProgressBar = (ProgressBar) findViewById(R.id.detail_progress_bar);
        informationRelativeLayout = (RelativeLayout) findViewById(R.id.information_layout);

        Intent intent = getIntent();

        if(intent.hasExtra("movie")){
            Movie movie = (Movie) intent.getSerializableExtra("movie");
            // Fetching image
            String url = getString(R.string.api_domain_images) + movie.getPosterPath();
            Uri uri = Uri.parse(getString(R.string.api_domain_images) + movie.getPosterPath());
            Picasso.with(this).load(uri.toString()).fit().centerInside().into(detailImageView);

            // Populating text views
            titleTextView.setText(movie.getTitle());
            originalTitleTextView.setText(movie.getOriginalTitle());
            releaseDateTextView.setText(movie.getReleaseDate().split("-")[0]);
            synopsisTextView.setText(movie.getSinopse());
            userRatingTextView.setText(String.valueOf(movie.getUserRating()));

            // Changing visibilities
            detailProgressBar.setVisibility(View.INVISIBLE);
            informationRelativeLayout.setVisibility(View.VISIBLE);
        }

        //Picasso.with(context).load("http://image.tmdb.org/t/p/w185//path_to_image").into(imageView);
    }
}