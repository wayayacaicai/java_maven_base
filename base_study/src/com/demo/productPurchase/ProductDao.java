package com.demo.productPurchase;

import java.util.ArrayList;
import java.util.Set;

public interface ProductDao {
	public void addProduct(Product p);

	public Set<Product> getAllProducts();

	public Product getProductById(int pId);

	public Product getProductByName(String pName);

	public ArrayList<Product> getProductsRangePrice(float minPrice, float maxPrice);

	public void deleteProductById(int pId);

	public void updateProduct(Product p);
}
