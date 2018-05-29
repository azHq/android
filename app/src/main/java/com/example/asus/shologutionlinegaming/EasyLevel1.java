package com.example.asus.shologutionlinegaming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EasyLevel1 extends AppCompatActivity {

    EasyLevel2 touch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        touch = new EasyLevel2(this);
        //touch.setBackground(Color);
        setContentView(touch);


    }


    @Override
    protected void onResume() {

        super.onResume();
        touch.resume();

    }

    @Override
    protected void onPause() {

        super.onPause();
        touch.pause();

    }

}