package com.example.asus.shologutionlinegaming;

public class DonotBeFool {
	
	public int AX,AY,BX,BY;

	public int source,middle,destination;



	public int totalpawn1,totalpawn2;
	public DonotBeFool(int totalpawn1,int totalpawn2) {

		this.totalpawn1=totalpawn1;
		this.totalpawn2=totalpawn2;


	}

	public boolean beClever(int counter2,int [][] Altarr,int [][] path) {
		
		
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
				if(path[i][j]!=-1&&Altarr2[path[i][j]][2]==1&&Altarr2[path[temp][j]][2]==0&&(i!=6&&i!=10&&i!=26&&i!=30)&&(i!=16&&i!=20)) {
					
					
					

						
						AX=Altarr2[i][0];
						AY=Altarr2[i][1];
						BX=Altarr2[path[temp][j]][0];
						BY=Altarr2[path[temp][j]][1];
						
						System.out.println(" Dont be fool azaz 1");




						source=i;
						middle=path[i][j];
						destination=path[temp][j];




						
						System.out.println(" Dont be fool azaz 2");
						
						
						

					
					return true;
					
					
					 
					
					 
				}
				else if(path[i][j]!=-1&&Altarr2[path[i][j]][2]==1&&Altarr2[path[temp][j]][2]==0&&(totalpawn2-totalpawn1)>3&&totalpawn1<10) {
					
					

						
						AX=Altarr2[i][0];
						AY=Altarr2[i][1];
						BX=Altarr2[path[temp][j]][0];
						BY=Altarr2[path[temp][j]][1];
						
						System.out.println(" Dont be fool azaz 3");





						source=i;
						middle=path[i][j];
						destination=path[temp][j];


						
						System.out.println(" Dont be fool azaz 3");
						

					
					return true;
					
				}
				
				}
				
			}
					 
					 
			}
		}
		
		return false;
	}

}
