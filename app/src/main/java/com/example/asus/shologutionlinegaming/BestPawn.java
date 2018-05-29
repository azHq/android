package com.example.asus.shologutionlinegaming;

public class BestPawn {

	int[][] arr=new int[37][3];
	int[][] path=new int[37][8];

	public int AX,AY,BX,BY;

	public int source,destination;

	public BestPawn(int[][] arr, int[][] path) {

		this.arr=arr;
		this.path=path;

	}

	public boolean saveGuti(int counter2,Thread thread) {

		for(int i=0;i<37;i++) {

			if(arr[i][2]==2) {
				for(int j=0;j<8;j++) {

					if(j<4) {

						boolean flag1=true;

						if(path[i][j]!=-1&&path[i][j+4]!=-1) {

							if(arr[path[i][j]][2]==0&&arr[path[i][j+4]][2]==1||arr[path[i][j+4]][2]==0&&arr[path[i][j]][2]==1) {

								if(arr[path[i][j]][2]==0) {


									if(checkDangerPosition(path[i][j],j)) {

									}
									else {


										int temp=path[i][j];

										for(int t=0;t<8;t++) {

											if(path[temp][t]!=-1) {

												if(arr[path[temp][t]][2]==2) {

													if(checkDangerTomove(path[temp][t])) {

													}
													else {


														//new add

														int[][] Altarr=new int[37][3];



														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}

														MakeFool mf=new MakeFool(Altarr,path);


														int num1=mf.maxForTeam1(Altarr);


														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}

														Altarr[path[i][j]][2]=2;
														Altarr[path[temp][t]][2]=0;

														int num2=mf.maxForTeam1(Altarr);

														System.out.println("method  save guti  num1"+num1+"num2"+num2);
														if(num2<=num1) {





															flag1=false;
															//if(counter2<150) {

																AX=arr[path[i][j]][0];
																AY=arr[path[i][j]][1];
																BX=arr[path[temp][t]][0];
																BY=arr[path[temp][t]][1];




																System.out.println("azaz 1");

															//}
															//if(counter2>150) {
															/*	arr[path[i][j]][2]=2;
																arr[path[temp][t]][2]=0;*/

																source=path[temp][t];
																destination=path[i][j];


																System.out.println("azaz 2");

															//}

															return true;
														}
													}

												}


											}

										}

									}

									if(flag1==true) {

										for(int t=0;t<8;t++) {

											if(path[i][t]!=-1) {
												if(arr[path[i][t]][2]==0) {
													if(!checkDangerPosition(path[i][t],t)) {


														//new add

														int[][] Altarr=new int[37][3];



														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}

														MakeFool mf=new MakeFool(Altarr,path);

														int num1=mf.maxForTeam1(Altarr);

														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}


														Altarr[i][2]=0;
														Altarr[path[i][t]][2]=2;

														int num2=mf.maxForTeam1(Altarr);

														if(num2<=num1) {




															//if(counter2<150) {

																AX=arr[i][0];
																AY=arr[i][1];
																BX=arr[path[i][t]][0];
																BY=arr[path[i][t]][1];



																System.out.println("azaz 3");

															//}
															//if(counter2>150) {
																/*arr[i][2]=0;
																arr[path[i][t]][2]=2;*/

																source=i;
																destination=path[i][t];

																System.out.println("azaz 4");
															//}

															return true;
														}

													}
												}
											}

										}

									}


								}




								if(arr[path[i][j+4]][2]==0) {

									if(checkDangerPosition(path[i][j+4],j+4)) {

									}
									else {

										int temp=path[i][j+4];

										for(int t=0;t<8;t++) {

											if(path[temp][t]!=-1) {

												if(arr[path[temp][t]][2]==2) {

													if(checkDangerTomove(path[temp][t])) {

													}
													else {


														//new add

														int[][] Altarr=new int[37][3];



														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}

														MakeFool mf=new MakeFool(Altarr,path);

														int num1=mf.maxForTeam1(Altarr);


														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}

														Altarr[path[i][j+4]][2]=2;
														Altarr[path[temp][t]][2]=0;
														int num2=mf.maxForTeam1(Altarr);

														if(num2<=num1) {




															//if(counter2<150) {

																AX=arr[path[i][j+4]][0];
																AY=arr[path[i][j+4]][1];
																BX=arr[path[temp][t]][0];
																BY=arr[path[temp][t]][1];




																System.out.println("azaz 5");

															//}
															//if(counter2>150) {
																/*arr[path[i][j+4]][2]=2;
																arr[path[temp][t]][2]=0;*/

																source=path[temp][t];
																destination=path[i][j+4];

																System.out.println("azaz 6");
															//}

															flag1=false;


															return true;
														}

													}

												}


											}

										}


									}

									if(flag1==true) {

										for(int t=0;t<8;t++) {

											if(path[i][t]!=-1) {
												if(arr[path[i][t]][2]==0) {
													if(!checkDangerPosition(path[i][t],t)) {

														//new add

														int[][] Altarr=new int[37][3];



														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}

														MakeFool mf=new MakeFool(Altarr,path);

														int num1=mf.maxForTeam1(Altarr);


														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}

														Altarr[i][2]=0;
														Altarr[path[i][t]][2]=2;
														int num2=mf.maxForTeam1(Altarr);

														if(num2<=num1) {







															//if(counter2<150) {

																AX=arr[i][0];
																AY=arr[i][1];
																BX=arr[path[i][t]][0];
																BY=arr[path[i][t]][1];




																System.out.println("azaz 7");

															//}
															//if(counter2>150) {
															/*	arr[i][2]=0;
																arr[path[i][t]][2]=2;*/


																source=i;
																destination=path[i][t];

																System.out.println("azaz 8");
															//}




															return true;

														}
													}
												}
											}
										}

									}


								}







							}

						}


					}





					if(j>=4) {

						boolean flag1=true;

						if(path[i][j]!=-1&&path[i][j-4]!=-1) {

							if(arr[path[i][j]][2]==0&&arr[path[i][j-4]][2]==1||arr[path[i][j-4]][2]==0&&arr[path[i][j]][2]==1) {

								if(arr[path[i][j]][2]==0) {


									if(checkDangerPosition(path[i][j],j)) {

									}
									else {


										int temp=path[i][j];

										for(int t=0;t<8;t++) {

											if(path[temp][t]!=-1) {

												if(arr[path[temp][t]][2]==2) {

													if(checkDangerTomove(path[temp][t])) {

													}
													else {





														//new add

														int[][] Altarr=new int[37][3];



														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}

														MakeFool mf=new MakeFool(Altarr,path);

														int num1=mf.maxForTeam1(Altarr);

														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}

														Altarr[path[i][j]][2]=0;
														Altarr[path[temp][t]][2]=2;

														int num2=mf.maxForTeam1(Altarr);

														if(num2<=num1) {



															flag1=false;



															//if(counter2<150) {

																AX=arr[path[i][j]][0];
																AY=arr[path[i][j]][1];
																BX=arr[path[temp][t]][0];
																BY=arr[path[temp][t]][1];



															//}
															//if(counter2>150) {
																/*arr[path[i][j]][2]=0;
																arr[path[temp][t]][2]=2;*/


																source=i;
																destination=path[temp][t];

															//}


															return true;
														}

													}

												}


											}

										}

									}

									if(flag1==true) {

										for(int t=0;t<8;t++) {

											if(path[i][t]!=-1) {
												if(arr[path[i][t]][2]==0) {
													if(!checkDangerPosition(path[i][t],t)) {



														//new add

														int[][] Altarr=new int[37][3];



														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}

														MakeFool mf=new MakeFool(Altarr,path);

														int num1=mf.maxForTeam1(Altarr);


														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}

														Altarr[i][2]=0;
														Altarr[path[i][t]][2]=2;

														int num2=mf.maxForTeam1(Altarr);

														if(num2<=num1) {



															//if(counter2<150) {

																AX=arr[i][0];
																AY=arr[i][1];
																BX=arr[path[i][t]][0];
																BY=arr[path[i][t]][1];




															//}
															//if(counter2>150) {
																/*arr[i][2]=0;
																arr[path[i][t]][2]=2;*/

																source=i;
																destination=path[i][t];


															//}



															return true;
														}

													}
												}
											}
										}

									}


								}




								if(arr[path[i][j-4]][2]==0) {

									if(checkDangerPosition(path[i][j-4],j-4)) {

									}
									else {

										int temp=path[i][j-4];

										for(int t=0;t<8;t++) {

											if(path[temp][t]!=-1) {

												if(arr[path[temp][t]][2]==2) {

													if(checkDangerTomove(path[temp][t])) {

													}
													else {



														//new add

														int[][] Altarr=new int[37][3];



														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}

														MakeFool mf=new MakeFool(Altarr,path);

														int num1=mf.maxForTeam1(Altarr);


														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}

														Altarr[path[i][j-4]][2]=0;
														Altarr[path[temp][t]][2]=2;
														int num2=mf.maxForTeam1(Altarr);

														if(num2<=num1) {



															flag1=false;

															//if(counter2<150) {

																AX=arr[path[i][j-4]][0];
																AY=arr[path[i][j-4]][1];
																BX=arr[path[temp][t]][0];
																BY=arr[path[temp][t]][1];



															//}
															//if(counter2>150) {
																/*arr[path[i][j-4]][2]=0;
																arr[path[temp][t]][2]=2;*/


																source=path[i][j-4];

																destination=path[temp][t];
															//}



															return true;
														}

													}

												}


											}

										}


									}

									if(flag1==true) {

										for(int t=0;t<8;t++) {

											if(path[i][t]!=-1) {
												if(arr[path[i][t]][2]==0) {
													if(!checkDangerPosition(path[i][t],t)) {



														//new add

														int[][] Altarr=new int[37][3];



														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}

														MakeFool mf=new MakeFool(Altarr,path);

														int num1=mf.maxForTeam1(Altarr);



														for(int m=0;m<Altarr.length;m++) {

															for(int n=0;n<Altarr[0].length;n++) {

																Altarr[m][n]=arr[m][n];


															}

														}


														Altarr[i][2]=0;
														Altarr[path[i][t]][2]=2;

														int num2=mf.maxForTeam1(Altarr);

														if(num2<=num1) {




															//if(counter2<150) {

																AX=arr[i][0];
																AY=arr[i][1];
																BX=arr[path[i][t]][0];
																BY=arr[path[i][t]][1];




															//}
															//if(counter2>150) {
																arr[i][2]=0;
																arr[path[i][t]][2]=2;


																source=i;
																destination=path[i][t];

															//}



															return true;

														}




													}
												}
											}
										}

									}


								}







							}

						}


					}




				}

			}
		}

		return false;

	}

	public boolean checkHotspotPosition(int counter2) {



		if(arr[26][2]==0) {


			if(!checkDangerPosition(26)) {

				for(int i=7;i>0;i--) {

					if(path[26][i]!=-1) {
						if(arr[path[26][i]][2]==2) {

							if(!checkDangerTomove(path[26][i])) {


								//if(counter2<150) {

									AX=arr[26][0];
									AY=arr[26][1];
									BX=arr[path[26][i]][0];
									BY=arr[path[26][i]][1];

								//}
								//if(counter2>150) {

									/*arr[26][2]=2;
									arr[path[26][i]][2]=0;*/

									source=path[26][i];
									destination=26;

								//}

								return true;

							}
						}
					}

				}
			}


		}
		if(arr[30][2]==0) {

			if(!checkDangerPosition(30)) {

				for(int i=7;i>0;i--) {

					if(path[30][i]!=-1) {
						if(arr[path[30][i]][2]==2) {

							if(!checkDangerTomove(path[30][i])) {

								//if(counter2<150) {

									AX=arr[30][0];
									AY=arr[30][1];
									BX=arr[path[30][i]][0];
									BY=arr[path[30][i]][1];

								//}
								//if(counter2>150) {

									/*arr[30][2]=2;
									arr[path[30][i]][2]=0;*/

								source=path[30][i];
								destination=30;
								//}


								return true;
							}
						}
					}
				}

			}

		}

		if(arr[10][2]==0) {


			System.out.println("mon pajore");

			if(!checkDangerPosition(10)) {

				for(int i=7;i>0;i--) {

					if(path[10][i]!=-1) {
						if(arr[path[10][i]][2]==2) {

							if(!checkDangerTomove(path[10][i])) {

								//if(counter2<150) {

									AX=arr[10][0];
									AY=arr[10][1];
									BX=arr[path[10][i]][0];
									BY=arr[path[10][i]][1];

								//}
								//if(counter2>150) {

									/*arr[10][2]=2;
									arr[path[10][i]][2]=0;*/

								source=path[10][i];
								destination=10;

								//}



								return true;
							}
						}
					}
				}

			}


		}
		if(arr[6][2]==0) {


			if(!checkDangerPosition(6)) {

				for(int i=7;i>0;i--) {

					if(path[6][i]!=-1) {
						if(arr[path[6][i]][2]==2) {

							if(!checkDangerTomove(path[6][i])) {

								//if(counter2<150) {

									AX=arr[6][0];
									AY=arr[6][1];
									BX=arr[path[6][i]][0];
									BY=arr[path[6][i]][1];

								//}
								//if(counter2>150) {

									/*arr[6][2]=2;
									arr[path[6][i]][2]=0;*/

								source=path[6][i];
								destination=6;
								//}





								return true;
							}
						}
					}
				}

			}


		}

		if(arr[16][2]==0) {



			if(!checkDangerPosition(16)) {

				for(int i=7;i>0;i--) {

					if(path[16][i]!=-1) {
						if(arr[path[16][i]][2]==2) {

							if(!checkDangerTomove(path[16][i])) {

								if(i<4) {

									if(path[16][i+4]==-1) {

										//if(counter2<150) {

											AX=arr[16][0];
											AY=arr[16][1];
											BX=arr[path[16][i]][0];
											BY=arr[path[16][i]][1];

										//}
										//if(counter2>150) {

											/*arr[16][2]=2;
											arr[path[16][i]][2]=0;*/


										source=path[16][i];
										destination=16;
										//}




										return true;
									}

									else if(path[16][i+4]!=-1) {
										if(arr[path[16][i+4]][2]!=1) {

										//	if(counter2<150) {

												AX=arr[16][0];
												AY=arr[16][1];
												BX=arr[path[16][i]][0];
												BY=arr[path[16][i]][1];

										//	}
										//	if(counter2>150) {

												/*arr[16][2]=2;
												arr[path[16][i]][2]=0;*/

											source=path[16][i];
											destination=16;
										//	}



											return true;

										}

									}


								}

								if(i>=4) {


									if(path[16][i-4]==-1) {

										//if(counter2<150) {

											AX=arr[16][0];
											AY=arr[16][1];
											BX=arr[path[16][i]][0];
											BY=arr[path[16][i]][1];

										//}
										//if(counter2>150) {

										/*	arr[16][2]=2;
											arr[path[16][i]][2]=0;*/

										source=path[16][i];
										destination=16;
										//}




										return true;
									}
									else if(path[16][i-4]!=-1) {
										if(arr[path[16][i-4]][2]!=1) {

										//	if(counter2<150) {

												AX=arr[16][0];
												AY=arr[16][1];
												BX=arr[path[16][i]][0];
												BY=arr[path[16][i]][1];

										//	}
										//	if(counter2>150) {

												/*arr[16][2]=2;
												arr[path[16][i]][2]=0;*/

											source=path[16][i];
											destination=16;
										//	}





											return true;

										}

									}


								}

							}

						}


					}
				}

			}


		}


		if(arr[20][2]==0) {



			if(!checkDangerPosition(20)) {

				for(int i=7;i>0;i--) {

					if(path[20][i]!=-1) {
						if(arr[path[20][i]][2]==2) {

							if(!checkDangerTomove(path[20][i])) {

								if(i<4) {

									if(path[20][i+4]==-1) {

										//if(counter2<150) {

											AX=arr[20][0];
											AY=arr[20][1];
											BX=arr[path[20][i]][0];
											BY=arr[path[20][i]][1];



										//}
										//if(counter2>150) {



											/*arr[20][2]=2;
											arr[path[20][i]][2]=0;*/

										source=path[20][i];
										destination=20;

										//}





										return true;
									}

									else if(path[20][i+4]!=-1) {
										if(arr[path[20][i+4]][2]!=1) {


										//	if(counter2<150) {

												AX=arr[20][0];
												AY=arr[20][1];
												BX=arr[path[20][i]][0];
												BY=arr[path[20][i]][1];

										//	}
										//	if(counter2>150) {

												/*arr[20][2]=2;
												arr[path[20][i]][2]=0;*/

											source=path[20][i];
											destination=20;

											//	}



											return true;

										}

									}


								}

								if(i>=4) {

									if(path[20][i-4]==-1) {


										//if(counter2<150) {

											AX=arr[20][0];
											AY=arr[20][1];
											BX=arr[path[20][i]][0];
											BY=arr[path[20][i]][1];

										//}
										//if(counter2>150) {

											/*arr[20][2]=2;
											arr[path[20][i]][2]=0;*/

										source=path[20][i];
										destination=20;

										//}


										return true;
									}
									else if(path[20][i-4]!=-1) {
										if(arr[path[20][i-4]][2]!=1) {



										//	if(counter2<150) {

												AX=arr[20][0];
												AY=arr[20][1];
												BX=arr[path[20][i]][0];
												BY=arr[path[20][i]][1];

										//	}
										//	if(counter2>150) {

												/*arr[20][2]=2;
												arr[path[20][i]][2]=0;*/

											source=path[20][i];
											destination=20;

											//	}



											return true;

										}

									}


								}

							}

						}

					}
				}
			}




		}

		/*if(arr[0][2]==0) {

			if(!checkDangerPosition(0)) {

				for(int i=7;i>0;i--) {

					if(path[0][i]!=-1) {
						if(arr[path[0][i]][2]==2) {

							if(!checkDangerTomove(path[0][i])) {

								if(counter2<150) {

									AX=arr[0][0];
									AY=arr[0][1];
									BX=arr[path[0][i]][0];
									BY=arr[path[0][i]][1];

								}
								if(counter2>150) {

									arr[0][2]=2;
									arr[path[0][i]][2]=0;
								}


								return true;
							}
						}
					}
				}

			}

		}

		if(arr[2][2]==0) {

			if(!checkDangerPosition(2)) {

				for(int i=7;i>0;i--) {

					if(path[2][i]!=-1) {
						if(arr[path[2][i]][2]==2) {

							if(!checkDangerTomove(path[2][i])) {

									if(counter2<150) {

										AX=arr[2][0];
										AY=arr[2][1];
										BX=arr[path[2][i]][0];
										BY=arr[path[2][i]][1];

									}
									if(counter2>150) {

										arr[2][2]=2;
										arr[path[2][i]][2]=0;
									}

									return true;
							}
						}
					}
				}

			}

		}

		/*if(arr[34][2]==0) {

			if(!checkDangerPosition(34)) {

				for(int i=7;i>0;i--) {

					if(path[34][i]!=-1) {
						if(arr[path[34][i]][2]==2) {

							if(!checkDangerTomove(path[34][i])) {
								if(counter2<150) {

									AX=arr[34][0];
									AY=arr[34][1];
									BX=arr[path[34][i]][0];
									BY=arr[path[34][i]][1];

								}
								if(counter2>150) {

									arr[34][2]=2;
									arr[path[34][i]][2]=0;
								}
								return true;
							}
						}
					}
				}

			}

		}*/

		/*if(arr[36][2]==0) {


			if(!checkDangerPosition(36)) {

				for(int i=7;i>0;i--) {

					if(path[36][i]!=-1) {
						if(arr[path[36][i]][2]==2) {

							if(!checkDangerTomove(path[36][i])) {

									if(counter2<150) {

										AX=arr[36][0];
										AY=arr[36][1];
										BX=arr[path[36][i]][0];
										BY=arr[path[36][i]][1];

									}
									if(counter2>150) {

										arr[36][2]=2;
										arr[path[36][i]][2]=0;
									}

									return true;

							}
						}
					}
				}

			}


		}*/


		return false;

	}

	public boolean checkDangerPosition(int source) {






		for(int j=0;j<8;j++) {

			if(j<4) {

				if(path[source][j]!=-1&&path[source][j+4]!=-1) {

					if(arr[path[source][j]][2]==0&&arr[path[source][j+4]][2]==1||arr[path[source][j+4]][2]==0&&arr[path[source][j]][2]==1) {

						return true;
					}

				}


			}
			if(j>=4) {

				if(path[source][j]!=-1&&path[source][j-4]!=-1) {

					if(arr[path[source][j]][2]==0&&arr[path[source][j-4]][2]==1||arr[path[source][j-4]][2]==0&&arr[path[source][j]][2]==1) {

						return true;

					}

				}


			}





		}
		return false;

	}



	public boolean checkDangerPosition1(int source) {






		for(int j=0;j<8;j++) {

			if(j<4) {

				if(path[source][j]!=-1&&path[source][j+4]!=-1) {

					if(arr[path[source][j]][2]==0&&arr[path[source][j+4]][2]==2||arr[path[source][j+4]][2]==0&&arr[path[source][j]][2]==2) {

						return true;
					}

				}


			}
			if(j>=4) {

				if(path[source][j]!=-1&&path[source][j-4]!=-1) {

					if(arr[path[source][j]][2]==0&&arr[path[source][j-4]][2]==2||arr[path[source][j-4]][2]==0&&arr[path[source][j]][2]==2) {

						return true;

					}

				}


			}





		}
		return false;

	}











	public boolean checkDangerTomove(int source) {

		for(int i=0;i<8;i++) {

			if(source!=-1) {
				int tempX1=path[source][i];

				if(tempX1!=-1) {
					int tempX2=path[tempX1][i];

					if(tempX2!=-1) {

						if(arr[tempX1][2]==2&&arr[tempX2][2]==1) {

							return true;
						}

					}
				}

			}
		}





		return false;
	}

	public boolean checkDangerTomove1(int source) {

		for(int i=0;i<8;i++) {

			if(source!=-1) {
				int tempX1=path[source][i];

				if(tempX1!=-1) {
					int tempX2=path[tempX1][i];

					if(tempX2!=-1) {

						if(arr[tempX1][2]==1&&arr[tempX2][2]==2) {

							return true;
						}

					}
				}

			}
		}





		return false;
	}









	public boolean checkDangerPosition(int source,int index) {









		int temp=0,tempX1=0;

		tempX1=source;

		temp=path[source][index];

		if(temp!=-1&&source!=-1) {
			if(path[source][index]!=-1&&arr[source][2]==0&&arr[path[source][index]][2]==1) {

				return true;
			}

		}



		for(int j=0;j<8;j++) {

			if(j<4) {

				if(path[source][j]!=-1&&path[source][j+4]!=-1) {

					if(arr[path[source][j]][2]==0&&arr[path[source][j+4]][2]==1||arr[path[source][j+4]][2]==0&&arr[path[source][j]][2]==1) {

						return true;
					}

				}


			}
			if(j>=4) {

				if(path[source][j]!=-1&&path[source][j-4]!=-1) {

					if(arr[path[source][j]][2]==0&&arr[path[source][j-4]][2]==1||arr[path[source][j-4]][2]==0&&arr[path[source][j]][2]==1) {

						return true;

					}

				}


			}





		}
		return false;

	}
}
