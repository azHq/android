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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class HardLevel2 extends SurfaceView implements View.OnTouchListener,Runnable {

    private int[][] arr = new int[37][3];

    private int[][] position = new int[37][3];

    public Queue<Integer> queue = new LinkedList<Integer>();

    public int tmp1 = 0, tmp2 = 0, tmp3 = 0, tmp4 = 0;

    public boolean playGame = true;

    public boolean ploy1 = true, ploy2 = true;

    private int x = 940, y = 485;  //940,485,

    private int delay = 6;
    private int f = 1, g, h = 1, a;
    private String team1 = "azaz", team2 = "bablu";
    private int totalpawn1 = 16, totalpawn2 = 16;


    public int source, destination, AX, AY, BX, BY;

    public int controlDoubleployForSource=-10,controlDoubleployForDestination=-10;



    public int[][] path = new int[37][8];

    public int[][] bestWay = new int[8][2];
    int[][] firstTwoMax = new int[2][2];

    int mouseClickCount = 0;

    private int mouseTemp;


    ArrayList<ArrayList<Integer>> multiLineDraw = new ArrayList<ArrayList<Integer>>();


    private int A = -100, B = -100;
    private int counter1 = 0, counter2 = 0;


    private boolean mouse, team1Move = true, team2Move = true;

    private boolean Atflag = false, drawLine = false, drawLine2 = false;

    boolean click = false;

    CountDownTimer timer;

    public long timeCounter = 0;

    public String time;


    public int height;
    public int width;

    public int eachRoomHeight;
    public int eachRoomWidth;

    public Bitmap bmp;
    public Context context;
    public boolean flag = false;

    SurfaceHolder holder;
    boolean isItok = false;

    public int temp = 0;

    Thread thread;


    public Paint paint;
    public Canvas canvas;


    public Bitmap bitmap;
    Soundplay soundplay;

    int minutes = 0, seconds = 0, hours = 0;
    long lefttime = 86400000;
    boolean timerIsRunning = true;

    public int minvalue=0;

    DatabaseHelper DH;

    public HardLevel2(Context context) {


        super(context);
        this.context = context;
        paint = new Paint();

        holder = getHolder();

        soundplay = new Soundplay(context);


        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.azaz6);


        DisplayMetrics metrics = new DisplayMetrics();
        // getWindowManager().getDefaultDisplay().getMetrics(metrics);

        width = Resources.getSystem().getDisplayMetrics().widthPixels;


        height = Resources.getSystem().getDisplayMetrics().heightPixels;


        int maxHeight = 2000;
        int maxWidth = 2000;
        float scale = Math.max(((float) height / bitmap.getWidth()), ((float) width / bitmap.getHeight()));

        Matrix matrix = new Matrix();
        matrix.postScale(scale - 50, scale + 10);


        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);


        // WindowManager w=getWindowManager();

       /* Display d = w.getDefaultDisplay();
        width = d.getWidth();
        height = d.getHeight();

         width = WindowManager.getWidth();
         height = getWindowManager().getDefaultDisplay().getHeight();*/

        System.out.println(width + " " + height);

        eachRoomWidth = width / 6 + 20;
        eachRoomHeight = height / 10 + 20;

        int posX, posY, count = 0;

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 5; j++) {

                posY = eachRoomHeight * (i - 1) - 10;
                posX = eachRoomWidth * j - 60;

                if ((i == 1 && j != 2 && j != 4) || (i == 2 && j != 1 && j != 5) || (i == 8 && j != 1 && j != 5) || (i == 9 && j != 2 && j != 4) || (i >= 3 && i <= 7)) {

                    position[count][0] = posX;
                    position[count][1] = posY + 30;

                    if (count >= 0 && count <= 15) position[count][2] = 2;
                    else if (count >= 21 && count <= 36) position[count][2] = 1;
                    else position[count][2] = 0;


                    //System.out.println("positionX:"+posX+"   positionY:"+posY+"   count:"+count);

                    count++;

                }


            }

        }

        //left
        position[0][0] += 50;
        position[0][1] += 50;

        position[1][0] += 1;
        position[1][1] += 50;


        position[2][0] -= 50;
        position[2][1] += 50;


        //right
        position[34][0] += 50;
        position[34][1] -= 50;

        position[35][0] += 1;
        position[35][1] -= 50;

        position[36][0] -= 50;
        position[36][1] -= 50;

        for (int i = 0; i < 37; i++) {

            arr[i][0] = position[i][0];
            arr[i][1] = position[i][1];
            arr[i][2] = position[i][2];


        }


        //path

        for (int i = 0; i < 37; i++) {

            for (int j = 0; j < 8; j++) {

                path[i][j] = -1;

            }
        }

        //0
        temp = 0;

        path[temp][5] = 3;
        path[temp][6] = 1;


        //1
        temp = 1;
        path[temp][2] = 0;
        path[temp][4] = 4;
        path[temp][6] = 2;


        //2
        temp = 2;
        path[temp][2] = 1;
        path[temp][3] = 5;

        //3
        temp = 3;
        path[temp][1] = 0;
        path[temp][6] = 4;
        path[temp][5] = 8;


        //4
        temp = 4;
        path[temp][0] = 1;
        path[temp][2] = 3;
        path[temp][4] = 8;
        path[temp][6] = 5;


        //5
        temp = 5;
        path[temp][7] = 2;
        path[temp][2] = 4;
        path[temp][3] = 8;


        //6
        temp = 6;
        path[temp][6] = 7;
        path[temp][4] = 11;
        path[temp][5] = 12;


        //7
        temp = 7;
        path[temp][2] = 6;
        path[temp][6] = 8;
        path[temp][4] = 12;


        //8
        temp = 8;
        path[temp][0] = 4;
        path[temp][1] = 3;
        path[temp][2] = 7;
        path[temp][3] = 12;
        path[temp][4] = 13;
        path[temp][5] = 14;
        path[temp][6] = 9;
        path[temp][7] = 5;


        //9
        temp = 9;
        path[temp][2] = 8;
        path[temp][6] = 10;
        path[temp][4] = 14;


        //10
        temp = 10;
        path[temp][2] = 9;
        path[temp][3] = 14;
        path[temp][4] = 15;


        //11
        temp = 11;
        path[temp][0] = 6;
        path[temp][6] = 12;
        path[temp][4] = 16;


        //12
        temp = 12;
        path[temp][0] = 7;
        path[temp][1] = 6;
        path[temp][2] = 11;
        path[temp][3] = 16;
        path[temp][4] = 17;
        path[temp][5] = 18;
        path[temp][6] = 13;
        path[temp][7] = 8;


        //complete


        //13
        temp = 13;
        path[temp][0] = 8;
        path[temp][2] = 12;
        path[temp][6] = 14;
        path[temp][4] = 18;


        //14
        temp = 14;
        path[temp][0] = 9;
        path[temp][1] = 8;
        path[temp][2] = 13;
        path[temp][3] = 18;
        path[temp][4] = 19;
        path[temp][5] = 20;
        path[temp][6] = 15;
        path[temp][7] = 10;


        //15
        temp = 15;
        path[temp][0] = 10;
        path[temp][2] = 14;
        path[temp][4] = 20;


        //16
        temp = 16;
        path[temp][0] = 11;
        path[temp][7] = 12;
        path[temp][6] = 17;
        path[temp][4] = 21;
        path[temp][5] = 22;


        //17
        temp = 17;
        path[temp][0] = 12;
        path[temp][2] = 16;
        path[temp][6] = 18;
        path[temp][4] = 22;


        //18
        temp = 18;
        path[temp][0] = 13;
        path[temp][1] = 12;
        path[temp][2] = 17;
        path[temp][3] = 22;
        path[temp][4] = 23;
        path[temp][5] = 24;
        path[temp][6] = 19;
        path[temp][7] = 14;


        //19
        temp = 19;
        path[temp][0] = 14;
        path[temp][2] = 18;
        path[temp][6] = 20;
        path[temp][4] = 24;


        //20
        temp = 20;
        path[temp][0] = 15;
        path[temp][1] = 14;
        path[temp][2] = 19;
        path[temp][3] = 24;
        path[temp][4] = 25;


        //21
        temp = 21;
        path[temp][0] = 16;
        path[temp][6] = 22;
        path[temp][4] = 26;


        //22
        temp = 22;
        path[temp][0] = 17;
        path[temp][1] = 16;
        path[temp][2] = 21;
        path[temp][3] = 26;
        path[temp][4] = 27;
        path[temp][5] = 28;
        path[temp][6] = 23;
        path[temp][7] = 18;


        //23
        temp = 23;
        path[temp][0] = 18;
        path[temp][2] = 22;
        path[temp][6] = 24;
        path[temp][4] = 28;


        //24
        temp = 24;
        path[temp][0] = 19;
        path[temp][1] = 18;
        path[temp][2] = 23;
        path[temp][3] = 28;
        path[temp][4] = 29;
        path[temp][5] = 30;
        path[temp][6] = 25;
        path[temp][7] = 20;


        //25
        temp = 25;
        path[temp][0] = 20;
        path[temp][2] = 24;
        path[temp][4] = 30;


        //26
        temp = 26;
        path[temp][0] = 21;
        path[temp][7] = 22;
        path[temp][6] = 27;


        //27
        temp = 27;
        path[temp][0] = 22;
        path[temp][2] = 26;
        path[temp][6] = 28;


        //28
        temp = 28;
        path[temp][0] = 23;
        path[temp][1] = 22;
        path[temp][2] = 27;
        path[temp][3] = 31;
        path[temp][4] = 32;
        path[temp][5] = 33;
        path[temp][6] = 29;
        path[temp][7] = 24;


        //29
        temp = 29;
        path[temp][0] = 24;
        path[temp][2] = 28;
        path[temp][6] = 30;


        //30
        temp = 30;
        path[temp][0] = 25;
        path[temp][1] = 24;
        path[temp][2] = 29;


        //31
        temp = 31;
        path[temp][7] = 28;
        path[temp][6] = 32;
        path[temp][3] = 34;


        //32
        temp = 32;
        path[temp][0] = 28;
        path[temp][2] = 31;
        path[temp][6] = 33;
        path[temp][4] = 35;


        //33
        temp = 33;
        path[temp][1] = 28;
        path[temp][2] = 32;
        path[temp][5] = 36;


        //34
        temp = 34;
        path[temp][7] = 31;
        path[temp][6] = 35;


        //35
        temp = 35;
        path[temp][0] = 32;
        path[temp][2] = 34;
        path[temp][6] = 36;


        //36
        temp = 36;
        path[temp][1] = 33;
        path[temp][2] = 35;

        Random rand = new Random();

        for (int i = 0; i < 37; i++) {

            queue.add(rand.nextInt(36));

        }

        for (int i = 0; i < 37; i++) {

            queue.add(i);

        }


        // draw d=new draw(context,position,arr,flag);

        thread = new Thread(this);
        thread.start();

        if (timerIsRunning) startTimer();


        setOnTouchListener(this);


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


    public boolean onTouch(View v, MotionEvent e) {


        click = true;


        //select


        int check;
        int x1 = (int) e.getX();
        int y1 = (int) e.getY();
        for (int i = 0; i < 37; i++) {

            if ((x1 >= (arr[i][0] - 40) && x1 <= arr[i][0] + 40) && ((y1 >= (arr[i][1] - 40) && y1 <= arr[i][1] + 40)) && arr[i][2] == 1) {


                mouseTemp = i;



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

                A = arr[mouseTemp][0];
                B = arr[mouseTemp][1];
                mouse = true;

            }
           /* if((x1>=(arr[i][0]-40)&&x1<=arr[i][0]+40)&&((y1>=(arr[i][1]-40)&&y1<=arr[i][1]+40))&&arr[i][2]==2) {

                mouseTemp=i;


						/*team1Move=true;
						team2Move=false;

						for(int j=0;j<8;j++) {

								check=path[mouseTemp][j];
								if(check!=-1&&path[check][j]!=-1) {

										if((arr[check][2]==1&&arr[mouseTemp][2]==2&&arr[path[check][j]][2]==0)) {

											team2Move=true;
										}
								}
						}

                A=arr[mouseTemp][0];
                 B=arr[mouseTemp][1];
                mouse=true;


            }*/


        }


        //move

        if (mouse == true) {

            int w;
            int x2 = (int) e.getX();
            int y2 = (int) e.getY();


            for (int i = 0; i < 8; i++) {


                if (path[mouseTemp][i] != -1) {

                    if ((x2 >= (arr[path[mouseTemp][i]][0] - 45) && x2 <= arr[path[mouseTemp][i]][0] + 45) && ((y2 >= (arr[path[mouseTemp][i]][1] - 45) && y2 <= arr[path[mouseTemp][i]][1] + 45))
                            && (arr[path[mouseTemp][i]][2] == 0 && arr[mouseTemp][2] == 1) && team1Move == true) {


                        if (arr[mouseTemp][2] == 1) {

                            arr[path[mouseTemp][i]][2] = 1;
                            A = arr[path[mouseTemp][i]][0];
                            B = arr[path[mouseTemp][i]][1];


                            Atflag = true;

                        }


                        arr[mouseTemp][2] = 0;

                        //mouse1=true;
                        team1Move = false;
                        team2Move = true;

                        mouse = false;


                        Atflag = true;

                    }


                    if ((x2 >= (arr[path[mouseTemp][i]][0] - 45) && x2 <= arr[path[mouseTemp][i]][0] + 45) && ((y2 >= (arr[path[mouseTemp][i]][1] - 45) && y2 <= arr[path[mouseTemp][i]][1] + 45))
                            && (arr[path[mouseTemp][i]][2] == 0 && arr[mouseTemp][2] == 2) && team2Move == true) {


                        if (arr[mouseTemp][2] == 2) {

                            Atflag = true;

                            arr[path[mouseTemp][i]][2] = 2;
                            //A=arr[path[mouseTemp][i]][0];
                            // B=arr[path[mouseTemp][i]][1];


                        }

                        arr[mouseTemp][2] = 0;

                        //mouse1=true;
                        team1Move = true;
                        team2Move = false;
                        mouse = false;
                        System.out.println(mouse);

                    }


                    int next = path[mouseTemp][i];
                    if (path[next][i] != -1) {

                        if ((x2 >= (arr[path[next][i]][0] - 45) && x2 <= arr[path[next][i]][0] + 45) && ((y2 >= (arr[path[next][i]][1] - 45) && y2 <= arr[path[next][i]][1] + 45))
                                && ((arr[next][2] == 2 && arr[mouseTemp][2] == 1 && arr[path[next][i]][2] == 0))) {

                            //System.out.println("mouseTemp:"+mouseTemp+"  "+"next:"+next+" "+"next2:"+path[next][i]);
                            if (arr[mouseTemp][2] == 1) {
                                totalpawn2--;
                                arr[path[next][i]][2] = 1;
                                A = arr[path[next][i]][0];
                                B = arr[path[next][i]][1];

                                int tmp = path[next][i];

                                Atflag = true;

                                for (int t = 0; t < 8; t++) {

                                    if (path[tmp][t] != -1) {
                                        int tmp2 = path[tmp][t];
                                        if (path[tmp2][t] != -1) {
                                            if (arr[path[tmp][t]][2] == 2 && arr[tmp][2] == 1 && arr[path[tmp2][t]][2] == 0) {

                                                Atflag = false;

                                            }
                                        }
                                    }


                                }


                            }

                            arr[mouseTemp][2] = 0;
                            arr[next][2] = 0;

                            team1Move = false;
                            team2Move = true;
                            mouse = false;
                            System.out.println("hello azaz");


                        }


                        if ((x2 >= (arr[path[next][i]][0] - 45) && x2 <= arr[path[next][i]][0] + 45) && ((y2 >= (arr[path[next][i]][1] - 45) && y2 <= arr[path[next][i]][1] + 45))
                                && ((arr[next][2] == 1 && arr[mouseTemp][2] == 2 && arr[path[next][i]][2] == 0))) {

                            //System.out.println("mouseTemp:"+mouseTemp+"  "+"next:"+next+" "+"next2:"+path[next][i]);

                            if (arr[mouseTemp][2] == 2) {
                                totalpawn1--;
                                arr[path[next][i]][2] = 2;

                                // A=arr[path[next][i]][0];
                                // B=arr[path[next][i]][1];

                                Atflag = true;

                            }
                            arr[mouseTemp][2] = 0;
                            arr[next][2] = 0;

                            team2Move = false;
                            team1Move = true;

                            mouse = false;
                            System.out.println("hello azaz");


                        }


                    }


                }


            }


        }


        if (Atflag == true) {

            ploy1 = true;
            ploy2 = true;


            int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, count7 = 0, count8 = 0;


            ArrayList<Integer> bestWay[] = new ArrayList[37];
            int[] counter = new int[37];

            int[] checkCounter = new int[37];

            for (int t = 0; t < 37; t++) {

                bestWay[t] = new ArrayList<Integer>();

            }


            if (ploy1 == true) {
                for (int i = 0; i < 37; i++) {


                    if (arr[i][2] == 2) {


                        for (int j = 0; j < 8; j++) {

                            int[][] Altarr = new int[37][3];

                            int s = 1, temp = 0, tempX1 = 0, tempX2 = 0, tempX3, lastPosition = -1;

                            for (int m = 0; m < Altarr.length; m++) {

                                for (int n = 0; n < Altarr[0].length; n++) {

                                    Altarr[m][n] = arr[m][n];


                                }

                            }

                            tempX1 = i;

                            temp = path[i][j];
                            tempX2 = temp;


                            if (temp != -1 && path[temp][j] != -1) {
                                if (path[i][j] != -1 && Altarr[path[i][j]][2] == 1 && Altarr[path[temp][j]][2] == 0) {


                                    BestWay bestway = new BestWay(tempX1, counter, bestWay, checkCounter);

                                    bestway.findBestWay(tempX1, Altarr, path);

                                    break;


                                }

                            }

                        }


                    }
                }


                for (int t = 0; t < 37; t++) {

                    System.out.print(checkCounter[t]);
                }


                System.out.println(" eat up counter in AI:");

                int max = -100, index = 0,min=100;
                for (int t = 0; t < 37; t++) {

                    System.out.print(counter[t]);


                    if (checkCounter[t] >= max) {

                        max = checkCounter[t];
                        index = t;
                    }

                    if(checkCounter[t]<=min) {

                        min=checkCounter[t];
                    }

                }

                minvalue=min;
                System.out.println();


                if (max > 0) {

                    max = counter[index];
                    drawLine = true;
                    //for(int l=0;l<250;l++) {

                    // System.out.println("best way array size:"+bestWay[index].size());
                    for (int t = 0; t < bestWay[index].size(); t += 3) {


                        ArrayList<Integer> multiLineXY = new ArrayList<Integer>();
                        int sour = bestWay[index].get(t);
                        int dest = bestWay[index].get(t + 2);

                        multiLineXY.add(arr[sour][0]);
                        multiLineXY.add(arr[sour][1]);


                        multiLineXY.add(arr[dest][0]);
                        multiLineXY.add(arr[dest][1]);


                        multiLineDraw.add(multiLineXY);

                        ploy1 = true;
                        ploy2 = false;


                    }

                    //setMultiLine();

                    System.out.println("azazazzooooooooooooooooooooooooooooooooogggggggggg1");
                    try {
                        thread.sleep(700);
                    } catch (InterruptedException ie) {

                    }


                    //run();


                    // }


                    // if(counter1>250) {


                    for (int t = 0; t < bestWay[index].size(); t += 3) {


                        int sour = bestWay[index].get(t);
                        int mid = bestWay[index].get(t + 1);
                        int dest = bestWay[index].get(t + 2);

                        arr[sour][2] = 0;
                        arr[mid][2] = 0;
                        arr[dest][2] = 2;


                        System.out.println("suboooooooooooooooooooooooooo");

                        team1Move = true;
                        team2Move = false;
                        mouse = false;


                        Atflag = false;
                        drawLine = false;


                        ploy2 = false;

                        A = -100;
                        B = -100;

                        soundplay.hitSound();


                        totalpawn1--;

                        multiLineDraw.removeAll(multiLineDraw);

                    }

                    System.out.println("bestWay" + bestWay[index]);


                }
                // }
                else {

                    if (!multiLineDraw.isEmpty()) {
                        multiLineDraw.removeAll(multiLineDraw);


                        team1Move = true;
                        team2Move = false;
                        mouse = false;


                        ploy1 = false;
                        ploy2 = true;
                        drawLine = false;


                    }

                }


                counter1++;
                if (counter1 > 251) counter1 = 0;
            }


            if (ploy2 == true) {


                boolean saveflag = true, safePlaceFlag = true, hotspotPointflag = true, otherflag = true;


                //making object
                DonotBeFool dbf=new DonotBeFool(totalpawn1,totalpawn2);

                BestPawn bestpawn = new BestPawn(arr, path);

                MakeFool mf = new MakeFool(arr, path);

                Apprehend aprrehend = new Apprehend(arr, path);

                MinimaxForHardLevel min=new MinimaxForHardLevel(path);


                //SafePosition safeposition=new SafePosition(arr,path);

                if(minvalue>=0&&dbf.beClever(counter2,arr,path)) {

                    counter2++;

                    drawLine2=true;


                        AX=dbf.AX;
                        AY=dbf.AY;
                        BX=dbf.BX;
                        BY=dbf.BY;


                        try {
                            thread.sleep(700);
                        } catch (InterruptedException ie) {

                        }

                        arr[dbf.source][2]=0;
                        arr[dbf.middle][2]=0;
                        arr[dbf.destination][2]=2;

                        soundplay.hitSound();


                        counter2=0;

                        Atflag=false;
                        ploy1=false;

                        team1Move=true;
                        team2Move=false;
                        mouse=false;

                        drawLine2=false;

                        totalpawn1--;

                        A=-100;
                        B=-100;


                        System.out.println("dont be fool   save gutiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");




                }

                else if (bestpawn.saveGuti(counter2, thread)) {


                    counter2++;

                    drawLine2 = true;
                    // if(counter2<150) {

                    AX = bestpawn.AX;
                    AY = bestpawn.AY;
                    BX = bestpawn.BX;
                    BY = bestpawn.BY;


                    try {
                        thread.sleep(700);
                    } catch (InterruptedException ie) {

                    }


                    //}

                    //if(counter2>151) {

                    soundplay.hitSound();

                    arr[bestpawn.source][2] = 0;
                    arr[bestpawn.destination][2] = 2;

                    counter2 = 0;

                    Atflag = false;
                    ploy1 = false;

                    team1Move = true;
                    team2Move = false;
                    mouse = false;

                    drawLine2 = false;


                    System.out.println("helloooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooAZAZ");
                    // }


                }

				/*if(safePlaceFlag==true) {




						boolean flagForNextCheck=true;

						B:for(int i=0;i<74;i++) {




										int element=queue.poll();
										if(arr[element][2]==2) {

										for(int j=0;j<8;j++) {

											int s=1,temp=0,tempX1=0,tempX2=0,tempX3;

											tempX1=element;

											temp=path[element][j];
											tempX2=temp;


											if(temp!=-1&&path[temp][j]==-1) {
											if(path[element][j]!=-1&&arr[path[element][j]][2]==0) {

												System.out.println("element"+element);
												if(!bestpawn.checkDangerPosition(temp)&&!bestpawn.checkDangerTomove(tempX1)) {

														System.out.println("88888"+counter2);

														counter2++;

														drawLine2=true;

														if(counter2==1) {

															AX=arr[tempX1][0];
															AY=arr[tempX1][1];
															BX=arr[tempX2][0];
															BY=arr[tempX2][1];

															tmp1=tempX1;
															tmp2=tempX2;



															System.out.println(tmp1+" "+tmp2+"azzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");

														}
														if(counter2>151) {


															counter2=0;

															Atflag=false;
															ploy1=false;

															team1Move=true;
															team2Move=false;
															mouse=false;

															drawLine2=false;

															arr[tmp1][2]=0;
															arr[tmp2][2]=2;

															System.out.println(tmp1+" "+"azzzzzzzzzzzzzzzzzzzzz2");
														}

														hotspotPointflag=false;
														otherflag=false;

														flagForNextCheck=false;


														queue.add(element);




														break B;
												}

											}
											}

										}

										}
										queue.add(element);





							}











						if(flagForNextCheck==true) {

								B:for(int i=0;i<74;i++) {





									int element=queue.poll();
									if(arr[element][2]==2) {

									for(int j=0;j<8;j++) {

										int s=1,temp=0,tempX1=0,tempX2=0,tempX3;

										tempX1=element;

										temp=path[element][j];
										tempX2=temp;



										if(temp!=-1&&path[temp][j]!=-1) {
										if(path[element][j]!=-1&&arr[path[element][j]][2]==0&&arr[path[temp][j]][2]!=1) {


											System.out.println("mmmmmmmmmmm"+element);
											if(!bestpawn.checkDangerPosition(temp)&&!bestpawn.checkDangerTomove(tempX1)) {



													counter2++;

													drawLine2=true;
													if(counter2==1) {

														AX=arr[tempX1][0];
														AY=arr[tempX1][1];
														BX=arr[tempX2][0];
														BY=arr[tempX2][1];

														tmp1=tempX1;
														tmp2=tempX2;


														System.out.println(tmp1+" "+tmp2+"subbbbbbbbbbbbooooooooooooooooooooooooooooooooooooooo");

													}
													if(counter2>151) {


														counter2=0;

														Atflag=false;
														ploy1=false;

														team1Move=true;
														team2Move=false;
														mouse=false;

														drawLine2=false;

														arr[tmp1][2]=0;
														arr[tmp2][2]=2;

														System.out.println(tmp1+" "+tmp2+"suboooooooooooooooo2");
													}



													hotspotPointflag=false;
													otherflag=false;





													System.out.println("2222222");


													queue.add(element);

													break B;

											}

										}

										}


									}

									}
									queue.add(element);




								}
						}

						System.out.println("flagForNextCheck"+flagForNextCheck+counter2);






				}*/


                else if (mf.makeFool()) {

                    drawLine2 = true;

                    counter2++;

                    // if(counter2<150) {

                    AX = arr[mf.source][0];
                    AY = arr[mf.source][1];
                    BX = arr[mf.destination][0];
                    BY = arr[mf.destination][1];


                    try {
                        thread.sleep(700);
                    } catch (InterruptedException ie) {

                    }


                    //}

                    //if(counter2>151) {

                    soundplay.hitSound();

                    arr[mf.source][2] = 0;
                    arr[mf.destination][2] = 2;


                    counter2 = 0;

                    Atflag = false;
                    ploy1 = false;

                    team1Move = true;
                    team2Move = false;
                    mouse = false;

                    drawLine2 = false;


                    System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiAZAZ");
                    //}
                }

                else if(min.findBestMove(arr,queue,controlDoubleployForSource,controlDoubleployForDestination)>=0) {

                    drawLine2=true;

                    counter2++;

                    //if(counter2<150) {


                    AX=arr[min.source][0];
                    AY=arr[min.source][1];
                    BX=arr[min.destination][0];
                    BY=arr[min.destination][1];
                    //}

                    //if(counter2>151) {

                    try {
                        thread.sleep(700);
                    } catch (InterruptedException ie) {

                    }



                    arr[min.source][2]=0;
                    arr[min.destination][2]=2;

                    controlDoubleployForSource=min.source;
                    controlDoubleployForDestination=min.destination;




                    counter2=0;

                    Atflag=false;
                    ploy1=false;

                    team1Move=true;
                    team2Move=false;
                    mouse=false;

                    drawLine2=false;



                    System.out.println("most efficient move");

                    soundplay.hitSound();


                    //}

                }



                else if ((totalpawn2 - totalpawn1) >= 2 && aprrehend.toCatch(counter2)) {


                    drawLine2 = true;

                    counter2++;

                    //if(counter2<150) {

                    AX = arr[aprrehend.source][0];
                    AY = arr[aprrehend.source][1];
                    BX = arr[aprrehend.destination][0];
                    BY = arr[aprrehend.destination][1];


                    try {
                        thread.sleep(700);
                    } catch (InterruptedException ie) {

                    }


                    // }

                    // if(counter2>151) {

                    soundplay.hitSound();
                    arr[aprrehend.source][2] = 0;
                    arr[aprrehend.destination][2] = 2;


                    counter2 = 0;

                    Atflag = false;
                    ploy1 = false;

                    team1Move = true;
                    team2Move = false;
                    mouse = false;

                    drawLine2 = false;


                    System.out.println("ApprehendAZAZ");
                    //}


                } else if (bestpawn.checkHotspotPosition(counter2)) {


                    System.out.println("hotspotshhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

                    drawLine2 = true;

                    counter2++;

                    // if(counter2<150) {

                    AX = bestpawn.AX;
                    AY = bestpawn.AY;
                    BX = bestpawn.BX;
                    BY = bestpawn.BY;


                    try {
                        thread.sleep(700);
                    } catch (InterruptedException ie) {

                    }


                    //}

                    // if(counter2>151) {


                    soundplay.hitSound();
                    arr[bestpawn.source][2] = 0;
                    arr[bestpawn.destination][2] = 2;


                    counter2 = 0;

                    Atflag = false;
                    ploy1 = false;

                    team1Move = true;
                    team2Move = false;
                    mouse = false;

                    drawLine2 = false;


                    System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiAZAZ");
                    //}


                    otherflag = false;



                } else {


                    System.out.println("othersssss");

                    int o;

                    boolean flagForNextCheck = true, flagForNextCheck2 = true, flagForNextCheck3 = true;

                    B:
                    for (int i = 0; i < 74; i++) {


                        int element = queue.poll();
                        if (arr[element][2] == 2) {

                            if ((element == 16 && (arr[12][2] == 1 || arr[22][2] == 1)) || (element == 20 && (arr[14][2] == 1 || arr[24][2] == 1)) || (element == 6 && (arr[7][2] == 1 || arr[11][2] == 1 || arr[12][2] == 1)) || (element == 26 && (arr[21][2] == 1 || arr[27][2] == 1 || arr[22][2] == 1))
                                    || (element == 30 && (arr[29][2] == 1 || arr[25][2] == 1 || arr[24][2] == 1)) || (element == 10 && (arr[9][2] == 1 || arr[14][2] == 1 || arr[15][2] == 1))) {


                                System.out.println("azzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzd1111111111111111111111111");
                                queue.add(element);
                                continue;
                            }


                            for (int j = 0; j < 8; j++) {

                                int s = 1, temp = 0, tempX1 = 0, tempX2 = 0, tempX3;

                                tempX1 = element;

                                temp = path[element][j];
                                tempX2 = temp;


                                if (temp != -1 && path[temp][j] != -1) {
                                    if (path[element][j] != -1 && arr[path[element][j]][2] == 0 && arr[path[temp][j]][2] != 1) {

                                        if (!bestpawn.checkDangerPosition(temp) && !bestpawn.checkDangerTomove(tempX1)) {


                                            counter2++;

                                            drawLine2 = true;
                                            // if(counter2==1) {

                                            AX = arr[tempX1][0];
                                            AY = arr[tempX1][1];
                                            BX = arr[tempX2][0];
                                            BY = arr[tempX2][1];

                                            tmp1 = tempX1;
                                            tmp2 = tempX2;


                                            try {
                                                thread.sleep(700);
                                            } catch (InterruptedException ie) {

                                            }


                                            System.out.println(tmp1 + " " + tmp2 + "wowwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww1");

                                            //}
                                            //if(counter2>150) {


                                            counter2 = 0;

                                            Atflag = false;
                                            ploy1 = false;

                                            team1Move = true;
                                            team2Move = false;
                                            mouse = false;

                                            drawLine2 = false;

                                            soundplay.hitSound();

                                            arr[tmp1][2] = 0;
                                            arr[tmp2][2] = 2;


                                            System.out.println(tmp1 + " " + tmp2 + "wowwwwwwwwwwwwwwwwwwwwwww2");
                                            // }

                                            flagForNextCheck = false;

                                            System.out.println("2222222");


                                            queue.add(element);

                                            break B;

                                        }


                                    }
                                }

                            }

                        }
                        queue.add(element);


                    }


                    System.out.println("counter" + counter2 + "  " + flagForNextCheck);


                    if (flagForNextCheck == true) {


                        B:
                        for (int i = 0; i < 74; i++) {


                            int element = queue.poll();
                            if (arr[element][2] == 2) {

                                if ((element == 16 && (arr[12][2] == 1 || arr[22][2] == 1)) || (element == 20 && (arr[14][2] == 1 || arr[24][2] == 1)) || (element == 6 && (arr[7][2] == 1 || arr[11][2] == 1 || arr[12][2] == 1)) || (element == 26 && (arr[21][2] == 1 || arr[27][2] == 1 || arr[22][2] == 1))
                                        || (element == 30 && (arr[29][2] == 1 || arr[25][2] == 1 || arr[24][2] == 1)) || (element == 10 && (arr[9][2] == 1 || arr[14][2] == 1 || arr[15][2] == 1))) {


                                    System.out.println("azzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzd222222222222222222222");
                                    queue.add(element);
                                    continue;
                                }

                                for (int j = 0; j < 8; j++) {

                                    int s = 1, temp = 0, tempX1 = 0, tempX2 = 0, tempX3;

                                    tempX1 = element;

                                    temp = path[element][j];
                                    tempX2 = temp;

                                    if (temp != -1 && path[temp][j] == -1) {
                                        if (path[element][j] != -1 && arr[path[element][j]][2] == 0) {

                                            if (!bestpawn.checkDangerPosition(temp) && !bestpawn.checkDangerTomove(tempX1)) {


                                                System.out.println("444444");

                                                counter2++;

                                                drawLine2 = true;

                                                // if(counter2==1) {

                                                AX = arr[tempX1][0];
                                                AY = arr[tempX1][1];
                                                BX = arr[tempX2][0];
                                                BY = arr[tempX2][1];

                                                tmp1 = tempX1;
                                                tmp2 = tempX2;


                                                try {
                                                    thread.sleep(700);
                                                } catch (InterruptedException ie) {

                                                }


                                                System.out.println(tmp1 + " " + tmp2 + "wowwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww3");

                                                // }
                                                // if(counter2>150) {


                                                counter2 = 0;

                                                Atflag = false;
                                                ploy1 = false;

                                                team1Move = true;
                                                team2Move = false;
                                                mouse = false;

                                                drawLine2 = false;

                                                soundplay.hitSound();

                                                arr[tmp1][2] = 0;
                                                arr[tmp2][2] = 2;


                                                System.out.println(tmp1 + " " + tmp2 + "wowwwwwwwwwwwwwwwwwwwwwww4");
                                                // }


                                                queue.add(element);

                                                flagForNextCheck2 = false;


                                                break B;
                                            }

                                        }
                                    }

                                }

                            }
                            queue.add(element);


                        }


                    }


                    if (flagForNextCheck2 == true && flagForNextCheck == true) {


                        B:
                        for (int i = 0; i < 74; i++) {


                            int element = queue.poll();
                            if (arr[element][2] == 2) {

                                if ((element == 16 && (arr[12][2] == 1 || arr[22][2] == 1)) || (element == 20 && (arr[14][2] == 1 || arr[24][2] == 1)) || (element == 6 && (arr[7][2] == 1 || arr[11][2] == 1 || arr[12][2] == 1)) || (element == 26 && (arr[21][2] == 1 || arr[27][2] == 1 || arr[22][2] == 1))
                                        || (element == 30 && (arr[29][2] == 1 || arr[25][2] == 1 || arr[24][2] == 1)) || (element == 10 && (arr[9][2] == 1 || arr[14][2] == 1 || arr[15][2] == 1))) {


                                    System.out.println("azzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzd33333333333");
                                    queue.add(element);
                                    continue;
                                }

                                for (int j = 0; j < 8; j++) {

                                    int s = 1, temp = 0, tempX1 = 0, tempX2 = 0, tempX3;

                                    tempX1 = element;

                                    temp = path[element][j];
                                    tempX2 = temp;


                                    if (temp != -1 && path[temp][j] != -1) {
                                        if (path[element][j] != -1 && arr[path[element][j]][2] == 0 && arr[path[temp][j]][2] != 1) {

                                            if (!bestpawn.checkDangerTomove(element)) {


                                                System.out.println("666666");

                                                counter2++;

                                                drawLine2 = true;

                                                // if(counter2==1) {

                                                AX = arr[tempX1][0];
                                                AY = arr[tempX1][1];
                                                BX = arr[tempX2][0];
                                                BY = arr[tempX2][1];

                                                tmp1 = tempX1;
                                                tmp2 = tempX2;


                                                try {
                                                    thread.sleep(700);
                                                } catch (InterruptedException ie) {

                                                }


                                                System.out.println(tmp1 + " " + tmp2 + "wowwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww5");

                                                // }
                                                // if(counter2>150) {


                                                counter2 = 0;

                                                Atflag = false;
                                                ploy1 = false;

                                                team1Move = true;
                                                team2Move = false;
                                                mouse = false;

                                                drawLine2 = false;

                                                soundplay.hitSound();

                                                arr[tmp1][2] = 0;
                                                arr[tmp2][2] = 2;


                                                System.out.println(tmp1 + " " + tmp2 + "wowwwwwwwwwwwwwwwwwwwwwww6");
                                                //}


                                                queue.add(element);

                                                flagForNextCheck3 = false;


                                                break B;
                                            }

                                        }
                                    }

                                }

                            }
                            queue.add(element);


                        }


                    }


                    if (flagForNextCheck2 == true && flagForNextCheck == true && flagForNextCheck3 == true) {


                        B:
                        for (int i = 0; i < 74; i++) {


                            int element = queue.poll();
                            if (arr[element][2] == 2) {

                                for (int j = 0; j < 8; j++) {

                                    int s = 1, temp = 0, tempX1 = 0, tempX2 = 0, tempX3;

                                    tempX1 = element;

                                    temp = path[element][j];
                                    tempX2 = temp;


                                    if (temp != -1 && path[temp][j] != -1) {
                                        if (path[element][j] != -1 && arr[path[element][j]][2] == 0) {


                                            System.out.println("other" + "88888");

                                            counter2++;

                                            drawLine2 = true;

                                            //if(counter2==1) {

                                            AX = arr[tempX1][0];
                                            AY = arr[tempX1][1];
                                            BX = arr[tempX2][0];
                                            BY = arr[tempX2][1];

                                            tmp1 = tempX1;
                                            tmp2 = tempX2;


                                            try {
                                                thread.sleep(700);
                                            } catch (InterruptedException ie) {

                                            }


                                            System.out.println(tmp1 + " " + tmp2 + "wowwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww7");

                                            // }
                                            //if(counter2>150) {


                                            counter2 = 0;

                                            Atflag = false;
                                            ploy1 = false;

                                            team1Move = true;
                                            team2Move = false;
                                            mouse = false;

                                            drawLine2 = false;

                                            soundplay.hitSound();

                                            arr[tmp1][2] = 0;
                                            arr[tmp2][2] = 2;


                                            System.out.println(tmp1 + " " + tmp2 + "wowwwwwwwwwwwwwwwwwwwwwww8");
                                            // }


                                            queue.add(element);


                                            break B;

                                        }
                                    }

                                }

                            }
                            queue.add(element);


                        }


                    }


                }


            }


        }


        showMessage();


        return false;
    }


    public void run() {


        while (isItok == true) {

            if (!holder.getSurface().isValid()) {
                continue;
            }


            canvas = holder.lockCanvas();


            canvas.drawBitmap(bitmap, 0, 0, paint);
            paint.setColor(Color.WHITE);

            /*Rect rect=new Rect(0,0,canvas.getWidth(),canvas.getHeight());
            canvas.drawRect(rect,paint);*/


            //System.out.println("canvas " + canvas.getWidth() + " " + canvas.getHeight() + "drawLine" + drawLine);


            Paint textpaint = new Paint();
            textpaint.setTextAlign(Paint.Align.CENTER);
            textpaint.setTypeface(Typeface.DEFAULT_BOLD);
            textpaint.setTextSize(30);
            textpaint.setColor(Color.BLUE);

            canvas.drawText("Me: " + totalpawn1, eachRoomWidth - 50, eachRoomHeight + 20, textpaint);

            textpaint.setColor(Color.GREEN);
            canvas.drawText("Op: " + totalpawn2, eachRoomWidth * 4 + 60, eachRoomHeight + 20, textpaint);

            textpaint.setTextSize(35);
            textpaint.setColor(Color.RED);
            canvas.drawText("Timer: " + time, eachRoomWidth * 3 - 40, (eachRoomHeight / 2) - 30, textpaint);


            //draw board
            paint.setStrokeWidth(4);
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


            paint.setColor(Color.BLUE);


            if (drawLine == true) {

                System.out.println("draw enter the run");
                for (ArrayList<Integer> arr : multiLineDraw) {

                    paint.setStrokeWidth(8);
                    paint.setColor(Color.RED);
                    canvas.drawLine(arr.get(0), arr.get(1), arr.get(2), arr.get(3), paint);
                }

                //holder.unlockCanvasAndPost(canvas);
                for (int l = 0; l < 30; l++) {

                }

                //drawLine=false;
                // break;

            }


            if (drawLine2 == true) {

                System.out.println("draw enter the run second hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");


                paint.setStrokeWidth(8);
                paint.setColor(Color.RED);
                canvas.drawLine(AX, AY, BX, BY, paint);


                //holder.unlockCanvasAndPost(canvas);
                for (int l = 0; l < 30; l++) {

                }

                //drawLine=false;
                // break;

            }


            //draw player


            for (int i = 0; i < 37; i++) {

                if (arr[i][2] > 0) {

                    if (arr[i][2] == 1) paint.setColor(Color.BLUE);
                    else paint.setColor(Color.GREEN);


                    canvas.drawCircle(arr[i][0] + 7, arr[i][1] + 7, 25, paint);

                }


            }


            if (click == true) {

                Paint circlePaint = new Paint();
                circlePaint.setStrokeWidth(8);
                circlePaint.setColor(Color.RED);
                circlePaint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(A + 7, B + 7, 25, circlePaint);
            }


            holder.unlockCanvasAndPost(canvas);


        }



    }


    public void pause() {

        isItok = false;

        while (true) {


            try {
                thread.join();
            } catch (InterruptedException i) {
                i.printStackTrace();
            }
            break;
        }
        thread = null;
    }

    public void resume() {

        isItok = true;

        thread = new Thread(this);
        thread.start();

    }

    public void startTimer() {

        timer = new CountDownTimer(lefttime, 1) {
            @Override
            public void onTick(long l) {

                timeCounter = 86400000 - l;

                hours = (int) ((timeCounter / 1000) / 3600);
                minutes = (int) ((timeCounter / 1000) / 60);
                seconds = (int) ((timeCounter / 1000) % 60);

                time = String.format("%02d:%02d:%02d\n", hours, minutes, seconds);


            }

            @Override
            public void onFinish() {

            }
        }.start();


    }

    public void showMessage() {

        if (totalpawn1 <= 0) {

            try {
                thread.sleep(100);
            } catch (InterruptedException e) {

            }

            timerIsRunning = false;
            timer.cancel();
            AlertDialog.Builder dialong = new AlertDialog.Builder(context);

            //dialong.setMessage("Sorry! try again").create();

            dialong.setMessage("   Sorry! try again").setPositiveButton("new game", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int e) {

                    for (int i = 0; i < 37; i++) {

                        arr[i][0] = position[i][0];
                        arr[i][1] = position[i][1];
                        arr[i][2] = position[i][2];


                    }

                    startTimer();
                    lefttime = 86400000;

                    timerIsRunning = true;

                    timeCounter = 0;
                    totalpawn1 = 16;
                    totalpawn2 = 16;

                    minutes = 0;
                    seconds = 0;
                    hours = 0;

                    A = -100;
                    B = -100;


                }


            }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    System.exit(0);
                }
            }).create();

            dialong.show();


        } else if (totalpawn2 <= 0) {


            Cursor data = DH.getListContents();

            int numofcol = data.getCount();
            System.out.println("column count" + numofcol);

            if (numofcol <= 0) {


                DH.addData("Oponent," + totalpawn2 + "," + timeCounter);

            } else {

                while (data.moveToNext()) {

                    String s = data.getString(1);

                    String[] str = s.split(",");

                    int num = Integer.parseInt(str[2]);

                    if (num > timeCounter) {
                        DH.deleteData();

                        DH.addData("Oponent," + totalpawn2 + "," + timeCounter);


                    }
                }


            }


            try {
                thread.sleep(100);
            } catch (InterruptedException e) {

            }

            timerIsRunning = false;
            timer.cancel();

            AlertDialog.Builder dialong = new AlertDialog.Builder(context);

            dialong.setMessage("     You have won the match\n" + "\t\t           by " + totalpawn1 + " beads\n" + "\t\t\t\t\t\t          in\n" + "\t\t" + minutes + " minutes and " + seconds + " seconds").setPositiveButton("new game", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int e) {

                    for (int i = 0; i < 37; i++) {

                        arr[i][0] = position[i][0];
                        arr[i][1] = position[i][1];
                        arr[i][2] = position[i][2];


                    }

                    startTimer();
                    lefttime = 86400000;

                    timerIsRunning = true;

                    timeCounter = 0;
                    totalpawn1 = 16;
                    totalpawn2 = 16;

                    minutes = 0;
                    seconds = 0;
                    hours = 0;

                    A = -100;
                    B = -100;


                }


            }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    System.exit(0);
                }
            }).create();

            dialong.show();


        }


    }
}




