package com.example.asus.shologutionlinegaming;

import android.content.Intent;
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

public class FriendRequest extends AppCompatActivity {

    View myView;
    TextView tUserName,tPosition,tRating,tWin,tLose,tMatch,tDraw,tStatus;
    String senderName,receiverName,position,rating,win,lose,match,draw,image,status;
    Button accountSettings;
    CircleImageView circleImageView;

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    public String receiverid;


    String currentUserUID;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference(),userReference;
    DatabaseReference databaseReference2=firebaseDatabase.getReference(),userReference2;

    Button cancel,accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);


        final String senderid=getIntent().getExtras().get("senderId").toString();
         receiverid=getIntent().getExtras().get("receiverId").toString();




        tUserName=(TextView)findViewById(R.id.textView17);
        tPosition=(TextView)findViewById(R.id.textView16);
        tRating=(TextView)findViewById(R.id.textView18);
        tWin=(TextView)findViewById(R.id.textView20);
        tLose=(TextView)findViewById(R.id.textView19);
        tMatch=(TextView)findViewById(R.id.textView21);
        tStatus=(TextView)findViewById(R.id.textView22);
        circleImageView=(CircleImageView)findViewById(R.id.circleImageView);

        cancel=(Button) findViewById(R.id.button9);
        accept=(Button) findViewById(R.id.button13);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        mAuth=FirebaseAuth.getInstance();

        setAccountView(senderid);


        userReference2=databaseReference2.child("Users").child(receiverid);

        userReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                receiverName=dataSnapshot.child("userName").getValue().toString();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.child("play").child(senderid+":"+receiverid).child("state").setValue("cancel");


            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.child("play").child(senderid+":"+receiverid).child("state").setValue("accept");



                    startGame(senderid+":"+receiverid,senderName+":"+receiverName,receiverName,"To");





            }
        });




    }


    public void startGame(String idPair,String playerGameId,String otherplayer,String type){

        databaseReference.child("play").child(idPair).child(playerGameId).removeValue();

        Intent t=new Intent(getApplicationContext(),OnlineGameActivity.class);
        t.putExtra("idPair",idPair);
        t.putExtra("player_session",playerGameId);
        t.putExtra("user_name",senderName);
        t.putExtra("other_player",otherplayer);
        t.putExtra("login_uid",receiverid);
        t.putExtra("request_type",type);

        startActivity(t);

        finish();

    }



    public void setAccountView(String id){

        userReference=databaseReference.child("Users").child(id);

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                senderName=dataSnapshot.child("userName").getValue().toString();
                tUserName.setText(senderName);
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
