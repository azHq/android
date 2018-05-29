package com.example.asus.shologutionlinegaming;

import java.util.ArrayList;

public class BestWay {
	
		public ArrayList<Integer> bestWay[]=new ArrayList[37];
		public int[] counter=new int[37];
		int[] checkCounter=new int[37];
	
		
		public int node;
		
		
		
		public BestWay(int source,int [] counter,ArrayList<Integer>[] bestWay,int[] checkCounter) {
			
			this.bestWay=bestWay;
			this.counter=counter;
			this.node=source;
			this.checkCounter=checkCounter;
			
			
		}
		
		int tempX1,tempX2,tempX3;
		
		
		public void findBestWay(int source,int [][] Altarr,int [][] path) {
				
				boolean flag=true;
				int[] totalCount=new int[8];
				
				for(int j=0;j<8;j++) {
					
					int [][] Altarr2=new int [37][3];
					for(int m=0;m<Altarr.length;m++) {
						
						for(int n=0;n<Altarr[0].length;n++) {
							
							Altarr2[m][n]=Altarr[m][n];
							
							
						}
						
					}
				
						tempX1=source;
						
						
						if(path[tempX1][j]!=-1) {
							
							tempX2=path[tempX1][j];
							
							
						
							if(path[tempX2][j]!=-1) {
								
								tempX3=path[tempX2][j];
								
							
							
								if(tempX2!=-1&&tempX3!=-1) {
									if(Altarr2[tempX2][2]==1&&Altarr2[tempX3][2]==0) {
										
										
										Altarr2[tempX1][2]=0;
										Altarr2[tempX2][2]=0;
										Altarr2[tempX3][2]=2;
										
										totalCount[j]++;
										
										checkWay( tempX3,j,Altarr2, path,totalCount);
										
										flag=false;
										
										
									}
							
								}
							
						
							}
						}
						
				}
				
				if(flag==true) {
					
					
					
					
					
					
					
					ArrayList<Integer> bestWay2[]=new ArrayList[37];
					int[] counter2=new int[37];
					
					
					for(int t=0;t<37;t++) {
						
						bestWay2[t]=new ArrayList<Integer>();
						
					}
					
					
					
					for(int i=0;i<37;i++) {
						
						
						if(Altarr[i][2]==1) {
						
						for(int j=0;j<8;j++) {
							
							int[][] Altarr2=new int[37][3];
							
							int s=1,temp=0,tempX1=0,tempX2=0,tempX3,lastPosition=-1;
							
							for(int m=0;m<Altarr.length;m++) {
								
								for(int n=0;n<Altarr[0].length;n++) {
									
									Altarr2[m][n]=Altarr[m][n];
									
									
								}
								
							}
							
							tempX1=i;
							
							temp=path[i][j];
							tempX2=temp;
							
							
							if(temp!=-1&&path[temp][j]!=-1) {
							if(path[i][j]!=-1&&Altarr2[path[i][j]][2]==2&&Altarr2[path[temp][j]][2]==0) {
								
								
							
								 
								OponentBestWay bestway=new OponentBestWay(tempX1,counter2,bestWay2);
								
								 bestway.findBestWay(tempX1,Altarr2,path); 
							}
							
							}
							
						}
						}
					}
			
					int max=0,index=0;
					
					 for(int t=0;t<37;t++) {
					
						 
						 
						 if(counter2[t]>=max) {
							 
							 max=counter2[t];
							 index=t;
						 }
					
					}
					 
					 //permit to eat single guti
					/* if(checkCounter[node]>=4&&max>0) {
							
							checkCounter[node]=checkCounter[node]/2;
							
							checkCounter[node]=checkCounter[node]-max;
							
						}
						else  checkCounter[node]=checkCounter[node]-max;*/
					 
					 
					 
					//don't eat single guti
					/*if(checkCounter[node]>=4&&max>0) {
						
						checkCounter[node]=checkCounter[node]/2;
						
						checkCounter[node]=checkCounter[node]-max;
						
					}
					 else  checkCounter[node]=checkCounter[node]-max*2;
					*/
					
					 checkCounter[node]=checkCounter[node]-max*2;
					
					
					
					
					
					
					
					return;
					
			}
				
				
				
				
				
				int max=0,temp=0;
				for(int i=0;i<8;i++) {
					
					if(totalCount[i]>max){
						
						max=totalCount[i];
						temp=i;
					}
					
				}
				
				
				tempX1=source;
				tempX2=path[source][temp];
				tempX3=path[tempX2][temp];
				
				Altarr[tempX1][2]=0;
				Altarr[tempX2][2]=0;
				
				for(int i=0;i<8;i++) {
					
					//System.out.print("TotalCount:"+totalCount[i]);
				
				}
				//System.out.println();
				//System.out.println("source:"+source+"   tempj: "+temp+"   node:"+node);
				Altarr[tempX3][2]=2;
				
				bestWay[node].add(tempX1);
				bestWay[node].add(tempX2);
				bestWay[node].add(tempX3);
				counter[node]++;
				
				checkCounter[node]+=2;
				
				findBestWay(tempX3,Altarr,path);
				
		}
		
		public void checkWay(int source,int way,int [][] Altarr,int [][] path,int[] totalCount) {
			
			int temp;
			/*int [][] Altarr2=new int [37][3];
			for(int m=0;m<Altarr.length;m++) {
				
				for(int n=0;n<Altarr[0].length;n++) {
					
					Altarr2[m][n]=Altarr[m][n];
					
					
				}
				
			}*/
			
		//	System.out.println("checkway method");
			tempX1=source;
		
			
			
			
			while(true){
				
				
				
				
				
				boolean flag=true;	
				for(int k=0;k<8;k++) {
					
					temp=tempX1;
					
				
					
					if(path[temp][k]!=-1) {
						
						temp=path[temp][k];
						tempX2=temp;
						
					
					if(path[temp][k]!=-1) {
						
						temp=path[temp][k];
						tempX3=temp;
						
					
					
					if(tempX2!=-1&&tempX3!=-1) {
					
						if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
						
							Altarr[temp][2]=0;
							Altarr[tempX2][2]=0;
							Altarr[tempX3][2]=2;
							
						
							tempX1=tempX3;
							
							totalCount[way]++;
					//	System.out.println("j-->"+k+"  tempX1 "+tempX2+ "  tempX2  "+tempX3+"  temp "+temp+" count7:  "+counter[source]);
							
							
							flag=false;
							
						}
						
					}
	
					
					}
					}
					
				}
				
				if(flag==true) {
					
					
					
					break;
					
				}
				
				
				
			}
			
		}

}
