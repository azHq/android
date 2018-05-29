package com.example.asus.shologutionlinegaming;

import java.util.Queue;

public class SafePosition {
	
	int[][] arr=new int[37][3];
	int[][] path=new int[37][8];
	
	public int AX,AY,BX,BY;
	public int tmp1,tmp2;
	
	public SafePosition(int[][] arr,int[][] path) {
		
		this.arr=arr;
		this.path=path;
		
		
		
	}
	
	
	public boolean findSafePosition(Queue<Integer> queue,int counter2,int tmp3,int tmp4) {
		
		
		boolean flag=true;
		
		BestPawn bestpawn=new BestPawn(arr,path);
		
	
		
		
			
		for(int i=0;i<74;i++) {
				
				
				
				
				int element=queue.poll();
				if(arr[element][2]==2) {
				
				for(int j=0;j<8;j++) {
					
					int s=1,temp=0,tempX1=0,tempX2=0,tempX3;
					
					tempX1=element;
					
					temp=path[element][j];
					tempX2=temp;
					
					
					if(temp!=-1&&path[temp][j]==-1) {
					if(path[element][j]!=-1&&arr[path[element][j]][2]==0) {
						
						if(!bestpawn.checkDangerPosition(temp)&&!bestpawn.checkDangerTomove(tempX1)) {
								
								
								
								
								if(counter2==0) {
									
									AX=arr[tempX1][0];
									AY=arr[tempX1][1];
									BX=arr[tempX2][0];
									BY=arr[tempX2][1];
									
									tmp1=tempX1;
									tmp2=tempX2;
									
									System.out.println("newnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn1");
									
									
								}
								if(counter2>149) {
									
									
									
									arr[tmp3][2]=0;
									arr[tmp4][2]=2;
								
									System.out.println("newnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn2");
								}
								
								
								
								
								System.out.println("new2"+tmp3+" "+tmp4);
								
								
								queue.add(element);
								
								return true;
								
						}
						
						
						
					}
					}
					
				}
				
				}
				queue.add(element);
				
				
				
		}
		
		
		
		for(int i=0;i<74;i++) {
			
			
			
			
				int element=queue.poll();
				if(arr[element][2]==2) {
				
				for(int j=0;j<8;j++) {
					
					int s=1,temp=0,tempX1=0,tempX2=0,tempX3;
					
					tempX1=element;
					
					temp=path[element][j];
					tempX2=temp;
					
					
					if(temp!=-1&&path[temp][j]!=-1) {
					if(path[element][j]!=-1&&arr[path[element][j]][2]==0&&arr[path[temp][j]][2]!=1) {
						
						if(!bestpawn.checkDangerPosition(temp)&&!bestpawn.checkDangerTomove(tempX1)) {
								
								
								
								counter2++;
								
								if(counter2==1) {
									
									AX=arr[tempX1][0];
									AY=arr[tempX1][1];
									BX=arr[tempX2][0];
									BY=arr[tempX2][1];
									
									tmp1=tempX1;
									tmp2=tempX2;
									
									
									System.out.println(tmp1+" "+tmp2+"newwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww3");
									
									
								}
								if(counter2>150) {
									
									
									
									arr[tmp3][2]=0;
									arr[tmp4][2]=2;
								
									System.out.println(tmp3+" "+tmp4+"newnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn4");
								}
								
								
								
								System.out.println(tmp3+" "+tmp4+"new4");
								
								
								queue.add(element);
								System.out.println(queue);
								
								
								
								return true;
								
						}
						
						
						
					}
					}
					
				}
				
				}
				queue.add(element);
				
			
			
			}
		
		
			return false;
		
			
			
		}
	
	
	
	
	
	
	
	
		

}
