package com.example.android.udacitymovieproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.udacitymovieproject.adapters.PostersRecyclerViewAdapter;
import com.example.android.udacitymovieproject.model.Movie;
import com.example.android.udacitymovieproject.model.enumerate.ListingEnum;
import com.example.android.udacitymovieproject.utils.UrlUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PostersRecyclerViewAdapter.ClickListener {

    ListingEnum listingEnum = ListingEnum.TOP_RATED;
    PostersRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    ProgressBar moviesProgressBar;
    private List<Movie> movies;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // data to populate the RecyclerView with
        moviesProgressBar = (ProgressBar) findViewById(R.id.pb_posters_progress_bar);
        recyclerView = (RecyclerView) findViewById(R.id.mv_poster_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        movies = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        try {
            parseJsonMovies();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();
        if(itemSelected == R.id.menu_sort_popular){
            listingEnum = ListingEnum.POPULAR;
        } else if(itemSelected == R.id.menu_sort_top_rated){
            listingEnum = ListingEnum.TOP_RATED;
        }
        try {
            moviesProgressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
            movies = new ArrayList<>();
            refreshMovies();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshMovies() throws MalformedURLException {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                UrlUtils.buildUrl(this, listingEnum).toString(),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            createMovieArray(response);
                            adapter.setMovies(movies);
                            moviesProgressBar.setVisibility(View.INVISIBLE);
                            recyclerView.setVisibility(View.VISIBLE);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }

        );
        requestQueue.add(request);
    }

    private void parseJsonMovies() throws MalformedURLException {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                UrlUtils.buildUrl(this, listingEnum).toString(),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            createMovieArray(response);
                            adapter = new PostersRecyclerViewAdapter(MainActivity.this, movies);
                            recyclerView.setAdapter(adapter);
                            adapter.setClickListener(MainActivity.this);
                            moviesProgressBar.setVisibility(View.INVISIBLE);
                            recyclerView.setVisibility(View.VISIBLE);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }

        );
        requestQueue.add(request);
    }

    private void createMovieArray(JSONObject response) throws JSONException {
        JSONArray jsonMovies = response.getJSONArray(getString(R.string.json_movies_result));
        for (int i = 0; i < jsonMovies.length(); i++){
            JSONObject jsonMovie = jsonMovies.getJSONObject(i);
            int id = jsonMovie.getInt(getString(R.string.json_movie_id));
            String title = jsonMovie.getString(getString(R.string.json_movie_title));
            String posterPath = jsonMovie.getString(getString(R.string.json_movie_poster_path));
            double userRating = jsonMovie.getDouble(getString(R.string.json_movie_user_rating));
            String sinopse = jsonMovie.getString(getString(R.string.json_movie_sinopse));
            String releaseDate = jsonMovie.getString(getString(R.string.json_movie_release_date));
            String originalTitle = jsonMovie.getString(getString(R.string.json_movie_original_title));

            Movie m = new Movie(id, posterPath, title, originalTitle, releaseDate, sinopse, userRating);
            movies.add(m);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent initDetailActivity = new Intent(MainActivity.this, MovieDetailActivity.class);
        initDetailActivity.putExtra("movie", movies.get(position));
        startActivity(initDetailActivity);
//        Toast.makeText(view.getContext(), "Testando", Toast.LENGTH_LONG)
//                .show();
    }
}
