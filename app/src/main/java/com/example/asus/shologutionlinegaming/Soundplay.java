package com.example.asus.shologutionlinegaming;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Soundplay {
    public SoundPool soundpool;
    public int hitsound;
    public Soundplay(Context context){

        //soundpool(int maxstream,int streamType,int srcQuility)
        soundpool=new SoundPool(2, AudioManager.STREAM_MUSIC,0);

        hitsound=soundpool.load(context,R.raw.hit,1);
    }

    public void hitSound(){

        soundpool.play(hitsound,1.0f,1.0f,1,0,1.0f);

    }
}
