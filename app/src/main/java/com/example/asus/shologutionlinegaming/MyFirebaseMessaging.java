package com.example.asus.shologutionlinegaming;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;


public class MyFirebaseMessaging extends FirebaseMessagingService{

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    String senderId,senderName="",receiverName="",receiverId;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;




    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);



        /*String title=remoteMessage.getNotification().getTitle();
        String body=remoteMessage.getNotification().getBody();*/

         senderId=remoteMessage.getData().get("from_sender_id");

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        databaseReference2=firebaseDatabase.getReference();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(MyFirebaseMessaging.this);
        mAuth=FirebaseAuth.getInstance();
        currentUser= mAuth.getCurrentUser();
         if(currentUser!=null) receiverId=currentUser.getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                System.out.println("receiver name: "+receiverId+" sender name: "+senderId);

                receiverName=dataSnapshot.child("Users").child(receiverId).child("userName").getValue().toString();
                senderName=dataSnapshot.child("Users").child(senderId).child("userName").getValue().toString();




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Intent tnt=new Intent(this,FriendRequest.class);

        tnt.putExtra("senderId",senderId);
        tnt.putExtra("receiverId",receiverId);

        PendingIntent pn=PendingIntent.getActivity(this,0,tnt,PendingIntent.FLAG_UPDATE_CURRENT);



        Intent snoozeIntent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);


        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mbuilder=new NotificationCompat.Builder(MyFirebaseMessaging.this);
        mbuilder.setContentTitle("friend request");
        mbuilder.setContentText(senderName+" send you a friend request");
        mbuilder.setSmallIcon(R.drawable.azaz7);
        mbuilder.setContentIntent(pn);
        mbuilder.setColor(getResources().getColor(R.color.colorPrimary));
        mbuilder.setLights(Color.WHITE,1000,5000);

        mbuilder.setVibrate(new long[]{0,300,300,300} );
        mbuilder.setSound(defaultSoundUri);




        int notificationId=(int) System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, mbuilder.build());






    }


}
