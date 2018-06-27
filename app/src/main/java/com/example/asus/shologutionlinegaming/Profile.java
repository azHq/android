package com.example.asus.shologutionlinegaming;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class Profile extends Fragment {

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

    public static  final int Gallery_Pick=1;


    public Profile() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        myView=inflater.inflate(R.layout.fragment_profile, container, false);
        tUserName=(TextView)myView.findViewById(R.id.textView17);
        tPosition=(TextView)myView.findViewById(R.id.textView16);
        tRating=(TextView)myView.findViewById(R.id.textView18);
        tWin=(TextView)myView.findViewById(R.id.textView20);
        tLose=(TextView)myView.findViewById(R.id.textView19);
        tMatch=(TextView)myView.findViewById(R.id.textView21);
        tStatus=(TextView)myView.findViewById(R.id.textView22);
        circleImageView=(CircleImageView) myView.findViewById(R.id.circleImageView);

        accountSettings=(Button) myView.findViewById(R.id.button7);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        mAuth=FirebaseAuth.getInstance();

        FirebaseUser firebaseUser=mAuth.getCurrentUser();

        if(firebaseUser!=null){
            currentUserUID=firebaseUser.getUid();
            setAccountView(currentUserUID);

        }


        accountSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent tnt=new Intent(getContext(),AccountSettings.class);
                startActivity(tnt);
            }
        });



        return myView;
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
