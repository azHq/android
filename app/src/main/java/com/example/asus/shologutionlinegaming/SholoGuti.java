package com.example.asus.shologutionlinegaming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SholoGuti extends AppCompatActivity {

    Button but1,but2,but3,but4,but5,but6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sholo_guti);

        but1=(Button)findViewById(R.id.button1);
        but2=(Button)findViewById(R.id.button2);
        but3=(Button)findViewById(R.id.button3);
        but4=(Button)findViewById(R.id.button4);
        but5=(Button)findViewById(R.id.button5);
        but6=(Button)findViewById(R.id.button6);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent t=new  Intent(SholoGuti.this,Level.class);
                startActivity(t);

            }
        });


        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(SholoGuti.this,TwoPlayerMode.class);

                startActivity(intent);


            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(SholoGuti.this,OnlineLogInActivity.class);

                startActivity(intent);


            }
        });

        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(SholoGuti.this,ShowInfo.class);

                startActivity(intent);


            }
        });

        but5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent=new Intent(SholoGuti.this,Help.class);

                startActivity(intent);


            }
        });

        but6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             /*   String s;
                System.exit(0);*/

                finish();



            }
        });






    }
}
