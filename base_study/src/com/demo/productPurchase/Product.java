package com.demo.productPurchase;

import java.awt.FlowLayout;

public class Product {
	private int pId;
	private String pName;
	private float pPrice;
	private int stockNum;

	public Product() {

	}

	public Product(int pId, String pName, float pPrice, int stockNum) {
		this.pId = pId;
		this.pName = pName;
		this.pPrice = pPrice;
		this.stockNum = stockNum;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public float getpPrice() {
		return pPrice;
	}

	public void setpPrice(float pPrice) {
		this.pPrice = pPrice;
	}

	public int getStockNum() {
		return stockNum;
	}

	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}

	@Override
	public String toString() {

		return this.pId + ",商品名" + this.pName + ",价格" + this.pPrice + ",库存量" + this.stockNum;
	}

}
