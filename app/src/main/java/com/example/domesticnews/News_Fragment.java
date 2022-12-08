package com.example.domesticnews;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/**
 * show webpage
 */
public class News_Fragment extends Fragment {
    private WebView newsWebView;

    public static final String TAG = "News_Fragment";

    private static News_Fragment instance = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        newsWebView = view.findViewById(R.id.newsWebview);
        startTimer();
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    public static News_Fragment getInstance() {
        if (instance == null) {
            instance = new News_Fragment();
        }
        return instance;
    }

    private static final String[] TITLES = new String[]{"Suzhou Universal Postal Service Study","Suzhou City Culture, Radio, Film and Tourism Bureau 2019-2021 European and American Regional Promotion Results Launch"," Deloitte China has released ten outstanding cases of institutional innovation for the first anniversary of the Suzhou Free Trade Zone"};

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x01) {
                Random random = new Random();
                int index = random.nextInt(3);
                String id = UUID.randomUUID().toString();
                Notification notification = new Notification.Builder(getActivity(), id)
                        .setContentTitle("News Notification")
                        .setContentText(TITLES[index])
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .build();

                NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
                // Create NotificationChannel
                NotificationChannel channel = new NotificationChannel(id, "DomesticNews", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);

                notificationManager.notify((int) System.currentTimeMillis()/1000, notification);
            }
        }
    };

    private Timer timer;

    private void startTimer() {
        if (timer == null) {
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = 0x01;
                    mHandler.sendMessage(msg);
                }
            };
            timer.schedule(timerTask, 0, 20 * 60 * 1000);
        }
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


}
