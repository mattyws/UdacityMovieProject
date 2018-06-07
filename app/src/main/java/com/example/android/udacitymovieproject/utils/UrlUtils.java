package com.example.android.udacitymovieproject.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.android.udacitymovieproject.R;
import com.example.android.udacitymovieproject.model.Movie;
import com.example.android.udacitymovieproject.model.enumerate.ListingEnum;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class UrlUtils {


    public static URL buildUrl(Context context, ListingEnum listingEnum) throws MalformedURLException {
        Uri uri;
        if(listingEnum.equals(ListingEnum.POPULAR)){
            uri = Uri.parse(context.getString(R.string.api_domain_popular));
        } else {
            uri = Uri.parse(context.getString(R.string.api_domain_top_rated));
        }
        uri = uri.buildUpon().appendQueryParameter(context.getString(R.string.api_key_query), context.getString(R.string.API_KEY))
                .build();
        URL url = new URL(uri.toString());
        return url;
    }

}
