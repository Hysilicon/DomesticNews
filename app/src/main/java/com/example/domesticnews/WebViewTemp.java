package com.example.domesticnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * webview的访问activity
 */
public class WebViewTemp extends AppCompatActivity implements View.OnClickListener{
    private Button btn_url,btn_html,btn_js;

    private Intent intent; //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_temp);

        btn_url = findViewById(R.id.btn_url);
        btn_html = findViewById(R.id.btn_html);

        btn_url.setOnClickListener(this);
        btn_html.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_url:
                intent = new Intent(this,WebViewUrl.class);
                break;
            case R.id.btn_html:
                intent = new Intent(this,WebViewHtml.class);
                break;
        }
        if(intent!=null){
            startActivity(intent);
        }

    }
}