package com.demo.compare;

import java.util.ArrayList;

public class SortArrayList {
	public void sort(ArrayList<User1> aU1,BiJiaoQi biJiaoQi){
		
		//基础版
//		for(int i=0;i<aU1.size()-1;i++){
//			for(int j=i+1;j<aU1.size();j++){
//				User1 temp1 = aU1.get(i);
//				if(aU1.get(i).getAge()>aU1.get(j).getAge()){
//					aU1.set(i, aU1.get(j));
//					aU1.set(j, temp1);			
		
		//升级版
		for(int i=0;i<aU1.size()-1;i++){
			for(int j=i+1;j<aU1.size();j++){
				User1 temp1 = aU1.get(i);
				boolean bijiao = biJiaoQi.biJiao(aU1.get(i), aU1.get(j));
				if(bijiao){
					aU1.set(i, aU1.get(j));
					aU1.set(j, temp1);	
				}
							
			}
		}
	}
}
