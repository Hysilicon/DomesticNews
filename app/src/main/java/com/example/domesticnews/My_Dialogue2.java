package com.example.domesticnews;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

/**
 * quit Popup window
 */
public class My_Dialogue2 extends Dialog {


    public My_Dialogue2(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        //设置对话框布局
        setContentView(R.layout.my_dialogue2);

        //点击事件
        findViewById(R.id.yes11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}
