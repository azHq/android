package com.example.asus.shologutionlinegaming;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class OnlineGameActivity2 extends SurfaceView implements View.OnTouchListener,Runnable{

    private int[][] arr= new int[37][3];
    private int[][] position=new int[37][3];

    private int x=940,y=485;  //940,485,

    private int delay=16;
    private int f=1,g,h=1,a;
    private String team1="azaz",team2="bablu";
    private int totalpawn1=16,totalpawn2=16;

    public boolean Atflag=false;

    public boolean playGame=true;


    int[][] path=new int[37][8];

    int[][] bestWay=new int[8][2];
    int[][] firstTwoMax=new int[2][2];

    private boolean mouse,team1Move=true,team2Move=true;
    int mouseClickCount=0;

    private int mouseTemp;

    public int source, destination, AX, AY, BX, BY;
    public boolean drawLine2=false;




    ArrayList<Integer> arraylist=new  ArrayList<Integer>();






    private int A=-100,B=-100;

    boolean click=false;






    public int height;
    public int width;

    public int eachRoomHeight;
    public int eachRoomWidth;

    public Bitmap bmp;
    public Context context;
    public boolean flag=false;

    SurfaceHolder holder;
    boolean isItok=false;

    public int temp=0;

    Thread thread;


    Paint paint;

    public Bitmap bitmap;




    Soundplay soundplay;


    int minutes=0,seconds=0,hours=0;
    long lefttime=86400000;
    boolean timerIsRunning=true;
    CountDownTimer timer;

    public long timeCounter=0;

    public String time;

    DatabaseHelper DH;

    public String playerSession;
    public String otherPlayer;
    public String userName;
    public String loginUID;
    public String requestType;
    public String idPair;


    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference();


    public OnlineGameActivity2(Context context,String UserName,String LoginUID,String OtherPlayer,String RequestType,String PlayerSession,String idPair){

        super(context);

        this.context=context;
        userName=UserName;
        loginUID=LoginUID;
        requestType=RequestType;
        playerSession=PlayerSession;
        otherPlayer=OtherPlayer;
        this.idPair=idPair;




        if(requestType.equalsIgnoreCase("To")){

            team1Move=true;
            team2Move=false;

            databaseReference.child("play").child(idPair).child(playerSession).child("turn").setValue(otherPlayer);

        }
        else{

            team1Move=false;
            team2Move=true;

            databaseReference.child("play").child(idPair).child(playerSession).child("turn").setValue(otherPlayer);

        }







        paint=new Paint();

        holder = getHolder();


        soundplay=new Soundplay(context);

        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.azaz6);

        DisplayMetrics metrics = new DisplayMetrics();

        // getWindowManager().getDefaultDisplay().getMetrics(metrics);

        width = Resources.getSystem().getDisplayMetrics().widthPixels;


        height = Resources.getSystem().getDisplayMetrics().heightPixels;




        int maxHeight = 2000;
        int maxWidth = 2000;
        float scale = Math.max(((float)height / bitmap.getWidth()), ((float)width / bitmap.getHeight()));

        Matrix matrix = new Matrix();
        matrix.postScale(scale-50, scale+10);


        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);



        // WindowManager w=getWindowManager();

       /* Display d = w.getDefaultDisplay();
        width = d.getWidth();
        height = d.getHeight();

         width = WindowManager.getWidth();
         height = getWindowManager().getDefaultDisplay().getHeight();*/

        System.out.println(width+" "+height);

        eachRoomWidth=width/6+20;
        eachRoomHeight=height/10+20;

        int posX,posY,count=0;

        for(int i=1;i<=9;i++) {
            for(int j=1;j<=5;j++) {

                posY=eachRoomHeight*(i-1)-10;
                posX=eachRoomWidth*j-60;

                if((i==1&&j!=2&&j!=4)||(i==2&&j!=1&&j!=5)||(i==8&&j!=1&&j!=5)||(i==9&&j!=2&&j!=4)||(i>=3&&i<=7)) {

                    position[count][0]=posX;
                    position[count][1]=posY+30;

                    if(count>=0&&count<=15) position[count][2]=1;
                    else if(count>=21&&count<=36) position[count][2]=2;
                    else position[count][2]=0;



                    //System.out.println("positionX:"+posX+"   positionY:"+posY+"   count:"+count);

                    count++;

                }


            }

        }

        //left
        position[0][0]+=50;
        position[0][1]+=50;

        position[1][0]+=1;
        position[1][1]+=50;


        position[2][0]-=50;
        position[2][1]+=50;



        //right
        position[34][0]+=50;
        position[34][1]-=50;

        position[35][0]+=1;
        position[35][1]-=50;

        position[36][0]-=50;
        position[36][1]-=50;

        for(int i=0;i<37;i++) {

            arr[i][0]=position[i][0];
            arr[i][1]=position[i][1];
            arr[i][2]=position[i][2];


        }




        //path

        for(int i=0;i<37;i++) {

            for(int j=0;j<8;j++) {

                path[i][j]=-1;

            }
        }

        //0
        temp=0;

        path[temp][5]=3;
        path[temp][6]=1;





        //1
        temp=1;
        path[temp][2]=0;
        path[temp][4]=4;
        path[temp][6]=2;




        //2
        temp=2;
        path[temp][2]=1;
        path[temp][3]=5;

        //3
        temp=3;
        path[temp][1]=0;
        path[temp][6]=4;
        path[temp][5]=8;



        //4
        temp=4;
        path[temp][0]=1;
        path[temp][2]=3;
        path[temp][4]=8;
        path[temp][6]=5;




        //5
        temp=5;
        path[temp][7]=2;
        path[temp][2]=4;
        path[temp][3]=8;





        //6
        temp=6;
        path[temp][6]=7;
        path[temp][4]=11;
        path[temp][5]=12;




        //7
        temp=7;
        path[temp][2]=6;
        path[temp][6]=8;
        path[temp][4]=12;





        //8
        temp=8;
        path[temp][0]=4;
        path[temp][1]=3;
        path[temp][2]=7;
        path[temp][3]=12;
        path[temp][4]=13;
        path[temp][5]=14;
        path[temp][6]=9;
        path[temp][7]=5;



        //9
        temp=9;
        path[temp][2]=8;
        path[temp][6]=10;
        path[temp][4]=14;





        //10
        temp=10;
        path[temp][2]=9;
        path[temp][3]=14;
        path[temp][4]=15;




        //11
        temp=11;
        path[temp][0]=6;
        path[temp][6]=12;
        path[temp][4]=16;





        //12
        temp=12;
        path[temp][0]=7;
        path[temp][1]=6;
        path[temp][2]=11;
        path[temp][3]=16;
        path[temp][4]=17;
        path[temp][5]=18;
        path[temp][6]=13;
        path[temp][7]=8;



        //complete



        //13
        temp=13;
        path[temp][0]=8;
        path[temp][2]=12;
        path[temp][6]=14;
        path[temp][4]=18;




        //14
        temp=14;
        path[temp][0]=9;
        path[temp][1]=8;
        path[temp][2]=13;
        path[temp][3]=18;
        path[temp][4]=19;
        path[temp][5]=20;
        path[temp][6]=15;
        path[temp][7]=10;




        //15
        temp=15;
        path[temp][0]=10;
        path[temp][2]=14;
        path[temp][4]=20;


        //16
        temp=16;
        path[temp][0]=11;
        path[temp][7]=12;
        path[temp][6]=17;
        path[temp][4]=21;
        path[temp][5]=22;




        //17
        temp=17;
        path[temp][0]=12;
        path[temp][2]=16;
        path[temp][6]=18;
        path[temp][4]=22;




        //18
        temp=18;
        path[temp][0]=13;
        path[temp][1]=12;
        path[temp][2]=17;
        path[temp][3]=22;
        path[temp][4]=23;
        path[temp][5]=24;
        path[temp][6]=19;
        path[temp][7]=14;




        //19
        temp=19;
        path[temp][0]=14;
        path[temp][2]=18;
        path[temp][6]=20;
        path[temp][4]=24;




        //20
        temp=20;
        path[temp][0]=15;
        path[temp][1]=14;
        path[temp][2]=19;
        path[temp][3]=24;
        path[temp][4]=25;





        //21
        temp=21;
        path[temp][0]=16;
        path[temp][6]=22;
        path[temp][4]=26;




        //22
        temp=22;
        path[temp][0]=17;
        path[temp][1]=16;
        path[temp][2]=21;
        path[temp][3]=26;
        path[temp][4]=27;
        path[temp][5]=28;
        path[temp][6]=23;
        path[temp][7]=18;


        //23
        temp=23;
        path[temp][0]=18;
        path[temp][2]=22;
        path[temp][6]=24;
        path[temp][4]=28;




        //24
        temp=24;
        path[temp][0]=19;
        path[temp][1]=18;
        path[temp][2]=23;
        path[temp][3]=28;
        path[temp][4]=29;
        path[temp][5]=30;
        path[temp][6]=25;
        path[temp][7]=20;


        //25
        temp=25;
        path[temp][0]=20;
        path[temp][2]=24;
        path[temp][4]=30;




        //26
        temp=26;
        path[temp][0]=21;
        path[temp][7]=22;
        path[temp][6]=27;




        //27
        temp=27;
        path[temp][0]=22;
        path[temp][2]=26;
        path[temp][6]=28;




        //28
        temp=28;
        path[temp][0]=23;
        path[temp][1]=22;
        path[temp][2]=27;
        path[temp][3]=31;
        path[temp][4]=32;
        path[temp][5]=33;
        path[temp][6]=29;
        path[temp][7]=24;




        //29
        temp=29;
        path[temp][0]=24;
        path[temp][2]=28;
        path[temp][6]=30;




        //30
        temp=30;
        path[temp][0]=25;
        path[temp][1]=24;
        path[temp][2]=29;




        //31
        temp=31;
        path[temp][7]=28;
        path[temp][6]=32;
        path[temp][3]=34;




        //32
        temp=32;
        path[temp][0]=28;
        path[temp][2]=31;
        path[temp][6]=33;
        path[temp][4]=35;




        //33
        temp=33;
        path[temp][1]=28;
        path[temp][2]=32;
        path[temp][5]=36;




        //34
        temp=34;
        path[temp][7]=31;
        path[temp][6]=35;




        //35
        temp=35;
        path[temp][0]=32;
        path[temp][2]=34;
        path[temp][6]=36;




        //36
        temp=36;
        path[temp][1]=33;
        path[temp][2]=35;










        // draw d=new draw(context,position,arr,flag);

        thread=new Thread(this);
        thread.start();

        if(timerIsRunning) startTimer();


        DH=new DatabaseHelper(context);


        setOnTouchListener(this);




        databaseReference.child("play").child(idPair).child(playerSession).child("turn").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                try {
                    String value = (String) dataSnapshot.getValue();

                    if (value.equals(userName)) {

                        //set your turn

                        team1Move=false;
                        team2Move=true;



                    }
                    else if(value.equals(otherPlayer)){

                        //setOtherPlayerTurn

                        team1Move=true;
                        team2Move=false;
                    }

                }catch(Exception e){

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        databaseReference.child("play").child(idPair).child(playerSession).child("game").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                HashMap<String,Object> hashMap=(HashMap<String,Object>) dataSnapshot.getValue();


                try {


                    if (hashMap != null) {

                        String value = " ";
                        String ployKey=" ";
                        String firstPlayerName = userName;

                        for (String key : hashMap.keySet()) {

                            value = (String) hashMap.get(key);
                            ployKey=key;



                            if (value.equals(userName)) {

                                team1Move = false;
                                team2Move = true;
                            } else if (value.equals(otherPlayer)) {

                                //setOtherPlayerTurn

                                team1Move = true;
                                team2Move = false;

                                firstPlayerName = value;

                                String[] splitId =  ployKey.split(":");

                                if(splitId.length==2) otherPlayerTurn(Integer.parseInt(splitId[0]),Integer.parseInt(splitId[1]));
                                else if(splitId.length==3) otherPlayerTurn(Integer.parseInt(splitId[0]),Integer.parseInt(splitId[1]),Integer.parseInt(splitId[2]));


                            }


                        }





                    }

                }catch(Exception b){

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }


   /* @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);



        paint.setColor(Color.RED);

        canvas.drawCircle(145,167,34,paint);
        System.out.println("azzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");


    }*/



   public void otherPlayerTurn(int source,int destination){

       System.out.println("source: "+source+" destination: "+destination);

       AX=arr[36-source][0];
       AY=arr[36-source][1];
       BX=arr[36-destination][0];
       BY=arr[36-destination][1];

       drawLine2=true;

       try {
           thread.sleep(700);
       }
       catch (InterruptedException ie) {

       }



       arr[36-source][2]=0;
       arr[36-destination][2]=1;

       drawLine2=false;

       soundplay.hitSound();





       team1Move = true;
       team2Move = false;

    }

    public void otherPlayerTurn(int source,int middle,int destination){

        System.out.println("source: "+source+" Middle: "+middle+" destination: "+destination);

        AX=arr[36-source][0];
        AY=arr[36-source][1];
        BX=arr[36-destination][0];
        BY=arr[36-destination][1];
        drawLine2=true;

        try {
            thread.sleep(700);
        } catch (InterruptedException ie) {

        }


        arr[36-source][2]=0;
        arr[36-middle][2]=0;
        arr[36-destination][2]=1;

        totalpawn2--;

        A=-1000;
        B=-1000;


        drawLine2=false;


        team1Move = true;
        team2Move = false;

        soundplay.hitSound();

    }




    public boolean onTouch(View v, MotionEvent e){





        click=true;



        //select


        int check;
        int x1=(int)e.getX();
        int y1=(int)e.getY();
        for(int i=0;i<37;i++) {

            /*if((x1>=(arr[i][0]-40)&&x1<=arr[i][0]+40)&&((y1>=(arr[i][1]-40)&&y1<=arr[i][1]+40))&&arr[i][2]==1&&team1Move==true) {


                mouseTemp=i;


                soundplay.hitSound();



						/*team2Move=true;
						team1Move=false;

						for(int j=0;j<8;j++) {

								check=path[mouseTemp][j];
								if(check!=-1&&path[check][j]!=-1) {

										if((arr[check][2]==2&&arr[mouseTemp][2]==1&&arr[path[check][j]][2]==0)) {

											team1Move=true;
										}
								}
						}*/

              /*  A=arr[mouseTemp][0];
                B=arr[mouseTemp][1];
                mouse=true;

            }*/
            if((x1>=(arr[i][0]-40)&&x1<=arr[i][0]+40)&&((y1>=(arr[i][1]-40)&&y1<=arr[i][1]+40))&&arr[i][2]==2&&team2Move==true) {

                mouseTemp=i;

                soundplay.hitSound();



						/*team1Move=true;
						team2Move=false;

						for(int j=0;j<8;j++) {

								check=path[mouseTemp][j];
								if(check!=-1&&path[check][j]!=-1) {

										if((arr[check][2]==1&&arr[mouseTemp][2]==2&&arr[path[check][j]][2]==0)) {

											team2Move=true;
										}
								}
						}*/

                A=arr[mouseTemp][0];
                B=arr[mouseTemp][1];
                mouse=true;


            }



        }






        //move

        if(mouse==true) {

            int x2=(int)e.getX();
            int y2=(int)e.getY();

            for(int i=0;i<8;i++) {


                if(path[mouseTemp][i]!=-1) {

                   /* if((x2>=(arr[path[mouseTemp][i]][0]-40)&&x2<=arr[path[mouseTemp][i]][0]+40)&&((y2>=(arr[path[mouseTemp][i]][1]-40)&&y2<=arr[path[mouseTemp][i]][1]+40))
                            &&(arr[path[mouseTemp][i]][2]==0&&arr[mouseTemp][2]==1)&&team1Move==true) {


                        if(arr[mouseTemp][2]==1) {

                            arr[path[mouseTemp][i]][2]=1;
                            A=arr[path[mouseTemp][i]][0];
                            B=arr[path[mouseTemp][i]][1];



                        }





                        soundplay.hitSound();


                        arr[mouseTemp][2]=0;

                        //mouse1=true;
                        team1Move=false;
                        team2Move=true;

                        databaseReference.child("playing").child(playerSession).child("game").child(mouseTemp+":"+path[mouseTemp][i]).setValue(userName);
                        databaseReference.child("playing").child(playerSession).child("turn").setValue(otherPlayer);



                        mouse=false;


                    }*/


                    if((x2>=(arr[path[mouseTemp][i]][0]-40)&&x2<=arr[path[mouseTemp][i]][0]+40)&&((y2>=(arr[path[mouseTemp][i]][1]-40)&&y2<=arr[path[mouseTemp][i]][1]+40))
                            &&(arr[path[mouseTemp][i]][2]==0&&arr[mouseTemp][2]==2)&&team2Move==true) {


                        if (arr[mouseTemp][2] == 2) {

                            arr[path[mouseTemp][i]][2] = 2;
                            A = arr[path[mouseTemp][i]][0];
                            B = arr[path[mouseTemp][i]][1];




                        }

                        arr[mouseTemp][2] = 0;

                        soundplay.hitSound();


                        //mouse1=true;
                        team1Move = true;
                        team2Move = false;


                        mouse = false;

                        databaseReference.child("play").child(idPair).child(playerSession).child("game").removeValue();
                        databaseReference.child("play").child(idPair).child(playerSession).child("game").child(mouseTemp+":"+path[mouseTemp][i]).setValue(userName);
                        databaseReference.child("play").child(idPair).child(playerSession).child("turn").setValue(otherPlayer);



                    }
                    int next=path[mouseTemp][i];
                    if(path[next][i]!=-1) {

                       /* if((x2>=(arr[path[next][i]][0]-40)&&x2<=arr[path[next][i]][0]+40)&&((y2>=(arr[path[next][i]][1]-40)&&y2<=arr[path[next][i]][1]+40))
                                &&((arr[next][2]==2&&arr[mouseTemp][2]==1&&arr[path[next][i]][2]==0))) {

                            //System.out.println("mouseTemp:"+mouseTemp+"  "+"next:"+next+" "+"next2:"+path[next][i]);
                            if(arr[mouseTemp][2]==1) {
                                totalpawn2--;
                                arr[path[next][i]][2]=1;
                                A=arr[path[next][i]][0];
                                B=arr[path[next][i]][1];

                            }

                            soundplay.hitSound();


                            arr[mouseTemp][2]=0;
                            arr[next][2]=0;

                            team1Move=false;
                            team2Move=true;
                            mouse=false;


                            int tmp=path[next][i];


                            databaseReference.child("playing").child(playerSession).child("game").child(mouseTemp+":"+path[mouseTemp][i]).setValue(userName);
                            databaseReference.child("playing").child(playerSession).child("turn").setValue(otherPlayer);



                            for(int t=0;t<8;t++) {

                                if(path[tmp][t]!=-1) {
                                    int tmp2=path[tmp][t];
                                    if(path[tmp2][t]!=-1) {
                                        if(arr[path[tmp][t]][2]==2&&arr[tmp][2]==1&&arr[path[tmp2][t]][2]==0) {

                                            team1Move=true;
                                            team2Move=true;

                                        }
                                    }
                                }


                            }



                        }*/







                        if((x2>=(arr[path[next][i]][0]-40)&&x2<=arr[path[next][i]][0]+40)&&((y2>=(arr[path[next][i]][1]-40)&&y2<=arr[path[next][i]][1]+40))
                                &&((arr[next][2]==1&&arr[mouseTemp][2]==2&&arr[path[next][i]][2]==0))) {

                            //System.out.println("mouseTemp:"+mouseTemp+"  "+"next:"+next+" "+"next2:"+path[next][i]);

                            if(arr[mouseTemp][2]==2) {
                                totalpawn1--;
                                arr[path[next][i]][2]=2;

                                A=arr[path[next][i]][0];
                                B=arr[path[next][i]][1];

                            }

                            soundplay.hitSound();

                            arr[mouseTemp][2]=0;
                            arr[next][2]=0;

                            team2Move=false;
                            team1Move=true;

                            mouse=false;



                            int tmp=path[next][i];

                            databaseReference.child("play").child(idPair).child(playerSession).child("game").removeValue();
                            databaseReference.child("play").child(idPair).child(playerSession).child("game").child(mouseTemp+":"+next+":"+path[next][i]).setValue(userName);
                            databaseReference.child("play").child(idPair).child(playerSession).child("turn").setValue(otherPlayer);



                            for(int t=0;t<8;t++) {

                                if(path[tmp][t]!=-1) {
                                    int tmp2=path[tmp][t];
                                    if(path[tmp2][t]!=-1) {
                                        if(arr[path[tmp][t]][2]==1&&arr[tmp][2]==2&&arr[path[tmp2][t]][2]==0) {

                                            team1Move=true;
                                            team2Move=true;

                                            databaseReference.child("play").child(idPair).child(playerSession).child("turn").setValue(userName);

                                        }
                                    }
                                }


                            }






                        }






                    }


                }


            }


        }


        showMessage();

        return false;
    }







    public void run() {








        while(isItok==true) {

            if (!holder.getSurface().isValid()) {
                continue;
            }





            Canvas canvas = holder.lockCanvas();




            canvas.drawBitmap(bitmap,0,0,paint);


            Paint textpaint=new Paint();
            textpaint.setTextAlign(Paint.Align.CENTER);
            textpaint.setTypeface(Typeface.DEFAULT_BOLD);
            textpaint.setTextSize(30);
            textpaint.setColor(Color.BLUE);

            canvas.drawText("Me: "+totalpawn2,eachRoomWidth-50,eachRoomHeight+20,textpaint);

            textpaint.setColor(Color.GREEN);
            canvas.drawText("Op: "+totalpawn1,eachRoomWidth*4+60,eachRoomHeight+20,textpaint);


            textpaint.setTextSize(35);
            textpaint.setColor(Color.RED);
            canvas.drawText("Timer: "+time,eachRoomWidth*2-80,(eachRoomHeight/2)-30,textpaint);

           /* Rect rect=new Rect(0,0,canvas.getWidth(),canvas.getHeight());
            canvas.drawRect(rect,paint);*/

            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(4);

           // System.out.println("canvas " + canvas.getWidth() + " " + canvas.getHeight());




            paint.setColor(Color.BLACK);


            for (int i = 6; i <= 10; i++) {

                canvas.drawLine(position[i][0], position[i][1], position[i + 20][0], position[i + 20][1], paint);

            }
            for (int i = 6; i <= 30; i = i + 5) {

                canvas.drawLine(position[i][0], position[i][1], position[i + 4][0], position[i + 4][1], paint);

            }

            //middle
            canvas.drawLine(position[1][0], position[1][1], position[35][0], position[35][1], paint);

            //slope
            canvas.drawLine(position[6][0], position[6][1], position[30][0], position[30][1], paint);

            canvas.drawLine(position[10][0], position[10][1], position[26][0], position[26][1], paint);

            //extended slope

            canvas.drawLine(position[16][0], position[16][1], position[2][0], position[2][1], paint);
            canvas.drawLine(position[20][0], position[20][1], position[0][0], position[0][1], paint);

            canvas.drawLine(position[16][0], position[16][1], position[36][0], position[36][1], paint);
            canvas.drawLine(position[20][0], position[20][1], position[34][0], position[34][1], paint);


            //extra line

            canvas.drawLine(position[0][0], position[0][1], position[2][0], position[2][1], paint);
            canvas.drawLine(position[3][0], position[3][1], position[5][0], position[5][1], paint);

            canvas.drawLine(position[34][0], position[34][1], position[36][0], position[36][1], paint);
            canvas.drawLine(position[31][0], position[31][1], position[33][0], position[33][1], paint);




            if (drawLine2 == true) {

               // System.out.println("draw enter the run second hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");


                paint.setStrokeWidth(8);
                paint.setColor(Color.RED);
                canvas.drawLine(AX, AY, BX, BY, paint);


                //holder.unlockCanvasAndPost(canvas);
                for (int l = 0; l < 30; l++) {

                }

                //drawLine=false;
                // break;

            }



            paint.setColor(Color.BLUE);




            for (int i = 0; i < 37; i++) {

                if (arr[i][2] >0) {

                    if (arr[i][2] == 2) paint.setColor(Color.BLUE);
                    else paint.setColor(Color.GREEN);


                    canvas.drawCircle(arr[i][0] + 7, arr[i][1] + 7, 25, paint);

                }


            }

            if(click==true) {

                Paint circlePaint=new Paint();
                circlePaint.setStrokeWidth(8);
                circlePaint.setColor(Color.RED);
                circlePaint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(A+7,B+7,25,circlePaint);

            }

            if(team1Move){

                Paint circlePaint=new Paint();
                circlePaint.setColor(Color.GREEN);
                canvas.drawCircle(eachRoomWidth*4+65,eachRoomHeight+50,20,circlePaint);

                textpaint.setTextSize(35);
                textpaint.setColor(Color.YELLOW);
                canvas.drawText(userName+"'s Turn",eachRoomWidth*4-30,(eachRoomHeight/2)-30,textpaint);



            }
            else if(team2Move){

                Paint circlePaint=new Paint();
                circlePaint.setColor(Color.BLUE);
                canvas.drawCircle(eachRoomWidth-55,eachRoomHeight+50,20,circlePaint);

                textpaint.setTextSize(35);
                textpaint.setColor(Color.YELLOW);
                canvas.drawText("Your Turn!",eachRoomWidth*4-20,(eachRoomHeight/2)-30,textpaint);



            }









            holder.unlockCanvasAndPost(canvas);
        }



    }


    public void pause(){

        isItok=false;

        while(true){


            try{
                thread.join();
            }catch(InterruptedException i){
                i.printStackTrace();
            }
            break;
        }
        thread=null;
    }
    public void resume(){

        isItok=true;

        thread=new Thread(this);
        thread.start();

    }



    public void startTimer(){

        timer=new CountDownTimer(lefttime,1) {
            @Override
            public void onTick(long l) {

                timeCounter=86400000-l;

                hours=(int)((timeCounter/1000)/3600);
                minutes=(int)((timeCounter/1000)/60);
                seconds=(int)((timeCounter/1000)%60);

                time=String.format("%02d:%02d:%02d\n",hours,minutes,seconds);



            }

            @Override
            public void onFinish() {

            }
        }.start();


    }






    public void showMessage(){

        if(totalpawn1<=0){


            Cursor data=DH.getListContents();

            int numofcol=data.getCount();
            System.out.println("column count"+numofcol);

            if(numofcol<=0){


                DH.addData("Green,"+totalpawn2+","+timeCounter);

            }
            else{

                while(data.moveToNext()) {

                    String s = data.getString(1);

                    String[] str = s.split(",");

                    int num = Integer.parseInt(str[2]);

                    if (num > timeCounter) {
                        DH.deleteData();

                        DH.addData("Green," + totalpawn2 + "," + timeCounter);


                    }
                }





            }









            try {
                thread.sleep(100);
            }
            catch(InterruptedException e) {

            }

            timerIsRunning=false;
            timer.cancel();
            AlertDialog.Builder dialong=new  AlertDialog.Builder(context);

            //dialong.setMessage("Sorry! try again").create();

            dialong.setMessage("     Green have won the match\n"+"\t\t           by "+totalpawn1+" beads\n"+"\t\t\t\t\t\t          in\n"+"\t\t"+minutes+" minutes and "+seconds+" seconds").setPositiveButton("new game", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int e) {

                    for(int i=0;i<37;i++) {

                        arr[i][0]=position[i][0];
                        arr[i][1]=position[i][1];
                        arr[i][2]=position[i][2];


                    }

                    startTimer();
                    lefttime=86400000;

                    timerIsRunning=true;

                    timeCounter=0;
                    totalpawn1=16;
                    totalpawn2=16;

                    minutes=0;
                    seconds=0;
                    hours=0;

                    A=-100;
                    B=-100;


                }


            }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    System.exit(0);
                }
            }).create();

            dialong.show();




        }

        if(totalpawn2<=0){



            Cursor data=DH.getListContents();

            int numofcol=data.getCount();
            System.out.println("column count"+numofcol);

            if(numofcol<=0){


                DH.addData("Blue,"+totalpawn2+","+timeCounter);

            }
            else{

                while(data.moveToNext()) {

                    String s = data.getString(1);

                    String[] str = s.split(",");

                    int num = Integer.parseInt(str[2]);

                    if (num > timeCounter) {
                        DH.deleteData();

                        DH.addData("Blue," + totalpawn2 + "," + timeCounter);


                    }
                }





            }














            try {
                thread.sleep(100);
            }
            catch(InterruptedException e){

            }

            timerIsRunning=false;
            timer.cancel();


            AlertDialog.Builder dialong1=new  AlertDialog.Builder(context);

            dialong1.setMessage("     Blue have won the match\n"+"\t\t           by "+totalpawn1+" beads\n"+"\t\t\t\t\t\t          in\n"+"\t\t"+minutes+" minutes and "+seconds+" seconds").setPositiveButton("new game", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int e) {

                    for(int i=0;i<37;i++) {

                        arr[i][0]=position[i][0];
                        arr[i][1]=position[i][1];
                        arr[i][2]=position[i][2];


                    }

                    startTimer();
                    lefttime=86400000;

                    timerIsRunning=true;

                    timeCounter=0;
                    totalpawn1=16;
                    totalpawn2=16;

                    minutes=0;
                    seconds=0;
                    hours=0;

                    A=-100;
                    B=-100;


                }


            }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    System.exit(0);
                }
            }).create();

            dialong1.show();




        }


    }



}
