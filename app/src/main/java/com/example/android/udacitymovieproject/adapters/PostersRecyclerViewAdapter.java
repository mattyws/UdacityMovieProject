package com.example.android.udacitymovieproject.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.udacitymovieproject.R;
import com.example.android.udacitymovieproject.model.Movie;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.List;

public class PostersRecyclerViewAdapter extends RecyclerView.Adapter<PostersRecyclerViewAdapter.ViewHolder>{

    private Context context;
    private ClickListener clickListener;
    private List<Movie> data;

    public PostersRecyclerViewAdapter(Context context, List<Movie> data){
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.poster_view_cell, parent, false);
        ViewHolder holder =  new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = data.get(position);
        String url = context.getString(R.string.api_domain_images) + movie.getPosterPath();
//        Log.d(PostersRecyclerViewAdapter.class.getCanonicalName(), "Movie url + " + url);
        Uri uri = Uri.parse(context.getString(R.string.api_domain_images) + movie.getPosterPath());
        Picasso.with(context).load(uri.toString()).fit().centerInside().into(holder.posterImageView);
        holder.idTextView.setText(String.valueOf(movie.getId()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView posterImageView;
        public TextView idTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            posterImageView = (ImageView) itemView.findViewById(R.id.mv_image_banner);
            idTextView = (TextView) itemView.findViewById(R.id.mv_movie_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
        }

    }

    public Movie getMovie(int id) throws JSONException {
        return data.get(id);
    }

    public void setClickListener(ClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public interface ClickListener {
        void onItemClick(View view, int position);
    }

    public void setMovies(List<Movie> movies){
        this.data = movies;
    }
}
