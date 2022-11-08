package com.example.domesticnews;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //城市名，需要赋值
    private String city = "城市（可修改）";

    private Toolbar toolbar;
    private DrawerLayout drawer;

    //search view toolbar菜单栏搜索
    SearchView.SearchAutoComplete mSearchAutoComplete;
    SearchView mSearchView;
    String TAG = "MainActivity";

    //获取当前时间和日期
    Date newDate = new Date(System.currentTimeMillis());
    String allshijian = newDate.toString();
    String riqi = allshijian.split(" ")[1] + " " + allshijian.split(" ")[2] + " " + allshijian.split(" ")[5];
    String shijian = allshijian.split(" ")[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);

        //接受城市名字，并显示出来
        toolbar.setTitle(city);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        //监听侧边栏
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //添加toolbar 右侧的菜单
        toolbar.inflateMenu(R.menu.switch_city);

        //侧边栏按钮
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.cebianlan_open, R.string.cebianlan_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    

    @Override
    // 侧边栏打开后各个item点击效果
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.night_mode:
                break;
            case R.id.setting:
                break;
            case R.id.huancun:
                break;
            case R.id.rili:
                Toast.makeText(this, riqi, Toast.LENGTH_SHORT).show();
                break;
            case R.id.shijian:
                Toast.makeText(this, shijian, Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //侧边栏按钮打开侧边栏
    public void OnBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //菜单栏得以显示
        getMenuInflater().inflate(R.menu.switch_city, menu);
        //菜单栏是用于改变城市的菜单栏
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_switch_city:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("选择城市");
                builder.setIcon(R.drawable.location);
                final String itemsId[] = {"苏州", "无锡", "常州", "南京", "宿迁"};
                final boolean []checkedItems=new boolean[]{false,false,false,false,false};
                builder.setMultiChoiceItems(itemsId, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedItems[which]=isChecked;
                    }
                });
                builder.setPositiveButton("确定", null);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setCancelable(false);
                final AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("checkedItems", "checkedItems: "+checkedItems);
                        String text="";
                        boolean hasSelected=false;
                        for(int i=0;i<itemsId.length;i++)
                        {
                            text+=checkedItems[i]?itemsId[i]+",":"";
                            if (checkedItems[i]){
                                hasSelected = checkedItems[i];
                                toolbar.setTitle(itemsId[i]);
                                break;
                            }
                        }
                        if (hasSelected) {
                            Toast.makeText(MainActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "还未选择！", Toast.LENGTH_SHORT).show();
                            return;
                        }


                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);


    }




    }
