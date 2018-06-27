package com.example.asus.shologutionlinegaming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersProfile extends AppCompatActivity {

    View myView;
    TextView tUserName,tPosition,tRating,tWin,tLose,tMatch,tDraw,tStatus;
    String userName,position,rating,win,lose,match,draw,image,status;
    Button accountSettings;
    CircleImageView circleImageView;

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    String currentUserUID;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference(),userReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_profile);

        String id=getIntent().getExtras().get("ID").toString();


        tUserName=(TextView)findViewById(R.id.textView17);
        tPosition=(TextView)findViewById(R.id.textView16);
        tRating=(TextView)findViewById(R.id.textView18);
        tWin=(TextView)findViewById(R.id.textView20);
        tLose=(TextView)findViewById(R.id.textView19);
        tMatch=(TextView)findViewById(R.id.textView21);
        tStatus=(TextView)findViewById(R.id.textView22);
        circleImageView=(CircleImageView)findViewById(R.id.circleImageView);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        mAuth=FirebaseAuth.getInstance();

        setAccountView(id);
    }



    public void setAccountView(String id){

        userReference=databaseReference.child("Users").child(id);

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                tUserName.setText(dataSnapshot.child("userName").getValue().toString());
                tStatus.setText(dataSnapshot.child("status").getValue().toString());
                //tUserName.setText(dataSnapshot.child("userName").getValue().toString());


                image=dataSnapshot.child("profile_image").getValue().toString();



                if(image!=null&&!image.equals("default_image")) Picasso.get().load(image).placeholder(R.drawable.azaz12).into(circleImageView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
