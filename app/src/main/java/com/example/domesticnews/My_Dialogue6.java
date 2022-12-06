package com.example.domesticnews;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Calendar;

/**
 * quit Popup window
 */
public class My_Dialogue6 extends Dialog {

    private boolean auto_switch = false;
    public My_Dialogue6(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        //set the conversation
        setContentView(R.layout.my_dialogue6);

        //click event
        findViewById(R.id.no111111).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public boolean set_boolean(){
        return false;
    }


}
