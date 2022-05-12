package com.example.intropagejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    IntroViewPagerAdapter adapter;
    TabLayout tabIndicator;
    Button btnSkip;
    Button btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        btnSkip = findViewById(R.id.btn_skip);
        btnGetStarted = findViewById(R.id.btn_get_started);

        tabIndicator = findViewById(R.id.tab_indicator);

        final List<ScreenItem> list = new ArrayList<>();
        list.add(new ScreenItem("Lorem Ipsum", "Lorem Ipsum is simply dummy text of the printing and typesetting\nindustry. Lorem Ipsum has been the industry's", R.raw.first));
        list.add(new ScreenItem("Lorem Ipsum", "Lorem Ipsum is simply dummy text of the printing and typesetting\nindustry. Lorem Ipsum has been the industry's", R.raw.second));
        list.add(new ScreenItem("Lorem Ipsum", "Lorem Ipsum is simply dummy text of the printing and typesetting\nindustry. Lorem Ipsum has been the industry's", R.raw.three));


        viewPager = findViewById(R.id.screen_viewpager);
        adapter = new IntroViewPagerAdapter(this, list);

        viewPager.setAdapter(adapter);

        tabIndicator.setupWithViewPager(viewPager);

        btnSkip.setOnClickListener(view -> viewPager.setCurrentItem(list.size()));

        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == list.size() - 1) {
                    loadLastScreen();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnGetStarted.setOnClickListener(view -> {
            Intent mainActivity = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(mainActivity);
            finish();
        });
    }

    private void loadLastScreen() {
        btnSkip.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
    }
}