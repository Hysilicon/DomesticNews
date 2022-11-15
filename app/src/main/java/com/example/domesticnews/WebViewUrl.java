package com.example.domesticnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

/**
 * 直接访问url地址
 */

public class WebViewUrl extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_url);


        WebView webView = findViewById(R.id.WebView_URL);
        webView.loadUrl("http://121.37.95.54:3001/articles/");

    }
}