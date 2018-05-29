package com.example.asus.shologutionlinegaming;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowInfo extends AppCompatActivity {
    String s;
    DatabaseHelper DH;

    TextView t1,t2,t3,t4;
    TextView[] text={t1,t2,t3,t4};
    EditText et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);



        text[0]=(TextView) findViewById(R.id.textView4);
        text[1]=(TextView) findViewById(R.id.textView5);
        text[2]=(TextView) findViewById(R.id.textView6);
        text[3]=(TextView) findViewById(R.id.textView9);






        DH=new DatabaseHelper(this);

        ArrayList<String> arrayList=new ArrayList<>();
        Cursor data=DH.getListContents();

        if(data.getCount()==0){

            Toast.makeText(ShowInfo.this,"Database was empty :(.",Toast.LENGTH_LONG).show();

        }
        else{
            while(data.moveToNext()){

                arrayList.add(data.getString(1));
                //ListAdapter listadapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
                //listView.setAdapter(listadapter);

                //System.out.println(data.getCount()+"hsddddddddddddddddddddddddddddddddddddddddddddddddddddddjssssssssssssssssssssssssssss");

            }
            for(int i=0;i<arrayList.size();i++){


                if(i<text.length) {

                   // System.out.println(arrayList.get(i));
                    String[] s=arrayList.get(i).split(",");

                    int num=Integer.parseInt(s[2]);

                   int  hours=(int)((num/1000)/3600);
                  int  minutes=(int)((num/1000)/60);
                   int  seconds=(int)((num/1000)%60);

                  String  time=String.format("%02d:%02d:%02d\n",hours,minutes,seconds);

                    text[i].setText(s[0]+"                      "+s[1]+"                "+time);
                }
                else{
                    Toast.makeText(ShowInfo.this,"Your input exceed the input limit.",Toast.LENGTH_LONG).show();
                    i=0;
                }

            }
            arrayList.removeAll(arrayList);
            if(!arrayList.isEmpty()) {

                System.out.println("helloooooooooooooooooooooooooooooooooo");

            }


        }
    }
}
