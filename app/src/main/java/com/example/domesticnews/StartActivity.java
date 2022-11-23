package com.example.domesticnews;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    private static int time_out = 1500;

    //anim 动画
    Animation top,buttom,middle;

    //动画组件
    View l1,l2,l3,l4,l5,l6;
    TextView title,sbuttom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        //anim 动画
        top = AnimationUtils.loadAnimation(this,R.anim.top);
        buttom = AnimationUtils.loadAnimation(this,R.anim.bottom);
        middle= AnimationUtils.loadAnimation(this,R.anim.middle);

        //动画组件
        l1 = findViewById(R.id.Line1);
        l2 = findViewById(R.id.Line2);
        l3 = findViewById(R.id.Line3);
        l4 = findViewById(R.id.Line4);
        l5 = findViewById(R.id.Line5);
        l6 = findViewById(R.id.Line6);
        title = findViewById(R.id.starttitle);
        sbuttom = findViewById(R.id.startbuttom);

        l1.setAnimation(top);
        l2.setAnimation(top);
        l3.setAnimation(top);
        l4.setAnimation(top);
        l5.setAnimation(top);
        l6.setAnimation(top);

        title.setAnimation(middle);
        sbuttom.setAnimation(buttom);

        //跳转到主页
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },time_out);
}
    }
