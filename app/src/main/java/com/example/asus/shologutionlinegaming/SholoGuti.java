package com.example.asus.shologutionlinegaming;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SholoGuti extends AppCompatActivity {

    Button but1,but2,but3,but4,but5,but6;

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    String loginUserId,userName="",logUID;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;

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

                Toast.makeText(getApplicationContext(), "Online activity ",Toast.LENGTH_LONG).show();

                System.out.println(CheckNetwork.connection);
                CheckNetwork ch=new CheckNetwork();

                if(CheckNetwork.connection||MainActivity.connection2) {



                      if(currentUsers()){
                          Intent intent = new Intent(SholoGuti.this, Fragments.class);
                          startActivity(intent);
                      }
                      else{

                          Intent t=new Intent(getApplicationContext(),SignUpForm.class);
                          startActivity(t);
                      }


                }else{



                    android.app.AlertDialog.Builder builder=new  android.app.AlertDialog.Builder(SholoGuti.this);

                    builder.setTitle("No Connection");
                    builder.setMessage("You need to have data or wifi connection to access this.Press ok to exit");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            /*Intent t=new Intent(getApplicationContext(),SholoGuti.class);
                            startActivity(t);*/
                            // finish();

                        }

                    }).create();

                    builder.show();


                }


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

    public boolean currentUsers(){


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        databaseReference2=firebaseDatabase.getReference();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        mAuth=FirebaseAuth.getInstance();

        currentUser= mAuth.getCurrentUser();



        if(currentUser!=null){

                    /*logUID=currentUser.getUid();

                    Log.d("Auth","Sign users"+currentUser.getUid()+"email"+currentUser.getEmail());

                    loginUserId=currentUser.getEmail();*/

                   return true;

        }
        else{

            return false;


        }



    }
}
