package com.base.day14;

public class DiGui {

	public static void main(String[] args) {
		System.out.println(diGui2(6));

	}
	
	public static int diGui1(int num){
		if(num==0){
			return 1;
		}else{
			return num * diGui1(num-1);
		}
	}
	
	public static int diGui2(int num){
		if(num<=2){
			return 1;
		}else{
			return diGui2(num-1) + diGui2(num-2);
		}
	}

}
