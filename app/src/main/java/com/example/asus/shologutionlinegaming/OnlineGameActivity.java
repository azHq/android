package com.example.asus.shologutionlinegaming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OnlineGameActivity extends AppCompatActivity {

    OnlineGameActivity2 touch;

    public String playerSession=" ";
    public String otherPlayer=" ";
    public String playerName=" ";
    public String loginUID=" ";
    public String requestType=" ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playerName=getIntent().getExtras().get("user_name").toString();
        loginUID=getIntent().getExtras().get("login_uid").toString();
        otherPlayer=getIntent().getExtras().get("other_player").toString();
        requestType=getIntent().getExtras().get("request_type").toString();
        playerSession=getIntent().getExtras().get("player_session").toString();



        touch = new OnlineGameActivity2(this,playerName,loginUID,otherPlayer,requestType,playerSession);
        //touch.setBackground(Color);
        setContentView(touch);

    }

    @Override
    protected void onResume(){

        super.onResume();
        touch.resume();

    }

    @Override
    protected void onPause(){

        super.onPause();
        touch.pause();

    }
}
