package com.example.asus.shologutionlinegaming;

import java.util.Queue;


public class MinimaxForMiddleLevel {

    public boolean team1,team2;
    int[][] path=new int[37][8];
    public int source,destination,index=-10;

    public int currentSource,currentDestination,direction;

    public int callbySource=0,callbyDestination=0;

    String team=null;

    int initialGuti1=0,initialGuti2=0;


    public MinimaxForMiddleLevel(int [][] path) {

        this.path=path;

    }

    public int minimax(int[][] board,int alpha,int beta, int depth, boolean isMax)
    {


        //System.out.println("depthdephtttttttttttttttttttttttttttttt: "+depth);

        System.out.println("Call by "+team);

        System.out.println("callbySource: "+callbySource+" callbydestination: "+callbyDestination);


        int count1=0,count2=0;

        for(int i=0;i<board.length;i++) {


            if(board[i][2]==1) count1++;
            if(board[i][2]==2) count2++;




        }

			/*for(int i=0;i<37;i++) {

				if(i>=6&&i<=10) System.out.print(" ");
				if(i==0||i==3||i==31||i==34)System.out.print("            ");
				System.out.print(i+"("+board[i][2]+")"+"       ");
				if(i==2||i==5||i==10||i==15||i==20||i==25||i==30||i==33||i==36) System.out.println();


			}*/


        System.out.println();

        System.out.println(" number of pawn  count1: "+count1+"count2: "+count2);








        if(depth==0) {



            int evaluate=evaluation(board,path,currentSource,currentDestination,direction);

            return evaluate;
        }






        //if(count1<16) return 1;
        //if(count2<count1) return -1;





	/*	if(checkWhoWin(board,path,currentSource,currentDestination,direction)==1) {


				System.out.println("return from 1");


				return 1;
		}
		else if(checkWhoWin(board,path,currentSource,currentDestination,direction)==-1) {

			System.out.println("return from -1");

				return -1;
		}*/










        // If this maximizer's move
        if (isMax)
        {
            int newBeta=alpha;





            System.out.println("teammmmmmmmmmmmmmmmmmmmmmmmmmmmmmm222222222222222222222222222222222222222222222222222222222222222222222");

            // Traverse all cells
            for (int i = 0; i<37; i++)
            {

                int element=i;
                if(board[i][2]==2) {


                    for(int j=0;j<8;j++) {

                        int p=0;
                        int s=1,temp=0,tempX1=0,tempX2=0,tempX3;

                        tempX1=i;

                        temp=path[i][j];
                        tempX2=temp;



                        BestPawn bestpawn=new BestPawn(board,path);
                        if(temp!=-1) {



                            if(board[temp][2]==0/*&&!bestpawn.checkDangerPosition(temp)&&!bestpawn.checkDangerTomove(element)*/) {

                                board[i][2]=0;
                                //System.out.println("call fromm team 2"+i);
                                board[temp][2] = 2;


                                currentSource=i;
                                currentDestination=temp;
                                direction=j;

                                team="AI";

                                newBeta = Math.max( newBeta,minimax(board,alpha,beta, depth-1, !isMax) );

                                // System.out.println("best for team 2: "+best+"  depth:"+depth);

                                board[i][2]=2;
                                board[temp][2] =0;



                            }

                            else if(path[temp][j]!=-1&&board[temp][2]==1&&board[path[temp][j]][2]==0) {



                                int[][] Altarr=new int[37][3];
                                int[][] Altarr2=new int[37][3];


                                for(int k=0;k<Altarr.length;k++) {
                                    for(int l=0;l<3;l++) {

                                        Altarr[k][l]=board[k][l];
                                        Altarr2[k][l]=board[k][l];
                                    }

                                }

                                System.out.println("AI eatinggggggggggggggggggggggggggggggggggggggg function"+i);


                                int[] counter=new int[37];
                                int[] checkCounter=new int[37];

                                BestWayForMinimax bestway=new BestWayForMinimax(i,counter,checkCounter);

                                bestway.findBestWayForMinimax2(i, Altarr, path);



									/*MakeFool mf=new MakeFool(Altarr,path);
									mf.maxForTeam2(Altarr);*/

                                //Altarr[temp][2]=0;
                                //Altarr[path[temp][j]][2]=2;




                                newBeta= Math.max( newBeta,minimax(Altarr,-10000,10000, depth-1, !isMax) );

                                for(int k=0;k<Altarr.length;k++) {
                                    for(int l=0;l<3;l++) {

                                        board[k][l]=Altarr2[k][l];
                                    }

                                }


                            }

                        }

                    }


                }
            }

            team="AI";


            return newBeta;
        }


        else
        {
            int newAlpha =beta;



            System.out.println("teammmmmmmmmmmmmmmmmmmmmmmmmmmmmmm11111111111111111111111111111111111111111111111111111111111111111");

            for (int i = 0; i<37; i++)
            {

                int element=i;
                if(board[i][2]==1) {


                    for(int j=0;j<8;j++) {

                        int s=1,temp=0,tempX1=0,tempX2=0,tempX3;

                        tempX1=i;

                        temp=path[i][j];
                        tempX2=temp;

                        BestPawn bestpawn=new BestPawn(board,path);
                        if(temp!=-1) {

                            //System.out.println("call fromm team 1"+i);
                            if(board[temp][2]==0/*&&!bestpawn.checkDangerPosition1(temp)&&!bestpawn.checkDangerTomove1(element)*/) {
                                // Make the move

                                board[i][2]=0;
                                board[temp][2] = 1;



                                currentSource=i;
                                currentDestination=temp;
                                direction=j;

                                team="Human";



                                newAlpha = Math.min( newAlpha, minimax(board,alpha,beta, depth-1, !isMax) );
                                System.out.println("best for team 1:  "+newAlpha);


                                board[temp][2]=0;
                                board[i][2]=1;


                            }

                            else if(path[temp][j]!=-1&&board[temp][2]==2&&board[path[temp][j]][2]==0) {



                                int[][] Altarr=new int[37][3];
                                int[][] Altarr2=new int[37][3];

                                for(int k=0;k<Altarr.length;k++) {
                                    for(int l=0;l<3;l++) {

                                        Altarr[k][l]=board[k][l];
                                        Altarr2[k][l]=board[k][l];

                                    }

                                }

                                System.out.println("AI eatinggggggggggggggggggggggggggggggggggggggg function"+i);

										/*MakeFool mf=new MakeFool(Altarr,path);
										mf.maxForTeam1(Altarr);*/

                                int[] counter=new int[37];
                                int[] checkCounter=new int[37];

                                BestWayForMinimax bestway=new BestWayForMinimax(i,counter,checkCounter);

                                bestway.findBestWayForMinimax1(i, Altarr, path);


                                //Altarr[temp][2]=0;
                                //Altarr[path[temp][j]][2]=1;



                                newAlpha = Math.min( newAlpha,minimax(Altarr,-10000,10000,depth-1, !isMax) );

                                for(int k=0;k<Altarr.length;k++) {
                                    for(int l=0;l<3;l++) {

                                        board[k][l]=Altarr2[k][l];
                                    }

                                }


                            }

                        }






                    }
                }

            }

            team="Human";


            return newAlpha;

        }

    }


    int findBestMove(int[][] arr,Queue<Integer> queue,int controlDoubleployForSource,int controlDoubleployForDestination)
    {
        int bestVal = -10000000;



        int[][] board=new int[37][3];
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<3;j++) {

                board[i][j]=arr[i][j];
            }

        }



        int count1=0,count2=0;
        for(int i=0;i<board.length;i++) {


            if(board[i][2]==1) count1++;
            if(board[i][2]==2) count2++;


        }

        initialGuti1=count1;
        initialGuti2=count2;


        boolean firstcheck1=false,firstcheck2=false;

        A:for (int i =36; i>=0; i--)
        {

            int element= i;  //queue.poll();




            if(board[element][2]==2) {

                if((element==16&&(arr[12][2]==1||arr[22][2]==1))||(element==20&&(arr[14][2]==1||arr[24][2]==1))||(element==6&&(arr[7][2]==1||arr[11][2]==1||arr[12][2]==1))||(element==26&&(arr[21][2]==1||arr[27][2]==1||arr[22][2]==1))
                        ||(element==30&&(arr[29][2]==1||arr[25][2]==1||arr[24][2]==1))||(element==10&&(arr[9][2]==1||arr[14][2]==1||arr[15][2]==1))||(element==16&&(arr[10][2]==1||arr[30][2]==1))||(element==20&&(arr[6][2]==1||arr[26][2]==1))) {


                    System.out.println("azzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzd1111111111111111111111111");

                    continue;
                }

                if(element==controlDoubleployForSource||element==controlDoubleployForDestination) {

                    System.out.println("double ploy "+i);
                    continue A;
                }

                for(int j=7;j>=0;j--) {

                    int s=1,temp=0,tempX1=0,tempX2=0,tempX3;

                    tempX1=element;

                    temp=path[element][j];
                    tempX2=temp;






                    board[element][2]=0;
                    BestPawn bestpawn=new BestPawn(board,path);

                    if(temp!=-1) {
                        if(board[temp][2]==0/*&&!bestpawn.checkDangerPosition(temp)&&!bestpawn.checkDangerTomove(element)*/) {


                            firstcheck1=true;
                            // Make the move
                            board[temp][2] = 2;



                            currentSource=i;
                            currentDestination=temp;

                            callbySource=i;
                            callbyDestination=temp;

                            System.out.println("callcalllbybbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb by"+element);

                            int moveVal = minimax(board, -1000000,1000000,1,false);

                            //   System.out.println("return valuevvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv:"+moveVal);

                            // Undo the move

                            board[temp][2] =0;


                            System.out.println("return valueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee: "+moveVal);

                            if (moveVal > bestVal)
                            {

                                System.out.println("checkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk best for move element"+element );
                                index=element;
                                bestVal = moveVal;

                                source=element;
                                destination=temp;

                            }
                        }

                    }

                    board[element][2]=2;




                }

            }

            //queue.add(element);
        }

        if(firstcheck1==false) {
            for (int i = 36; i>=0; i--)
            {

                int element= i;  //queue.poll();

                if((element==16&&(arr[12][2]==1||arr[22][2]==1))||(element==20&&(arr[14][2]==1||arr[24][2]==1))||(element==6&&(arr[7][2]==1||arr[11][2]==1||arr[12][2]==1))||(element==26&&(arr[21][2]==1||arr[27][2]==1||arr[22][2]==1))
                        ||(element==30&&(arr[29][2]==1||arr[25][2]==1||arr[24][2]==1))||(element==10&&(arr[9][2]==1||arr[14][2]==1||arr[15][2]==1))||(element==16&&(arr[10][2]==1||arr[30][2]==1))||(element==20&&(arr[6][2]==1||arr[26][2]==1))) {


                    System.out.println("azzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzd1111111111111111111111111");

                    continue;
                }


                if(board[element][2]==2) {

                    A:for(int j=7;j>=0;j--) {

                        int s=1,temp=0,tempX1=0,tempX2=0,tempX3;

                        tempX1=element;

                        temp=path[element][j];
                        tempX2=temp;




                        if(temp==controlDoubleployForSource&&element==controlDoubleployForDestination) {

                            System.out.println("double ploy "+i);
                            continue A;
                        }


                        board[element][2]=0;
                        BestPawn bestpawn=new BestPawn(board,path);

                        if(temp!=-1) {
                            if(board[temp][2]==0&&!bestpawn.checkDangerPosition(temp)&&!bestpawn.checkDangerTomove(element)) {

                                firstcheck2=true;


                                // Make the move
                                board[temp][2] = 2;



                                currentSource=i;
                                currentDestination=temp;

                                callbySource=i;
                                callbyDestination=temp;

                                System.out.println("callcalllbybbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb by"+element);

                                int moveVal = minimax(board,-10000,10000, 1, false);

                                //   System.out.println("return valuevvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv:"+moveVal);

                                // Undo the move

                                board[temp][2] =0;


                                System.out.println("return valueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee: "+moveVal);

                                if (moveVal > bestVal)
                                {

                                    System.out.println("checkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk best for move element"+element );
                                    index=element;
                                    bestVal = moveVal;

                                    source=element;
                                    destination=temp;

                                }
                            }

                        }

                        board[element][2]=2;




                    }

                }

                //queue.add(element);
            }


        }







        if(firstcheck2==false&&firstcheck1==false) {
            A:for (int i = 36; i>=0; i--)
            {



                int element= i;  //queue.poll();




                if(board[element][2]==2) {

                    if(element==controlDoubleployForSource||element==controlDoubleployForDestination) {

                        System.out.println("double ploy "+i);
                        continue A;
                    }

                    for(int j=7;j>=0;j--) {

                        int s=1,temp=0,tempX1=0,tempX2=0,tempX3;

                        tempX1=element;

                        temp=path[element][j];
                        tempX2=temp;





                        if(temp!=-1) {
                            if(board[temp][2]==0) {


                                // Make the move
                                board[element][2]=0;
                                board[temp][2] = 2;



                                currentSource=i;
                                currentDestination=temp;

                                callbySource=i;
                                callbyDestination=temp;

                                System.out.println("callcalllbybbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb by"+element);

                                int moveVal = minimax(board,-10000,10000, 1, false);

                                //   System.out.println("return valuevvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv:"+moveVal);

                                // Undo the move
                                board[temp][2] =0;

                                board[element][2]=2;


                                System.out.println("return valueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee: "+moveVal);

                                if (moveVal > bestVal)
                                {

                                    System.out.println("checkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk best for move element"+element );
                                    index=element;
                                    bestVal = moveVal;

                                    source=element;
                                    destination=temp;

                                }
                            }

                        }




                    }

                }

                //queue.add(element);
            }


        }

        System.out.println("The Optimal Move is   source: "+index+"   desination: "+destination);
        System.out.println("final max value: "+bestVal);






        System.out.println("return from: "+team);

        return index;
    }




    public int  evaluation(int[][] Altarr,int[][] path,int currentSource,int currentDestination,int direction) {




        int[][]  Altarr2=new int[37][3];


        BestPawn bestpawn=new BestPawn(Altarr2,path);


        for(int m=0;m<Altarr2.length;m++) {

            for(int n=0;n<Altarr2[0].length;n++) {

                Altarr2[m][n]=Altarr[m][n];


            }

        }




        int num1=0,num2=0;

		/*MakeFool mk1=new MakeFool(Altarr2,path);

		num1+=mk1.maxForTeam1(Altarr2);

		num2+=mk1.maxForTeam2(Altarr2);*/






        int count1=0,count2=0;
        for(int i=0;i<Altarr2.length;i++) {


            if(Altarr2[i][2]==1) count1++;
            if(Altarr2[i][2]==2) count2++;




        }

        count1=initialGuti1-count1;
        count2=initialGuti2-count2;



        int AIscore=0,AIscore2=0,AIscore3=0,AIscore4=0,AIscore5=0,AIscore6=0,humanscore=0,humanscore2=0,humanscore3=0,humanscore4=0,humanscore5=0;




        System.out.println("return value: "+(count1*10-count2*20));

        for(int i=0;i<37;i++) {

            int element=i;
            if(Altarr2[i][2]==2) {

                if(Altarr2[i][2]==2&&bestpawn.checkDangerPosition(i)) humanscore+=6;

                if(!bestpawn.checkDangerPosition(i)&&((element==16&&(Altarr2[12][2]==1||Altarr2[22][2]==1))||(element==20&&(Altarr2[14][2]==1||Altarr2[24][2]==1))||(element==6&&(Altarr2[7][2]==1||Altarr2[11][2]==1||Altarr2[12][2]==1))||(element==26&&(Altarr2[21][2]==1||Altarr2[27][2]==1||Altarr2[22][2]==1))
                        ||(element==30&&(Altarr2[29][2]==1||Altarr2[25][2]==1||Altarr2[24][2]==1))||(element==10&&(Altarr2[9][2]==1||Altarr2[14][2]==1||Altarr2[15][2]==1))||(element==16&&(Altarr2[10][2]==1||Altarr2[30][2]==1))||(element==20&&(Altarr2[6][2]==1||Altarr2[26][2]==1)))) {

                    AIscore+=10;

                }


                if(count1>count2&&(i==6||i==10||i==16||i==20||i==30||i==26)) AIscore+=6;

            }

            else if(Altarr2[i][2]==1) {

                if(Altarr2[i][2]==1&&bestpawn.checkDangerPosition1(i)) AIscore+=6;


                if(i==6||i==10||i==16||i==20||i==30||i==26) humanscore+=6;
            }
        }



        if(initialGuti1<8&&(initialGuti2-initialGuti1)>=2) {


            if((Altarr2[33][2]==2||Altarr2[36][2]==2)&&(Altarr2[31][2]==1||Altarr2[32][2]==1||Altarr2[34][2]==1||Altarr2[35][2]==1)) {

                AIscore+=20;

            }
            if((Altarr2[34][2]==2||Altarr2[31][2]==2)&&(Altarr2[33][2]==1||Altarr2[36][2]==1||Altarr2[32][2]==1||Altarr2[35][2]==1)) {

                AIscore+=20;
            }

            if((Altarr2[0][2]==2||Altarr2[3][2]==2)&&(Altarr2[1][2]==1||Altarr2[2][2]==1||Altarr2[4][2]==1||Altarr2[5][2]==1)) {

                AIscore+=20;
            }
            if((Altarr2[2][2]==2||Altarr2[5][2]==2)&&(Altarr2[3][2]==1||Altarr2[4][2]==1||Altarr2[1][2]==1||Altarr2[0][2]==1)) {

                AIscore+=20;
            }




            if(((Altarr2[34][2]==2||Altarr2[36][2]==2)&&Altarr2[28][2]==2)&&(Altarr2[34][2]==1||Altarr2[36][2]==1)) {

                AIscore+=30;

            }



            if(((Altarr2[0][2]==2||Altarr2[2][2]==2)&&Altarr2[8][2]==2)&&(Altarr2[0][2]==1||Altarr2[2][2]==1)) {

                AIscore+=30;

            }



        }







        for(int i=0;i<37;i++) {
            for(int j=0;j<8;j++) {



                int s=1,temp=0,tempX1=0,tempX2=0,tempX3=-1,lastPosition=-1;

                int tempX4=-1,tempX5=-1,tempX6=-1;

                tempX1=i;

                temp=path[i][j];
                tempX2=temp;


                if(temp!=-1) tempX3=path[temp][j];
                if(tempX3!=-1) tempX4=path[tempX3][j];
                if(tempX4!=-1) tempX5=path[tempX4][j];
                if(tempX5!=-1) tempX6=path[tempX5][j];



                if(temp!=-1&&path[temp][j]!=-1&&initialGuti1<8&&(initialGuti2-initialGuti1)>=2) {
                    if(path[i][j]!=-1&&Altarr2[path[i][j]][2]==0&&Altarr2[path[temp][j]][2]==2&&i!=18) {

                        AIscore+=12;

                        //System.out.println("1 step Ai"+i);

                    }

                    else if(tempX4!=-1&&path[i][j]!=-1&&Altarr2[path[i][j]][2]==0&&Altarr2[path[temp][j]][2]==0&&Altarr2[tempX4][2]==2) {

                        AIscore+=9;

                        //System.out.println("2 step Ai"+i);

                    }

                    else if(tempX4!=-1&&tempX5!=-1&&path[i][j]!=-1&&Altarr2[path[i][j]][2]==0&&Altarr2[path[temp][j]][2]==0&&Altarr2[tempX4][2]==0&&Altarr2[tempX5][2]==2) {

                        AIscore+=6;
                        humanscore+=3;

                        //System.out.println("3 step AI"+i);

                    }

                    else if(tempX4!=-1&&tempX5!=-1&&tempX6!=-1&&path[i][j]!=-1&&Altarr2[path[i][j]][2]==0&&Altarr2[path[temp][j]][2]==0&&Altarr2[tempX4][2]==0&&Altarr2[tempX5][2]==0&&Altarr2[tempX6][2]==2) {

                        AIscore+=3;
                        humanscore+=6;


                        //System.out.println("4 step AI"+i);

                    }



                    int element=i;

                    if((element==16&&(Altarr2[12][2]==1||Altarr2[22][2]==1))||(element==20&&(Altarr2[14][2]==1||Altarr2[24][2]==1))||(element==6&&(Altarr2[7][2]==1||Altarr2[11][2]==1||Altarr2[12][2]==1))||(element==26&&(Altarr2[21][2]==1||Altarr2[27][2]==1||Altarr2[22][2]==1))
                            ||(element==30&&(Altarr2[29][2]==1||Altarr2[25][2]==1||Altarr2[24][2]==1))||(element==10&&(Altarr2[9][2]==1||Altarr2[14][2]==1||Altarr2[15][2]==1))||(element==16&&(Altarr2[10][2]==1||Altarr2[30][2]==1))||(element==20&&(Altarr2[6][2]==1||Altarr2[26][2]==1))) {

                        AIscore+=6;

                    }


                }
            }


        }


        System.out.println("AI TOTAL SCORE: 1: "+AIscore+" 2:"+count1*100);
        System.out.println("HUMAN TOTAL SCORE: 1: "+humanscore+" 2:"+count2*100);


        int total=(AIscore-humanscore)+count1*1000-count2*1000;







        return total;

































		/*int AIscore1=0,AIscore2=0,AIscore3=0,AIscore4=0,AIscore5=0,AIscore6=0,humanscore1=0,humanscore2=0,humanscore3=0,humanscore4=0,humanscore5=0;


		for(int i=0;i<37;i++) {


			if(Altarr2[i][2]==2) {

				if(Altarr2[i][2]==2&&!bestpawn.checkDangerPosition(i)) AIscore5++;
				else {


					humanscore1+=50;

				}


				if(count1>count2&&(i==6||i==10||i==16||i==20||i==30||i==26)) AIscore3+=10;
				if(count1<count2&&(i==6||i==10||i==16||i==20||i==30||i==26)) AIscore3+=2;

				if((i==28||i==23)&&(Altarr2[26][2]==1&&Altarr2[30][2]==1&&(Altarr2[34][2]==2||Altarr2[36][2]==2))) AIscore3+=20;
				if((i==28||i==23)&&(Altarr2[26][2]==1&&Altarr2[30][2]==1)) {
					AIscore3+=20;
					System.out.println("28 or 23 aprreeeeeeeeeeeeeeeee");
				}
				if((i==8||i==13)&&(Altarr2[6][2]==1&&Altarr2[10][2]==1&&(Altarr2[0][2]==2||Altarr2[2][2]==2))) AIscore3+=20;
				if((i==8||i==13)&&(Altarr2[6][2]==1&&Altarr2[10][2]==1)) AIscore3+=20;


				if((i==16||i==17)&&(Altarr2[6][2]==1&&Altarr2[26][2]==1)) AIscore3+=20;
				if((i==19||i==20)&&(Altarr2[10][2]==1&&Altarr2[30][2]==1)) AIscore3+=20;


				if(Altarr2[0][2]!=1&&Altarr2[1][2]!=1&&Altarr2[2][2]!=1&&Altarr2[3][2]!=1&&Altarr2[4][2]!=1&&Altarr2[5][2]!=1&&(i==0||i==1||i==2)) {

					AIscore5-=6;
				}
				else if(Altarr2[0][2]!=1&&Altarr2[1][2]!=1&&Altarr2[2][2]!=1&&Altarr2[3][2]!=1&&Altarr2[4][2]!=1&&Altarr2[5][2]!=1&&(i==3||i==4||i==5)) {

					AIscore5-=4;
				}
				else if(Altarr2[0][2]!=1&&Altarr2[1][2]!=1&&Altarr2[2][2]!=1&&Altarr2[3][2]!=1&&Altarr2[4][2]!=1&&Altarr2[5][2]!=1&&i==8) AIscore5-=2;

				else if(Altarr2[31][2]!=1&&Altarr2[32][2]!=1&&Altarr2[33][2]!=1&&Altarr2[34][2]!=1&&Altarr2[35][2]!=1&&Altarr2[36][2]!=1&&(i==34||i==35||i==36)) {

					AIscore5-=2;
				}
				else if(Altarr2[31][2]!=1&&Altarr2[32][2]!=1&&Altarr2[33][2]!=1&&Altarr2[34][2]!=1&&Altarr2[35][2]!=1&&Altarr2[36][2]!=1&&(i==31||i==32||i==33)) {

					AIscore5-=4;
				}
				else if(Altarr2[31][2]!=1&&Altarr2[32][2]!=1&&Altarr2[33][2]!=1&&Altarr2[34][2]!=1&&Altarr2[35][2]!=1&&Altarr2[36][2]!=1&&i==28) AIscore5-=6;


				for(int j=0;j<8;j++) {





					int s=1,temp=0,tempX1=0,tempX2=0,tempX3=0,lastPosition=-1;



					tempX1=i;
					int tempX4=-1,tempX5=-1,tempX6=-1;

					temp=path[i][j];
					tempX2=temp;
					if(temp!=-1) tempX3=path[temp][j];
					if(tempX3!=-1) tempX4=path[tempX3][j];
					if(tempX4!=-1) tempX5=path[tempX4][j];
					if(tempX5!=-1) tempX6=path[tempX5][j];


					if(i==18) AIscore6-=50;
					if(temp!=-1&&j<4&&path[i][j+4]!=-1&&Altarr2[path[i][j+4]][2]==1&&Altarr2[temp][2]==1) AIscore6+=50;
					if(temp!=-1&&j>=4&&path[i][j-4]!=-1&&Altarr2[path[i][j-4]][2]==1&&Altarr2[temp][2]==1) AIscore6+=50;

					if(temp!=-1&&path[temp][j]!=-1) {
						if(path[i][j]!=-1&&Altarr2[path[i][j]][2]==0&&Altarr2[path[temp][j]][2]==1) {


							humanscore2+=4;

							//System.out.println("1 step hum"+i);

						}
						else if(tempX4!=-1&&path[i][j]!=-1&&Altarr2[path[i][j]][2]==0&&Altarr2[path[temp][j]][2]==0&&Altarr2[tempX4][2]==1) {

							humanscore4+=8;

							//System.out.println("2 step hum"+i);

						}

						else if(tempX4!=-1&&tempX5!=-1&&path[i][j]!=-1&&Altarr2[path[i][j]][2]==0&&Altarr2[path[temp][j]][2]==0&&Altarr2[tempX4][2]==0&&Altarr2[tempX5][2]==1) {

							//System.out.println("3 step hum"+i);
							humanscore2+=16;

						}

						else if(tempX4!=-1&&tempX5!=-1&&tempX6!=-1&&path[i][j]!=-1&&Altarr2[path[i][j]][2]==0&&Altarr2[path[temp][j]][2]==0&&Altarr2[tempX4][2]==0&&Altarr2[tempX5][2]==0&&Altarr2[tempX6][2]==1) {

							//System.out.println("4 step hum"+i);
							humanscore4+=32;

						}

					}

				}
			}


			else if(Altarr2[i][2]==1) {




					for(int j=0;j<8;j++) {



							int s=1,temp=0,tempX1=0,tempX2=0,tempX3,lastPosition=-1;



							tempX1=i;

							temp=path[i][j];
							tempX2=temp;


							if(temp!=-1&&path[temp][j]!=-1) {
								if(path[i][j]!=-1&&Altarr[path[i][j]][2]==0&&Altarr[path[temp][j]][2]==2&&i!=18) {

									AIscore6+=10;
									humanscore-=10;

								}
							}




					}







					if(Altarr2[i][2]==1&&!bestpawn.checkDangerPosition1(i)) humanscore5++;
					else {

						AIscore1+=50;
					}

					if(i==6||i==10||i==16||i==20||i==30||i==26) humanscore3+=10;


					for(int j=0;j<8;j++) {



						int s=1,temp=0,tempX1=0,tempX2=0,tempX3=-1,lastPosition=-1;

						int tempX4=-1,tempX5=-1,tempX6=-1;

						tempX1=i;

						temp=path[i][j];
						tempX2=temp;


						if(temp!=-1) tempX3=path[temp][j];
						if(tempX3!=-1) tempX4=path[tempX3][j];
						if(tempX4!=-1) tempX5=path[tempX4][j];
						if(tempX5!=-1) tempX6=path[tempX5][j];



						if(temp!=-1&&path[temp][j]!=-1) {
							if(path[i][j]!=-1&&Altarr2[path[i][j]][2]==0&&Altarr2[path[temp][j]][2]==2&&i!=18) {

								AIscore2+=32;

								//System.out.println("1 step Ai"+i);

							}

							else if(tempX4!=-1&&path[i][j]!=-1&&Altarr2[path[i][j]][2]==0&&Altarr2[path[temp][j]][2]==0&&Altarr2[tempX4][2]==2) {

								AIscore4+=16;

								//System.out.println("2 step Ai"+i);

							}

							else if(tempX4!=-1&&tempX5!=-1&&path[i][j]!=-1&&Altarr2[path[i][j]][2]==0&&Altarr2[path[temp][j]][2]==0&&Altarr2[tempX4][2]==0&&Altarr2[tempX5][2]==2) {

								AIscore2+=8;

								//System.out.println("3 step AI"+i);

							}

							else if(tempX4!=-1&&tempX5!=-1&&tempX6!=-1&&path[i][j]!=-1&&Altarr2[path[i][j]][2]==0&&Altarr2[path[temp][j]][2]==0&&Altarr2[tempX4][2]==0&&Altarr2[tempX5][2]==0&&Altarr2[tempX6][2]==2) {

								AIscore4+=4;

								//System.out.println("4 step AI"+i);

							}


						}
					}



				}


			}


		System.out.println("AI score details: "+" AI1: "+AIscore1+" AI2: "+AIscore2+" AI3: "+AIscore3+" AI4: "+AIscore4+" AI5: "+AIscore5+" AI6 "+AIscore6);
		System.out.println("human score:"+"hum1: "+humanscore1+" hum2: "+humanscore2+" hum3: "+humanscore3+" hum4: "+humanscore4+" hum5: "+humanscore5);

		humanscore=humanscore1+humanscore2+humanscore3+humanscore4+humanscore5;
		AIscore=AIscore1+AIscore2+AIscore3+AIscore4+AIscore5+AIscore6;

		System.out.println("toatal  AI: "+AIscore+" hum: "+humanscore);

		System.out.println("how much gut i can eat team1 "+(num1-1)+"team2 "+(num2-1));

		num1*=5+10;
		num2*=5;

		return AIscore*num2-humanscore*(num1);




			/*if(flagforDanger==true) return  -1;
			else {

				System.out.println("not in danger: "+i);
				return 1;
			}*/




			/*if(Altarr[i][2]==2) {


				for(int j=0;j<8;j++) {



				int s=1,temp=0,tempX1=0,tempX2=0,tempX3,lastPosition=-1;



				tempX1=i;

				temp=path[i][j];
				tempX2=temp;


				if(temp!=-1&&path[temp][j]!=-1) {
				if(path[i][j]!=-1&&Altarr[path[i][j]][2]==1&&Altarr[path[temp][j]][2]==0) {

					System.out.println("return from first");

					//MakeFool mk=new MakeFool(Altarr,path);

					//int num=mk.maxForTeam2(Altarr);

						return 1;

				}

			}
		}



			}*/








				/*for(int j=0;j<8;j++) {



					int s=1,temp=0,tempX1=0,tempX2=0,tempX3,lastPosition=-1;



					tempX1=i;

					temp=path[i][j];
					tempX2=temp;


					if(temp!=-1&&path[temp][j]!=-1) {
						if(path[i][j]!=-1&&Altarr[path[i][j]][2]==0&&Altarr[path[temp][j]][2]==1) {


							System.out.println("return from third"+i+" "+path[i][j]+" "+path[temp][j]);


								return 1;
						}

					}

				}*/


				/*for(int j=0;j<8;j++) {



					int s=1,temp=0,tempX1=0,tempX2=0,tempX3,lastPosition=-1;



					tempX1=i;

					temp=path[i][j];
					tempX2=temp;


					if(temp!=-1&&path[temp][j]==-1) {
					if(path[i][j]!=-1&&Altarr[path[i][j]][2]==0) {


						System.out.println("return from fourth");

								return 1;
							}

						}
					}



			}

			for(int j=0;j<8;j++) {



				int s=1,temp=0,tempX1=0,tempX2=0,tempX3=0,lastPosition=-1;



				tempX1=i;

				temp=path[i][j];
				if(temp!=-1) tempX2=path[temp][j];
				if(tempX2!=-1) tempX3=path[tempX2][j];


				if(temp!=-1&&path[temp][j]!=-1&&path[tempX2][j]!=-1&&path[tempX3][j]!=-1) {
					if(Altarr[temp][2]==0&&Altarr[tempX2][2]==0&&Altarr[tempX3][2]==0&&Altarr[path[tempX3][j]][2]==1) {


						System.out.println("return from fifth");

							return 1;
					}

				}*/







			/*if(Altarr[i][2]==1) {


				for(int j=0;j<8;j++) {



						int s=1,temp=0,tempX1=0,tempX2=0,tempX3,lastPosition=-1;



						tempX1=i;

						temp=path[i][j];
						tempX2=temp;


						if(temp!=-1&&path[temp][j]!=-1) {
							if(path[i][j]!=-1&&Altarr[path[i][j]][2]==2&&Altarr[path[temp][j]][2]==0) {


								System.out.println("return from sixth");

								//MakeFool mk=new MakeFool(Altarr,path);

								//int num=mk.maxForTeam1(Altarr);









								return -1;


							}

						}
				}



			}
			/*if(Altarr[i][2]==1) {



				for(int j=0;j<8;j++) {



					int s=1,temp=0,tempX1=0,tempX2=0,tempX3,lastPosition=-1;



					tempX1=i;

					temp=path[i][j];
					tempX2=temp;


					if(temp!=-1&&path[temp][j]!=-1) {
						if(path[i][j]!=-1&&Altarr[path[i][j]][2]==2&&Altarr[path[temp][j]][2]!=0) {

							System.out.println("return from seven");


							return 1;
						}

					}
				}



			}
			*/


















        //return 0;


    }

}
