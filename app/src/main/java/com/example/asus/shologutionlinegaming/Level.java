package com.example.asus.shologutionlinegaming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level extends AppCompatActivity {

    Button but1,but2,but3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        but1=(Button) findViewById(R.id.button1);
        but2=(Button) findViewById(R.id.button2);
        but3=(Button) findViewById(R.id.button3);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent t=new Intent(Level.this,EasyLevel1.class);
                startActivity(t);

            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent t=new Intent(Level.this,MediumLevel1.class);
                startActivity(t);
            }
        });


        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent t=new Intent(Level.this,HardLevel1.class);
                startActivity(t);
            }
        });


    }
}
