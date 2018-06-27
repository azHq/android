package com.example.asus.shologutionlinegaming;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class Different_fragment extends AppCompatActivity {


    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabsPagerAdapter tabsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       /* viewPager=(ViewPager)findViewById(R.id.viewpager);
        tabsPagerAdapter=new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsPagerAdapter);

        /*tabLayout=(TabLayout)findViewById(R.id.tabbar);
        tabLayout.setupWithViewPager(viewPager);

        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SholoGuti");*/



    }
}
