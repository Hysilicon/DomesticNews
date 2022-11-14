package com.example.domesticnews;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchAddress extends AsyncTask<String, Void, String> {

    private WeakReference<Toolbar> toolbar;

    public FetchAddress(Toolbar toolbar) {
        this.toolbar = new WeakReference<>(toolbar);
    }

//    private WeakReference<TextView> locationText;
//
//    public FetchAddress(TextView locationText) {
//        this.locationText = new WeakReference<>(locationText);
//    }



    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getAddress(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {

//
//        {
//            "status":"OK",
//                "result":{
//            "location":{
//                "lng":116.379763,
//                        "lat":39.913542
//            },
//            "formatted_address":"北京市西城区复兴门内大街5号",
//                    "business":"西单,宣武门,和平门",
//                    "addressComponent":{
//                "city":"北京市",
//                        "direction":"near",
//                        "distance":"45",
//                        "district":"西城区",
//                        "province":"北京市",
//                        "street":"复兴门内大街",
//                        "street_number":"5号"
//            },
//            "cityCode":131
//        }
//        }

        super.onPostExecute(s);
        try {
            //...
            JSONObject q = new JSONObject(s);
            Log.d("Json", q.toString());
            String formatted_address = null;


            try {
                JSONObject result = q.getJSONObject("result");
                formatted_address = result.getString("formatted_address");

            } catch (Exception e) {
                e.printStackTrace();
            }


            if (formatted_address != null) {
//                locationText.get().setText(formatted_address);
                toolbar.get().setTitle(formatted_address);
            } else {
//                locationText.get().setText(R.string.no_results);
                toolbar.get().setTitle(R.string.no_results);
            }


        } catch (JSONException e) {
            // If onPostExecute does not receive a proper JSON string,
            // update the UI to show failed results.
//            locationText.get().setText(R.string.no_results);
            e.printStackTrace();
        }

    }
}
