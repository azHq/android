package com.example.asus.shologutionlinegaming;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class OnlineLogInActivity extends AppCompatActivity {


    ListView loginUsers;
    ArrayList<String> list_loginUsers=new ArrayList<>();
    ArrayAdapter loginUserAdapter;


    ListView requestUsers;
    ArrayList<String> requestUsersList=new ArrayList<>();
    ArrayAdapter requestUserAdapter;

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    TextView userId,sendRequest,acceptRequest;
    String loginUserId,userName,logUID;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference();


    Button login;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_log_in);

       /* Intent t=new Intent(OnlineLogInActivity.this,Fragments.class);
        startActivity(t);*/



        userId=(TextView)findViewById(R.id.userId);
        sendRequest=(TextView)findViewById(R.id.sendRequest);
        acceptRequest=(TextView)findViewById(R.id.AcceptRequest);

        sendRequest.setText("please wait.......");
        acceptRequest.setText("please wait.......");




        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAuth=FirebaseAuth.getInstance();






        loginUsers=(ListView)findViewById(R.id.LoginUsers);

        loginUserAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,list_loginUsers);
        loginUsers.setAdapter(loginUserAdapter);

        requestUsers=(ListView) findViewById(R.id.RequestedUsers);
        requestUserAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,requestUsersList);
        requestUsers.setAdapter(requestUserAdapter);





        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser currentUser = mAuth.getCurrentUser();

                if(currentUser!=null){

                    logUID=currentUser.getUid();

                    Log.d("Auth","Sign users"+currentUser.getUid()+"email"+currentUser.getEmail());

                    loginUserId=currentUser.getEmail();

                    userId.setText(loginUserId);

                    userName=convertEmailToStrong(loginUserId);

                    databaseReference.child("Users").child(userName).child("request").setValue(loginUserId);

                    requestUserAdapter.clear();

                    acceptIncomingUsers();


                }
                else{

                    Log.d("log in","Auth failed");

                    joinOnlineGame();
                }
            }
        };


        databaseReference.getRoot().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                updateUsers(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        loginUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final String requestToUser=((TextView)view).getText().toString();

                confirmRequest(requestToUser,"To");

            }
        });

        requestUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final String requestFromUser=((TextView)view).getText().toString();

                confirmRequest(requestFromUser,"From");

            }
        });






    }

    public void confirmRequest(final String otherPlayer,final String type){

        AlertDialog.Builder b=new AlertDialog.Builder(this);

        LayoutInflater inflater=this.getLayoutInflater();
        final View v=inflater.inflate(R.layout.connectedplayer,null);

        b.setView(v);

        b.setTitle("Start game???");
        b.setMessage("connect with "+otherPlayer);

        b.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                databaseReference.child("Users").child(otherPlayer).child("request").push().setValue(loginUserId);


                if(type.equalsIgnoreCase("from")){

                    startGame(otherPlayer+":"+userName,otherPlayer,"from");
                }
                else{

                    startGame(userName+":"+otherPlayer,otherPlayer,"To");
                }
            }
        });

        b.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });

        b.show();

    }

    public void startGame(String playerGameId,String otherplayer,String type){

        databaseReference.child("playing").child(playerGameId).removeValue();

        Intent t=new Intent(getApplicationContext(),OnlineGameActivity.class);
        t.putExtra("player_session",playerGameId);
        t.putExtra("user_name",userName);
        t.putExtra("other_player",otherplayer);
        t.putExtra("login_uid",logUID);
        t.putExtra("request_type",type);

        startActivity(t);

        finish();

    }




    public void updateUsers(DataSnapshot dataSnapshot){

        String key=" ";

        Set<String> set=new HashSet<>();

        Iterator i=dataSnapshot.getChildren().iterator();

        while(i.hasNext()){

            key=((DataSnapshot) i.next()).getKey();

            if(!key.equalsIgnoreCase(userName)){

                set.add(key);
            }


        }

        loginUserAdapter.clear();

        loginUserAdapter.addAll(set);
        loginUserAdapter.notifyDataSetChanged();

        sendRequest.setText("Send request to...");
        acceptRequest.setText("Accept request from...");


    }


    public void acceptIncomingUsers(){

        databaseReference.child("Users").child(userName).child("request").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    HashMap<String, Object> hashMap = (HashMap<String, Object>) dataSnapshot.getValue();


                    if (hashMap != null) {

                        String value = " ";

                        for (String key : hashMap.keySet()) {

                            value = (String) hashMap.get(key);

                            requestUserAdapter.add(convertEmailToStrong(value));
                            requestUserAdapter.notifyDataSetChanged();

                            databaseReference.child("Users").child(userName).child("request").setValue(loginUserId);

                        }


                    }
                }catch(Exception e){


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    public String convertEmailToStrong(String email){

        String s=email.substring(0,email.indexOf("@"));

        s=s.replace(":","");

        return s;

    }


    public void joinOnlineGame(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        LayoutInflater inflater=this.getLayoutInflater();

        final View  v=inflater.inflate(R.layout.log_in,null);

        builder.setView(v);

        final EditText etEmail=(EditText) v.findViewById(R.id.editText);
       // final EditText epassword=(EditText) v.findViewById(R.id.editText2);

        builder.setTitle("Registration");
        builder.setMessage("Enter your email and password.");
        builder.setPositiveButton("register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //registration(etEmail.getText().toString(),epassword.getText().toString());
            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent t=new Intent(getApplicationContext(),SholoGuti.class);
                startActivity(t);
                finish();
            }
        });

        builder.show();


    }

    public void registration(String email,String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("new user", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {

                            Log.w("new user", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });


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


    public void registeredUsers(String email,String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("register", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {

                            Log.w("Register", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });

    }
}
