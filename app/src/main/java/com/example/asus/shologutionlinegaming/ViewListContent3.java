
package com.example.asus.shologutionlinegaming;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewListContent3 extends AppCompatActivity {

    String s;
    DatabaseHelper DH;

    TextView t1,t2,t3,t4;
    TextView[] text={t1,t2,t3,t4};
    EditText et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_content3);
       // s=getIntent().getStringExtra("azaz");

        System.out.println("azaz4"+s);


        // ListView listView=(ListView) findViewById(R.id.dynamic);



        text[1]=(TextView) findViewById(R.id.textView4);
        text[2]=(TextView) findViewById(R.id.textView5);
        text[3]=(TextView) findViewById(R.id.textView6);
        text[4]=(TextView) findViewById(R.id.textView9);






        DH=new DatabaseHelper(this);

        ArrayList<String> arrayList=new ArrayList<>();
        Cursor data=DH.getListContents();

        if(data.getCount()==0){

            Toast.makeText(ViewListContent3.this,"Database was empty :(.",Toast.LENGTH_LONG).show();

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

                    System.out.println(arrayList.get(i)+i);
                    text[i].setText(arrayList.get(i));
                }
                else{
                    Toast.makeText(ViewListContent3.this,"Your input exceed the input limit.",Toast.LENGTH_LONG).show();
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

