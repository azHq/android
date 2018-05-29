package com.example.asus.shologutionlinegaming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Apprehend {
	
	int[][] arr=new int[37][3];
	int[][] path=new int[37][8];
	
	int[][] Altarr=new int[37][3];
	
	public int source,destination,previous;
	
	public int counter=0,counter2=0;;
	
	public Queue<Integer> queue=new LinkedList<Integer>();
	public Queue<Integer> queue2=new LinkedList<Integer>();
	
	public ArrayList<Integer> bestWay[]=new ArrayList[37];
	
	
	
	public Apprehend(int[][] arr,int[][] path) {
		
		this.arr=arr;
		this.path=path;
		
	}
	
	
	public boolean toCatch(int counter2) {
		
	
				this.counter2=counter2;
				int s=1,temp=0,tempX1=0,tempX2=0,tempX3=0;
				
				for(int i=0;i<37;i++) {
					
					bestWay[i]=new ArrayList<Integer>();
				}
				
				
				for(int i=0;i<37;i++) {
					
						int element=i;
						if(arr[element][2]==1) {
							
							for(int j=0;j<8;j++) {
								tempX1=element;
								
								
								temp=path[element][j];
								tempX2=temp;
								
								previous=temp;
							
							
								if(temp!=-1&&path[temp][j]!=-1) {
								
									tempX3=path[temp][j];
									if(path[element][j]!=-1&&arr[path[element][j]][2]==0&&arr[path[temp][j]][2]==2) {
									
										bestWay[element].add(path[temp][j]);
										
									}
								
								
								}
							}
						}
					
				}
				
				
				
				
				
				
				//new add
				
				for(int m=0;m<Altarr.length;m++) {
					
					for(int n=0;n<Altarr[0].length;n++) {
						
						Altarr[m][n]=arr[m][n];
						
						
					}
					
				}
				
				System.out.println("apprehenddddddddddddddddddddddddddddddddddddddddd azaz");
				if(Altarr[6][2]==1||Altarr[10][2]==1) {
					
					
					
					if(Altarr[8][2]==0) {
						
						previous=8;
						if(findMyPawn(8)) {
							
							BestPawn bestpawn=new BestPawn(Altarr,path);
							if(!bestpawn.checkDangerTomove(source)&&Altarr[previous][2]!=1) {
								
								
								
								if(counter2==0) {
									Altarr[source][2]=0;
									Altarr[destination][2]=2;
								}
								
								
								
								BestPawn bestpawn2=new BestPawn(Altarr,path);
								
								if(!bestpawn2.checkDangerPosition(destination)) {
									
									System.out.println("newwwwwwwwwwwwwwwwwwwwwwwwwwwwww8"+counter2 +"        sour"+source+"dest"+destination);
							
										return true;
								}
							}
							
						}
						
						
					}
					
					else {
						
						
						
						if(Altarr[6][2]==1) {
							previous=16;
							if(findMyPawn(16)) {
								
								BestPawn bestpawn=new BestPawn(Altarr,path);
								if(!bestpawn.checkDangerTomove(source)&&Altarr[previous][2]!=1) {
									
									
									
									if(counter2==0) {
										Altarr[source][2]=0;
										Altarr[destination][2]=2;
									}
									
									
									
									BestPawn bestpawn2=new BestPawn(Altarr,path);
									
									if(!bestpawn2.checkDangerPosition(destination)) {
										
										System.out.println("newwwwwwwwwwwwwwwwwwwwwwwwwwwwww 6"+counter2 +"        sour"+source+"dest"+destination);
								
											return true;
									}
								}
								
							}
						
						}
					
						else if(Altarr[10][2]==1) {
							
							previous=20;
							if(findMyPawn(20)) {
								
								BestPawn bestpawn=new BestPawn(Altarr,path);
								if(!bestpawn.checkDangerTomove(source)&&Altarr[previous][2]!=1) {
									
									
									
									if(counter2==0) {
										Altarr[source][2]=0;
										Altarr[destination][2]=2;
									}
									
									
									
									BestPawn bestpawn2=new BestPawn(Altarr,path);
									
									if(!bestpawn2.checkDangerPosition(destination)) {
										
										System.out.println("newwwwwwwwwwwwwwwwwwwwwwwwwwwwww 10"+counter2 +"        sour"+source+"dest"+destination);
								
											return true;
									}
									
								}
							
							
						}
						
						
					}
					
					}
				}
						
					
					
				
				else if(Altarr[26][2]==1||Altarr[30][2]==1) {
					
					
					if(Altarr[28][2]==0) {
						
						previous=28;
						if(findMyPawn(28)) {
							
							BestPawn bestpawn=new BestPawn(Altarr,path);
							if(!bestpawn.checkDangerTomove(source)&&Altarr[previous][2]!=1) {
								
								
								
								if(counter2==0) {
									Altarr[source][2]=0;
									Altarr[destination][2]=2;
								}
								
								
								
								BestPawn bestpawn2=new BestPawn(Altarr,path);
								
								if(!bestpawn2.checkDangerPosition(destination)) {
									
									System.out.println("newwwwwwwwwwwwwwwwwwwwwwwwwwwwww 28"+counter2 +"        sour"+source+"dest"+destination);
							
										return true;
								}
							}
							
						}
						
						
					}
					
					else {
						
						
							if(Altarr[26][2]==1) {
								previous=16;
								if(findMyPawn(16)) {
									
									BestPawn bestpawn=new BestPawn(Altarr,path);
									if(!bestpawn.checkDangerTomove(source)&&Altarr[previous][2]!=1) {
										
										
										
										if(counter2==0) {
											Altarr[source][2]=0;
											Altarr[destination][2]=2;
										}
										
										
										
										BestPawn bestpawn2=new BestPawn(Altarr,path);
										
										if(!bestpawn2.checkDangerPosition(destination)) {
											
											System.out.println("newwwwwwwwwwwwwwwwwwwwwwwwwwwwww 26"+counter2 +"        sour"+source+"dest"+destination);
									
												return true;
										}
									}
									
								}
							
							}
						
							else if(Altarr[30][2]==1) {
								
								previous=20;
								if(findMyPawn(20)) {
									
									BestPawn bestpawn=new BestPawn(Altarr,path);
									if(!bestpawn.checkDangerTomove(source)&&Altarr[previous][2]!=1) {
										
										
										
										if(counter2==0) {
											Altarr[source][2]=0;
											Altarr[destination][2]=2;
										}
										
										
										
										BestPawn bestpawn2=new BestPawn(Altarr,path);
										
										if(!bestpawn2.checkDangerPosition(destination)) {
											
											System.out.println("newwwwwwwwwwwwwwwwwwwwwwwwwwwwww 30"+counter2 +"        sour"+source+"dest"+destination);
									
												return true;
										}
										
									}
								
								
							}
							
						
							}
							
					}
						
						
					
					
					
				}
				
				
				
				
				
				
				
				
				
				
				Random rand=new Random();
				
				for(int i=0;i<37;i++) {
					
					queue.add(rand.nextInt(37));
					
				}
				
				for(int i=0;i<37;i++) {
					
					queue.add(i);
					
				}
				
				for(int j=0;j<8;j++) {
					
					queue2.add(rand.nextInt(8));
				}
				
				for(int j=0;j<8;j++) {
					
					queue2.add(j);
				}
				
				
				
				for(int i=0;i<74;i++) {
					
					
					
					int element=queue.poll();
					if(arr[element][2]==1) {
					
					for(int t=0;t<16;t++) {
							
						int j=queue2.poll();
							for(int m=0;m<Altarr.length;m++) {
								
								for(int n=0;n<Altarr[0].length;n++) {
									
									Altarr[m][n]=arr[m][n];
									
									
								}
								
							}
						
						
							tempX1=element;
							
						
							temp=path[element][j];
							tempX2=temp;
							
							previous=temp;
						
						
							if(temp!=-1&&path[temp][j]!=-1) {
							
								tempX3=path[temp][j];
								if(path[element][j]!=-1&&Altarr[path[element][j]][2]==0&&Altarr[path[temp][j]][2]==0) {
									
									counter=0;
									System.out.println("Apprehend check 1");
									if(findMyPawn(tempX3)) {
										
										BestPawn bestpawn=new BestPawn(Altarr,path);
										if(!bestpawn.checkDangerTomove(source)&&Altarr[previous][2]!=1) {
											
											System.out.println("Apprehend check 2");
											
											if(counter2==0) {
												Altarr[source][2]=0;
												Altarr[destination][2]=2;
											}
											
											if((source==16&&(arr[12][2]==1||arr[22][2]==1))||(source==20&&(arr[14][2]==1||arr[24][2]==1))||(source==6&&(arr[7][2]==1||arr[11][2]==1||arr[12][2]==1))||(source==26&&(arr[21][2]==1||arr[27][2]==1||arr[22][2]==1))
													||(source==30&&(arr[29][2]==1||arr[25][2]==1||arr[24][2]==1))||(source==10&&(arr[9][2]==1||arr[14][2]==1||arr[15][2]==1))) {
												
												
												System.out.println("azzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzd1111111111111111111111111");
												queue.add(j);
												continue;
											}
											
											BestPawn bestpawn2=new BestPawn(Altarr,path);
											
											if(!bestpawn2.checkDangerPosition(destination)) {
												
												System.out.println(counter2 +"        sour"+source+"dest"+destination);
										
													return true;
											}
										}
										
									}
									
									
								}
								
							}
							
							queue2.add(j);
						}
					
						
					}
					
				}
				
			return false;
	}


	public boolean findMyPawn(int tempX3) {
		
		counter++;
		int tempX2=0;
		int tempX1=0;
		for(int j=0;j<8;j++) {
			
			
			
			tempX1=tempX3;
		
			
		
		
			if(tempX1!=-1&&path[tempX1][j]!=-1) {
			
				tempX2=path[tempX1][j];
				
				if(Altarr[tempX1][2]==0&&Altarr[tempX2][2]==2) {
					
					/*for(ArrayList<Integer> arrayList:bestWay) {
						
						for(int num:arrayList) {
							
							if(num==tempX2) return false;
						}
						
					}*/
					
					if(counter2==0) {
						source=tempX2;
						destination=tempX1;
					}
					
					return true;
				}
				else {
					
					previous=tempX1;
				}
			
					
					
			}
		}
		
		
		
		if(counter<25) findMyPawn(tempX2);
		
		return false;
	}

}
