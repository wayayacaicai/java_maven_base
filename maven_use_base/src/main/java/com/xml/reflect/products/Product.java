package com.xml.reflect.products;

/**
 * @Desc:商品类
 * @author:zpp
 * @time:2019年3月22日 下午10:49:19
 */
public class Product {
	private int id;
	private String name;
	private float price;
	private int stockNum;
	private String color;

	public Product() {
		super();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStockNum() {
		return stockNum;
	}

	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", stockNum=" + stockNum + ", color="
				+ color + "]";
	}

}
