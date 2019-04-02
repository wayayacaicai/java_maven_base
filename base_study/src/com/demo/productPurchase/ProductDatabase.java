package com.demo.productPurchase;

import java.util.HashMap;

public class ProductDatabase {
	public static HashMap<Integer, Product> hMap = new HashMap<>();

	static {
		Product p1 = new Product(1, "手表", 20.5f, 5);
		Product p2 = new Product(2, "戒指", 40.5f, 10);
		Product p3 = new Product(3, "篮球", 30.5f, 6);

		hMap.put(p1.getpId(), p1);
		hMap.put(p2.getpId(), p2);
		hMap.put(p3.getpId(), p3);
	}
}
