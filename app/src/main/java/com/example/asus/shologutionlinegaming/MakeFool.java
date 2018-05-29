package com.example.asus.shologutionlinegaming;

import java.util.ArrayList;

public class MakeFool {
	
	int[][] arr=new int[37][3];
	int[][] path=new int[37][8];
	
	public int source,destination;
	
	public MakeFool(int[][] arr,int[][] path) {
		
		this.arr=arr;
		this.path=path;
		
	}
	
	public boolean makeFool() {
		
		System.out.println("start foolllllllllllllllllllllllllllll");
		
		BestPawn bestpawn=new BestPawn(arr,path);
		
		B:for(int i=0;i<37;i++) {
			
			
			
			
			int element=i;
			if(arr[element][2]==2) {
			
			for(int j=0;j<8;j++) {
				
				
				
				int [][] Altarr=new int [37][3];
				for(int m=0;m<Altarr.length;m++) {
					
					for(int n=0;n<Altarr[0].length;n++) {
						
						Altarr[m][n]=arr[m][n];
						
						
					}
					
				}
			
				
				int s=1,temp=0,tempX1=0,tempX2=0,tempX3;
				
				tempX1=element;
				
				temp=path[element][j];
				tempX2=temp;
				
				
				
				if(temp!=-1&&path[temp][j]!=-1) {
					if((path[element][j]!=-1&&Altarr[path[element][j]][2]==0&&Altarr[path[temp][j]][2]==1)||(Altarr[path[element][j]][2]==0&&bestpawn.checkDangerPosition(temp)&&bestpawn.checkDangerTomove(tempX1))) {
					
					
						
						System.out.println("tempX1"+tempX1+"  tempX2"+tempX2+" MAKE FOOLLLLLLLLLLLLLLLLLLLLLLLLLLLLLFOOLLLLLLLLLLLLLLLLL");
						
						Altarr[tempX1][2]=0;
						Altarr[tempX2][2]=2;
						
						if(maxForTeam1Alt(Altarr)==-100) {
							
							return false;
						
						}
						else {
								
								for(int m=0;m<Altarr.length;m++) {
									
									for(int n=0;n<Altarr[0].length;n++) {
										
										Altarr[m][n]=arr[m][n];
										
										
									}
									
								}
								
								Altarr[tempX1][2]=0;
								Altarr[tempX2][2]=2;
									
						
								if(maxForTeam1(Altarr)<(maxForTeam2(Altarr)-maxForTeam1(Altarr))) {
									
									source=tempX1;
									destination=tempX2;
									
									System.out.println("end  fool with return true  foolllllllllllllllllllllllllllll");
									
									return true;
									
								}
						}
								
						
						
						
					
					}
				}
			}
			}
			
		}
		
		System.out.println("end with false foolllllllllllllllllllllllllllll");
		
		return false;
		
	}
	
	
	public int maxForTeam1(int [][] Altarr) {
		
		System.out.println("start team1");
		ArrayList<Integer> bestWay2[]=new ArrayList[37];
		int[] counter2=new int[37];
		
		
		for(int t=0;t<37;t++) {
			
			bestWay2[t]=new ArrayList<Integer>();
			
		}
		
		
		
		for(int i=0;i<37;i++) {
			
			
			
			int[][] Altarr2=new int[37][3];
			if(Altarr[i][2]==1) {
			
			for(int j=0;j<8;j++) {
				
				
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
					 
					 break;
				}
				
				}
				
			}
			}
			
			
			
			/*if(counter2[i]>maxForTeam2(Altarr2)) {
				
				return -100;
			}*/
			
		}
		
		
		
		System.out.println("newwwww team 1 counter in AI:");
		for(int t=0;t<37;t++) {
		
				//System.out.println("index:"+t+" value"+counter2[t]);
		}
		
		System.out.println();

		int max1=0,index=0;
		
		 for(int t=0;t<37;t++) {
		
			 
			 
			 if(counter2[t]>=max1) {
				 
				 max1=counter2[t];
				 index=t;
			 }
		
		}
		 
		 
		 for(int t=0;t<bestWay2[index].size();t+=3) {
				
				
				int sour=bestWay2[index].get(t);
				int mid=bestWay2[index].get(t+1);
				int dest=bestWay2[index].get(t+2);
				
				System.out.println("sour"+sour+" mid"+mid+"dest"+dest);
				
				Altarr[sour][2]=0;
				Altarr[mid][2]=0;
				Altarr[dest][2]=1;
		 }
		 
		 
		
		 
		
		 System.out.println("Fool max1: "+max1);
		 
		 System.out.println("end team1");
		 return max1;
		
	}
	
	public int maxForTeam2(int [][] Altarr) {
		
		System.out.println("start team2");
				ArrayList<Integer> bestWay[]=new ArrayList[37];
				int[] counter=new int[37];
				
				int[] checkCounter=new int[37];
				
				for(int t=0;t<37;t++) {
					
					bestWay[t]=new ArrayList<Integer>();
					
				}
				
				
				for(int i=0;i<37;i++) {
					
					
					if(Altarr[i][2]==2) {
					
					for(int j=0;j<8;j++) {
						
						int[][] Altarr2=new int[37][3];
						
						int s=1,temp=0,tempX1=0,tempX2=0,tempX3,lastPosition=-1;
						
						for(int m=0;m<Altarr2.length;m++) {
							
							for(int n=0;n<Altarr2[0].length;n++) {
								
								Altarr2[m][n]=Altarr[m][n];
								
								
							}
							
						}
						
						tempX1=i;
						
						temp=path[i][j];
						tempX2=temp;
						
						
						if(temp!=-1&&path[temp][j]!=-1) {
						if(path[i][j]!=-1&&Altarr[path[i][j]][2]==1&&Altarr[path[temp][j]][2]==0) {
							
							
							
							 
							 BestWay bestway=new BestWay(tempX1,counter,bestWay,checkCounter);
							
							 bestway.findBestWay(tempX1,Altarr,path); 
						}
						
						}
						
					}
					}
				}
				
				System.out.println("newwwww team 2 counter in AI:");
				for(int t=0;t<37;t++) {
				
					//System.out.println("index:"+t+" value"+counter[t]);
				}
				System.out.println();
					
				
					
				int max2=0,index=0;
				for(int t=0;t<37;t++) {
							
					 
								 
								
								 
					 if(counter[t]>=max2) {
									 
							max2=counter[t];
							index=t;
					}
							
				}
				
				
				
				 for(int t=0;t<bestWay[index].size();t+=3) {
						
						
						int sour=bestWay[index].get(t);
						int mid=bestWay[index].get(t+1);
						int dest=bestWay[index].get(t+2);
						
						System.out.println("sour"+sour+" mid"+mid+"dest"+dest);
						
						Altarr[sour][2]=0;
						Altarr[mid][2]=0;
						Altarr[dest][2]=2;
				 }
				 
				
				
				 System.out.println("Fool max2: "+max2);
				 
				 System.out.println("end team2");
				 
				 return max2;
				
				
				
				
				
	}
	
	
	
	
	
	
	
	
	
	
	
public int maxForTeam1Alt(int [][] Altarr) {
		
		System.out.println("start team1");
		ArrayList<Integer> bestWay2[]=new ArrayList[37];
		int[] counter2=new int[37];
		
		
		for(int t=0;t<37;t++) {
			
			bestWay2[t]=new ArrayList<Integer>();
			
		}
		
		
		
		for(int i=0;i<37;i++) {
			
			
			
			int[][] Altarr2=new int[37][3];
			if(Altarr[i][2]==1) {
			
			for(int j=0;j<8;j++) {
				
				
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
					 
					 break;
				}
				
				}
				
			}
			}
			
			
			
			if(counter2[i]>maxForTeam2(Altarr2)) {
				
				System.out.println("team1Alt return -100");
				
				return -100;
			}
			
		}
		
		
		
		System.out.println("newwwww team 1 counter in AI:");
		for(int t=0;t<37;t++) {
		
				//System.out.println("index:"+t+" value"+counter2[t]);
		}
		
		System.out.println();

		int max1=0,index=0;
		
		 for(int t=0;t<37;t++) {
		
			 
			 
			 if(counter2[t]>=max1) {
				 
				 max1=counter2[t];
				 index=t;
			 }
		
		}
		 
		 
		 for(int t=0;t<bestWay2[index].size();t+=3) {
				
				
				int sour=bestWay2[index].get(t);
				int mid=bestWay2[index].get(t+1);
				int dest=bestWay2[index].get(t+2);
				
				System.out.println("sour"+sour+" mid"+mid+"dest"+dest);
				
				Altarr[sour][2]=0;
				Altarr[mid][2]=0;
				Altarr[dest][2]=1;
		 }
		 
		 
		
		 
		
		 System.out.println("Fool max1: "+max1);
		 
		 System.out.println("end team1");
		 return max1;
		
	}
	
	
	

}
