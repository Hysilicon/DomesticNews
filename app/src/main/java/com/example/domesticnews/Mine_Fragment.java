package com.example.domesticnews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

/**
 * log in page
 * item: email,password
 */
public class Mine_Fragment extends Fragment {
    EditText email,password;
    TextView forgetpass;
    Button login;
    float a=0;

    private static Mine_Fragment instance = null;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        ViewGroup mine = (ViewGroup) inflater.inflate(R.layout.fragment_mine,container,false);

        email= mine.findViewById(R.id.email1);
        password=mine.findViewById(R.id.password1);
        forgetpass = mine.findViewById(R.id.forgetpass);
        login = mine.findViewById(R.id.login);

        email.setTranslationY(800);
        password.setTranslationY(800);
        forgetpass.setTranslationY(800);
        login.setTranslationY(800);

        email.setAlpha(a);
        password.setAlpha(a);
        forgetpass.setAlpha(a);
        login.setAlpha(a);

        email.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        forgetpass.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();

        return mine;


    }

    public static Mine_Fragment getInstance() {
        if (instance == null) {
            instance = new Mine_Fragment();
        }
        return instance;
    }



}
