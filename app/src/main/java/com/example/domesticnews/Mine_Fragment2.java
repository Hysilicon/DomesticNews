package com.example.domesticnews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
/**
 * register page
 * item: email,password,confirmPassword,username,
 */
public class Mine_Fragment2 extends Fragment {
    EditText email,password,cpassword,username;
    Button register;
    float a=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        ViewGroup mine = (ViewGroup) inflater.inflate(R.layout.fragment2_mine,container,false);

        email= mine.findViewById(R.id.email2);
        password=mine.findViewById(R.id.password2);
        cpassword = mine.findViewById(R.id.cpass);
        username = mine.findViewById(R.id.username2);
        register = mine.findViewById(R.id.register);

        email.setTranslationY(800);
        password.setTranslationY(800);
        cpassword.setTranslationY(800);
        username.setTranslationY(800);
        register.setTranslationY(800);

        email.setAlpha(a);
        password.setAlpha(a);
        cpassword.setAlpha(a);
        username.setAlpha(a);
        register.setAlpha(a);

        email.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        cpassword.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        username.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();
        register.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(900).start();

        return mine;
    }
}

