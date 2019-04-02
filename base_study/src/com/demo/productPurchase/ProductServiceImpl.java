package com.demo.productPurchase;

import java.util.ArrayList;
import java.util.Set;

public class ProductServiceImpl implements ProductService {

	ProductDao productDao = new ProductDaoImpl();

	@Override
	public void addProduct(Product p) {
		productDao.addProduct(p);
	}

	@Override
	public ArrayList<Product> getAllProducts() {

		ArrayList<Product> ap = new ArrayList<>();
		Set<Product> st = productDao.getAllProducts();

		for (Product product : st) {
			ap.add(product);
		}
		return ap;
	}

	@Override
	public Product getProductById(int pId) {

		return productDao.getProductById(pId);
	}

	@Override
	public Product getProductByName(String pName) {

		return productDao.getProductByName(pName);
	}

	@Override
	public ArrayList<Product> getProductsRangePrice(float minPrice, float maxPrice) {

		return productDao.getProductsRangePrice(minPrice, maxPrice);
	}

	@Override
	public void deleteProductById(int pId) {
		productDao.deleteProductById(pId);
	}

	@Override
	public void updateProductNameById(int pId, String newName) {
		Product productById = productDao.getProductById(pId);
		productById.setpName(newName);
		;
		productDao.updateProduct(productById);
	}

	@Override
	public void updateProductPriceById(int pId, float newPrice) {
		Product productById = productDao.getProductById(pId);
		productById.setpPrice(newPrice);
		;
		productDao.updateProduct(productById);
	}

	@Override
	public void updateProductStockNumById(int pId, int newStockNum) {
		Product productById = productDao.getProductById(pId);
		productById.setStockNum(newStockNum);
		;
		productDao.updateProduct(productById);
	}

}
