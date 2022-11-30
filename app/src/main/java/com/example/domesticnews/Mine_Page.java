package com.example.domesticnews;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Mine_Page extends AppCompatActivity {
    View account,grade,personal_information,inbox,setting00,complaint;
    TextView account1,grade1,personal_information1,inbox1,setting001,complaint1;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_mine);

        account = findViewById(R.id.account_information);
        account1 = findViewById(R.id.account_information1);
        grade = findViewById(R.id.integral);
        grade1 = findViewById(R.id.integral1);
        personal_information = findViewById(R.id.personal_information);
        personal_information1 = findViewById(R.id.personal_information1);
        inbox = findViewById(R.id.inbox);
        inbox1 = findViewById(R.id.inbox1);
        setting00 = findViewById(R.id.setting00);
        setting001 = findViewById(R.id.setting001);
        complaint = findViewById(R.id.complaint);
        complaint1 = findViewById(R.id.complaint1);

        showdialogue();

    }

    private void showdialogue(){
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My_Dialogue2 m1 = new My_Dialogue2(Mine_Page.this, R.style.mydialogue);
                m1.show();
            }
        });
        account1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My_Dialogue2 m1 = new My_Dialogue2(Mine_Page.this, R.style.mydialogue);
                m1.show();
            }
        });

        grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My_Dialogue3 m1 = new My_Dialogue3(Mine_Page.this, R.style.mydialogue);
                m1.show();
            }
        });

        grade1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My_Dialogue3 m1 = new My_Dialogue3(Mine_Page.this, R.style.mydialogue);
                m1.show();
            }
        });

        personal_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My_Dialogue4 m1 = new My_Dialogue4(Mine_Page.this, R.style.mydialogue);
                m1.show();
            }
        });
        personal_information1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My_Dialogue4 m1 = new My_Dialogue4(Mine_Page.this, R.style.mydialogue);
                m1.show();
            }
        });

        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My_Dialogue5 m1 = new My_Dialogue5(Mine_Page.this, R.style.mydialogue);
                m1.show();
            }
        });
        inbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My_Dialogue5 m1 = new My_Dialogue5(Mine_Page.this, R.style.mydialogue);
                m1.show();
            }
        });
        setting00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My_Dialogue5 m1 = new My_Dialogue5(Mine_Page.this, R.style.mydialogue);
                m1.show();
            }
        });
        setting001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My_Dialogue5 m1 = new My_Dialogue5(Mine_Page.this, R.style.mydialogue);
                m1.show();
            }
        });
        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My_Dialogue5 m1 = new My_Dialogue5(Mine_Page.this, R.style.mydialogue);
                m1.show();
            }
        });
        complaint1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My_Dialogue5 m1 = new My_Dialogue5(Mine_Page.this, R.style.mydialogue);
                m1.show();
            }
        });
    }


}
