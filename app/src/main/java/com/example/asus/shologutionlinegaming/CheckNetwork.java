package com.example.asus.shologutionlinegaming;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CheckNetwork extends BroadcastReceiver{

    MediaPlayer player;


    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    String loginUserId,userName="",logUID;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;

    public  static boolean connection=false;
    @Override
    public void onReceive(final Context context, final Intent intent) {

       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        Toast.makeText(context,"setAlarm after 5 seconds",Toast.LENGTH_LONG).show();

       // player= MediaPlayer.create(context,Settings.System.DEFAULT_RINGTONE_URI);
        //player.setLooping(true);


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        databaseReference2=firebaseDatabase.getReference();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        mAuth=FirebaseAuth.getInstance();

        currentUser= mAuth.getCurrentUser();






        if(currentUser!=null) {

            logUID = currentUser.getUid();

            Log.d("Auth", "Sign users" + currentUser.getUid() + "email" + currentUser.getEmail());



            loginUserId = currentUser.getEmail();

            userName = convertEmailToStrong(loginUserId);

            databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(logUID);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    databaseReference.child("Activity").onDisconnect().setValue(false);
                    databaseReference.child("Activity").setValue(true);
                    //connection=true;


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




        }else{




        }









        if(checkInternet(context))
        {


            connection=true;






        }
        else{
            Toast.makeText(context, "No network Connection",Toast.LENGTH_LONG).show();
            connection=false;



            /*databaseReference2.keepSynced(true);
            databaseReference2.child("Users").child("Username").child("Activity").setValue("inactive");
            Log.d("Auth","user on offline");
            databaseReference2.child("Users").child("username").child("Activity").onDisconnect().setValue(false);*/

        }

    }

    boolean checkInternet(Context context) {
        ServiceManager serviceManager = new ServiceManager(context);
        if (serviceManager.isNetworkAvailable()) {
            return true;
        } else {
            return false;
        }
    }


    public String convertEmailToStrong(String email){

        String s=email.substring(0,email.indexOf("@"));

        s=s.replace(":","");

        return s;

    }
}
