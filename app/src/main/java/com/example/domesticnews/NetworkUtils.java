package com.example.domesticnews;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.Locale;

public class NetworkUtils {

    private static final String LOG_TAG =
            NetworkUtils.class.getSimpleName();
    // Base URL for Books API.
    private static final String BASE_URL =  "http://api.map.baidu.com/geocoder?";

    private static final String QUERY_OUTPUT = "output";
    // Parameter that limits search results.
    private static final String LOCATION = "location";

    private static final String API_KEY = "ak";
    private static final String BASE_URL_NEWS = "http://121.37.95.54:3001/newsforjson?";
    private static final String ADDRESS = "address";



    static String getAddress(String location){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String addressJsonString = null;

        Uri builtURI = Uri.parse(BASE_URL).buildUpon().appendQueryParameter(QUERY_OUTPUT, "json")
                .appendQueryParameter(LOCATION, location)
                .appendQueryParameter(API_KEY, "esNPFDwwsXWtsQfw4NMNmur1")
                .build();

        try {
            //...
            String reqURL = builtURI.toString().replace("%2C", ",");
            reqURL = URLDecoder.decode(reqURL, "UTF-8");
            URL requestURL = new URL(reqURL);
            Log.d(LOG_TAG, reqURL);
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                // Since it's JSON, adding a newline isn't necessary (it won't
                // affect parsing) but it does make debugging a *lot* easier
                // if you print out the completed buffer for debugging.
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }
            addressJsonString = builder.toString();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //...
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return addressJsonString;


    }

    static String getNews(String address){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String addressJsonString = null;

        Uri builtURI = Uri.parse(BASE_URL_NEWS).buildUpon().appendQueryParameter(ADDRESS, address)
                .build();

        try {
            //...
            String reqURL = builtURI.toString().replace("%2C", ",");
            reqURL = URLDecoder.decode(reqURL, "UTF-8");
            URL requestURL = new URL(reqURL);
            Log.d(LOG_TAG, reqURL);
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                // Since it's JSON, adding a newline isn't necessary (it won't
                // affect parsing) but it does make debugging a *lot* easier
                // if you print out the completed buffer for debugging.
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }
            addressJsonString = builder.toString();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //...
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return addressJsonString;


    }

    static String getAddressByGeocoder(Context context, Location location){
        String locationText = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),
                    1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        locationText = addresses.get(0).getAddressLine(0);
        return locationText;
    }



}
