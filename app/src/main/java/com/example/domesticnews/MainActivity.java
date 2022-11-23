package com.example.domesticnews;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //城市名，需要赋值
    private final String CITY = "City";
    // 标题栏和侧边栏
    private Toolbar toolbar;
    private DrawerLayout drawer;

    private WebView newsWebView;


    //search view toolbar菜单栏搜索
    SearchView.SearchAutoComplete mSearchAutoComplete;
    SearchView mSearchView;
    String TAG = "MainActivity";

    //获取当前时间和日期
    Date newDate = new Date(System.currentTimeMillis());
    String allshijian = newDate.toString();
    String riqi = allshijian.split(" ")[1] + " " + allshijian.split(" ")[2] + " " + allshijian.split(" ")[5];
    String shijian = allshijian.split(" ")[3];

    //获取定位
    TextView locationText;
    TextView locationTextByManager;

    protected LocationManager locationManager;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);

        //获取定位
        locationText = (TextView) findViewById(R.id.textView111);
        locationTextByManager = (TextView) findViewById(R.id.textView222);

        //接受城市名字，并显示出来
        toolbar.setTitle(CITY);
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

        newsWebView = findViewById(R.id.newsWebview);
    }


    @Override
    // 侧边栏打开后各个item点击效果
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.night_mode:
                int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if (mode == Configuration.UI_MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                recreate();
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
                builder.setTitle("Choose City");
                builder.setIcon(R.drawable.location);
                final String itemsId[] = {"苏州", "无锡", "常州", "南京", "宿迁"};
                final boolean[] checkedItems = new boolean[]{false, false, false, false, false};
                builder.setMultiChoiceItems(itemsId, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedItems[which] = isChecked;
                    }
                });
                builder.setPositiveButton("Yes", null);
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
                        Log.d("checkedItems", "checkedItems: " + checkedItems);
                        String text = "";
                        boolean hasSelected = false;
                        for (int i = 0; i < itemsId.length; i++) {
                            text += checkedItems[i] ? itemsId[i] + "," : "";
                            if (checkedItems[i]) {
                                hasSelected = checkedItems[i];
                                toolbar.setTitle(itemsId[i]);
                                break;
                            }
                        }
                        if (hasSelected) {
                            Toast.makeText(MainActivity.this, "Submit Successfully！", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity.this, "Need a choice！", Toast.LENGTH_SHORT).show();
                            return;
                        }


                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);


    }


    // 获取定位
    public void getLastKnownLocation(View view) {


        Location location = null;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            List<String> providers = locationManager.getProviders(true);
            for (String provider : providers) {
                Log.d("providers", provider);
                Location l = locationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (location == null || l.getAccuracy() < location.getAccuracy()) {
                    // Found best last known location: %s", l);
                    location = l;
                }
            }

            //location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location != null) {


                String locationString = location.getLatitude() + "," + location.getLongitude();

                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = null;
                if (connMgr != null) {
                    networkInfo = connMgr.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        new FetchAddress(toolbar).execute(locationString);
                        drawer.closeDrawer(GravityCompat.START);


                    }

                }


            } else {
                toolbar.setTitle("Need GPS!");
                Toast.makeText(MainActivity.this, "Need GPS", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);
            }


        } else {
            requestLocationPermission();
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            new android.app.AlertDialog.Builder(this).setTitle("Permission needed")
                    .setMessage("Need Permission")
                    .setPositiveButton(R.string.ok, (dialogInterface, i) -> ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION
                    }, MY_PERMISSIONS_REQUEST_LOCATION_CODE))
                    .setNegativeButton("cancel", (dialogInterface, i) -> dialogInterface.dismiss())

                    .create()
                    .show();

        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, MY_PERMISSIONS_REQUEST_LOCATION_CODE);
        }


    }


}
