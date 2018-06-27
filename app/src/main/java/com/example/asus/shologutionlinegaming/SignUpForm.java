package com.example.asus.shologutionlinegaming;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class SignUpForm extends AppCompatActivity {

    Toolbar toolbar;

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    public String userId,userName,email,password;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference();

    EditText etUserName,etEmail,etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);

        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SholoGuti");

        etUserName=(EditText)findViewById(R.id.editText3);
        etEmail=(EditText)findViewById(R.id.editText5);
        etPassword=(EditText)findViewById(R.id.editText4);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAuth=FirebaseAuth.getInstance();


    }

    public void signUp(View v){

        userName=etUserName.getText().toString();
        email=etEmail.getText().toString();
        password=etPassword.getText().toString();

        registration(userName,email,password);



    }

    public void cancel(View v){

        Intent t=new Intent(getApplicationContext(),SholoGuti.class);
        startActivity(t);
       // finish();

    }


    public void registration(final String userName, final String email, final String password){


        System.out.println(userName+email+password);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("new user", "createUserWithEmail:success");
                            FirebaseUser currentUser= mAuth.getCurrentUser();

                            String deviceToker= FirebaseInstanceId.getInstance().getToken();
                            userId=currentUser.getUid();
                            databaseReference.child("Users").child(userId).child("userName").setValue(userName);
                            databaseReference.child("Users").child(userId).child("email").setValue(email);
                            databaseReference.child("Users").child(userId).child("password").setValue(password);
                            databaseReference.child("Users").child(userId).child("status").setValue("status");
                            databaseReference.child("Users").child(userId).child("Activity").setValue(true);
                            databaseReference.child("Users").child(userId).child("profile_image").setValue("default_image");
                            databaseReference.child("Users").child(userId).child("thumb_image").setValue("default_image");
                            databaseReference.child("Users").child(userId).child("position").setValue(0);
                            databaseReference.child("Users").child(userId).child("rating").setValue(0);
                            databaseReference.child("Users").child(userId).child("wins").setValue(0);
                            databaseReference.child("Users").child(userId).child("loses").setValue(0);
                            databaseReference.child("Users").child(userId).child("matches").setValue(0);
                            databaseReference.child("Users").child(userId).child("device_token").setValue(deviceToker);




                            Intent t=new Intent(getApplicationContext(),Fragments.class);
                            startActivity(t);



                        } else {

                            Log.w("new user", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });


    }
}
