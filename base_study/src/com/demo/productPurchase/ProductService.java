package com.demo.productPurchase;

import java.util.ArrayList;

public interface ProductService {

	public void addProduct(Product p);

	public ArrayList<Product> getAllProducts();

	public Product getProductById(int pId);

	public Product getProductByName(String pName);

	public ArrayList<Product> getProductsRangePrice(float minPrice, float maxPrice);

	public void deleteProductById(int pId);

	public void updateProductNameById(int pId, String newName);

	public void updateProductPriceById(int pId, float newPrice);

	public void updateProductStockNumById(int pId, int newStockNum);
}
