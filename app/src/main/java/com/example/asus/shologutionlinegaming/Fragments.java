package com.example.asus.shologutionlinegaming;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Fragments extends AppCompatActivity {


    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabsPagerAdapter tabsPagerAdapter;


    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    public String userId,userName,email,password;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        viewPager=(ViewPager)findViewById(R.id.viewpager);
        tabsPagerAdapter=new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsPagerAdapter);

        tabLayout=(TabLayout)findViewById(R.id.tabbar);
        tabLayout.setupWithViewPager(viewPager);

        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SholoGuti");



        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAuth=FirebaseAuth.getInstance();


        //taking users

       /* authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser currentUser = mAuth.getCurrentUser();

                if(currentUser!=null){


                    viewPager=(ViewPager)findViewById(R.id.viewpager);
                    tabsPagerAdapter=new TabsPagerAdapter(getSupportFragmentManager());
                    viewPager.setAdapter(tabsPagerAdapter);

                    tabLayout=(TabLayout)findViewById(R.id.tabbar);
                    tabLayout.setupWithViewPager(viewPager);

                    toolbar=(Toolbar)findViewById(R.id.app_bar);
                    setSupportActionBar(toolbar);
                    getSupportActionBar().setTitle("SholoGuti");



                    userId=currentUser.getUid();

                    Log.d("Auth","Sign users"+currentUser.getUid()+"email"+currentUser.getEmail());

                    email=currentUser.getEmail();




                    /*userName=convertEmailToStrong(loginUserId);

                    databaseReference.child("Users").child(userName).child("request").setValue(loginUserId);

                    requestUserAdapter.clear();

                    acceptIncomingUsers();


                }
                else{

                    Log.d("log in","Auth failed");


                }
            }
        };*/



    }



    /*@Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(authStateListener);
    }


    @Override
    public void onStop() {
        super.onStop();
        // Check if user is signed in (non-null) and update UI accordingly.
        if(authStateListener!=null) mAuth.removeAuthStateListener(authStateListener);
    }*/




}
