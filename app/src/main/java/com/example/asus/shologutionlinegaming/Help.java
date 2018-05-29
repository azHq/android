package com.example.asus.shologutionlinegaming;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        text=( TextView) findViewById(R.id.textView7);
        text.setText("This game is played between 2 person.Each having 16 Pawns. These pawns can move one step forward on the valid positions of the cort.If a player can cross a pawn of the other side then the player will achieve 1 point. In this way whoever manages to achieve 16 points will be the winner.But winning point could be declared at the beginning of the game.");
    }
}
