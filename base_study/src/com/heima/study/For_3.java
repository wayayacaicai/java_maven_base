package com.heima.study;

import java.awt.geom.GeneralPath;

public class For_3 {

	public static void main(String[] args) {
		// int sum=0;
		// for(int i=0;i<=100;i++){
		// if(i%2==0){
		// sum += i;
		// }
		// }
		// System.out.println(sum);

		// int count=0;
		// for(int i=100;i<=999;i++){
		// int ge = i%10;
		// int shi = i/10%10;
		// int bai = i/10/10;
		// if (ge*ge*ge + shi*shi*shi +bai*bai*bai == i){
		// System.out.println(i);
		// count++;
		// }
		// }
		// System.out.println(count);

		// for(int i=1;i<=9;i++){
		//
		// for(int j=1;j<=i;j++){
		// System.out.print(j + "*" + i + "=" + (j*i) + "\t");
		// }
		// System.out.print("\n");
		// }

		a: for (int i = 1; i <= 5; i++) {
			b: for (int j = 1; j <= i; j++) {
				if (i == 4) {
					// continue;
					break a;
				}
				System.out.print("*");
			}
			System.out.println();
		}

	}
}
