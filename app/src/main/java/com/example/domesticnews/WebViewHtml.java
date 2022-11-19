package com.example.domesticnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebViewHtml extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_html);


        WebView webView = findViewById(R.id.WebView_URL);
        //webView.loadUrl("http://www.bilibili.com");

        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);

        //字符串构建器将要显示的html内容放置在这个构造器中
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("<div>填写相对应的html元素</div>");
//        stringBuilder.append("<li>填写相对应的html元素</li>");
//        stringBuilder.append("<li>填写相对应的html元素</li>");
//        stringBuilder.append("<li>填写相对应的html元素</li>");
//        stringBuilder.append("<a href=\\\"http://121.37.95.54:3001/allnews\\\">对应url</a></h2></body></html>");
        String sss = "<html><head><title>欢迎</title></head><body><h2>欢迎您访问"+
                "<a href=\"http://121.37.95.54:3001/allnews\">新闻界面</a></h2></body></html>";


        //加载数据
        webView.loadData(sss,"text/html","utf-8");
        //webView.loadDataWithBaseURL("null",stringBuilder.toString(),"text/html","utf-8",null);
    }
}