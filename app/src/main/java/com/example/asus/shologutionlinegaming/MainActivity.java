package com.example.asus.shologutionlinegaming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread t=new Thread(){

            public void run(){

                try{
                    sleep(1000);
                }
                catch(InterruptedException e){

                }

                Intent t=new Intent(getApplicationContext(),SholoGuti.class);
                startActivity(t);


            }


        };

        t.start();

    }
}
