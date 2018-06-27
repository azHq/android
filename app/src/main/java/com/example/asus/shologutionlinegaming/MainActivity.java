package com.example.asus.shologutionlinegaming;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    public static boolean connection2;
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
        setContentView(R.layout.activity_main);


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        databaseReference2=firebaseDatabase.getReference();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        mAuth=FirebaseAuth.getInstance();

        currentUser= mAuth.getCurrentUser();



        checkConnection();
        Intent t=new Intent(getApplicationContext(),CheckNetwork.class);
        PendingIntent pi=PendingIntent.getBroadcast(getApplicationContext(),0,t,0);



        AlarmManager alarm=(AlarmManager) getSystemService(ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000,1000,pi);

        Toast.makeText(this,"setAlarm after 5 seconds",Toast.LENGTH_LONG).show();






        Thread thread = new Thread() {

            public void run() {

                try {
                    sleep(1000);
                } catch (InterruptedException e) {

                }

                Intent t = new Intent(getApplicationContext(), SholoGuti.class);
                startActivity(t);


            }


        };

        thread.start();

    }


    public void checkConnection() {

        if (currentUser != null) {

            connection2=true;


            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {







                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        } else {

            connection2=false;

        }
    }


}
