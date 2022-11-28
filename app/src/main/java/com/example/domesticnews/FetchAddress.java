//package com.example.domesticnews;
//
//import android.annotation.SuppressLint;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.location.Location;
//import android.os.AsyncTask;
//import android.util.Log;
//import android.webkit.JsResult;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.TextView;
//
//import androidx.appcompat.widget.Toolbar;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.lang.ref.WeakReference;
//
////Asynchronous task
//public class FetchAddress extends AsyncTask<String, Void, String> {
//
//
//    private WeakReference<TextView> title;
//    private WeakReference<WebView> newsWebView;
//
//
//    public FetchAddress(TextView locationText, WebView webview) {
//        this.title = new WeakReference<>(locationText);
//        this.newsWebView = new WeakReference<>(webview);
//    }
//
//
//
//    @Override
//    protected String doInBackground(String... strings) {
//        return NetworkUtils.getAddress(strings[0]);
//    }
////加载地址对应的URL
////Load the URL corresponding to the address
//    @SuppressLint("SetJavaScriptEnabled")
//    @Override
//    protected void onPostExecute(String s) {
//
////
////        {
////            "status":"OK",
////                "result":{
////            "location":{
////                "lng":116.379763,
////                        "lat":39.913542
////            },
////            "formatted_address":"北京市西城区复兴门内大街5号",
////                    "business":"西单,宣武门,和平门",
////                    "addressComponent":{
////                "city":"北京市",
////                        "direction":"near",
////                        "distance":"45",
////                        "district":"西城区",
////                        "province":"北京市",
////                        "street":"复兴门内大街",
////                        "street_number":"5号"
////            },
////            "cityCode":131
////        }
////        }
//
////Update the UI at the interface layer and update the webview here
//        super.onPostExecute(s);
//        try {
//            //...
//            JSONObject q = new JSONObject(s);
//            Log.d("Json", q.toString());
//            String formatted_address = null;
//
//
//            try {
//                JSONObject result = q.getJSONObject("result");
//                formatted_address = result.getJSONObject("addressComponent").getString("district");
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//            if (formatted_address != null) {
////                locationText.get().setText(formatted_address);
//                title.get().setText(formatted_address);
//                String titleText = title.get().getText().toString();
//                Log.d("title text:", titleText);
//
//                newsWebView.get().setWebViewClient(new WebViewClient());
//                newsWebView.get().setWebChromeClient(new WebChromeClient());
//                newsWebView.get().loadUrl("http://121.37.95.54:3001/news?address=" + titleText);
//                WebSettings webSettings = newsWebView.get().getSettings();
//                webSettings.setJavaScriptEnabled(true);
//
//
//
//            } else {
////                locationText.get().setText(R.string.no_results);
//                title.get().setText(R.string.no_results);
//            }
//
//
//        } catch (JSONException e) {
//            // If onPostExecute does not receive a proper JSON string,
//            // update the UI to show failed results.
////            locationText.get().setText(R.string.no_results);
//            e.printStackTrace();
//        }
//
//    }
//}
