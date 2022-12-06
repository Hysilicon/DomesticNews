package com.example.domesticnews;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
/**
 * quit Popup window
 */
public class My_Dialogue extends Dialog {


    public My_Dialogue(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        //set the conversation
        setContentView(R.layout.my_dialogue);

        //click event
        findViewById(R.id.yes1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        findViewById(R.id.no1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
