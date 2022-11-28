package com.example.domesticnews;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

/**
 * show webpage
 */
public class News_Fragment extends Fragment {
    private WebView newsWebView;
    private String[] menus = {"1", "2", "3", "4", "5"};
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    private static News_Fragment instance = null;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        newsWebView = view.findViewById(R.id.newsWebview);

        tabLayout = view.findViewById(R.id.tab_layout1);
        viewPager = view.findViewById(R.id.view_pager1);


        ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new Listtab_Fragment());
        fragmentList.add(new Listtab_Fragment());
        fragmentList.add(new Listtab_Fragment());
        fragmentList.add(new Listtab_Fragment());
        fragmentList.add(new Listtab_Fragment());

        News_adapter news_adapter = new News_adapter(this,fragmentList);
        viewPager.setAdapter(news_adapter);
        tabLayout.setTabTextColors(R.color.black,R.color.teal_700);
        tabLayout.setTabRippleColor(ColorStateList.valueOf(Color.TRANSPARENT));

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(menus[position]);
            }
        }).attach();

        return view;

    }

    public class News_adapter extends FragmentStateAdapter{
        ArrayList<Fragment> fragmentArrayList;
        public News_adapter(News_Fragment fragmentActivity, ArrayList<Fragment> fragmentArrayList){
            super(fragmentActivity);
            this.fragmentArrayList = fragmentArrayList;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentArrayList.size();
        }
    }

    public static News_Fragment getInstance() {
        if (instance == null) {
            instance = new News_Fragment();
        }
        return instance;
    }


}
