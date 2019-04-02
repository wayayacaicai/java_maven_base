package com.shop.product.service;

import java.util.List;

import com.shop.product.dao.ProductDao;
import com.shop.product.dao.ProductDaoImp;
import com.shop.product.pojo.Product;

public class ProductServiceImp implements ProductService {
	ProductDao productDao = new ProductDaoImp();

	@Override
	public List<Product> getAllProducts() throws Exception {
		List<Product> allProducts = productDao.getAllProducts();
		return allProducts;
	}

	@Override
	public List<Product> getProductsByCateName(String cateName) throws Exception {
		List<Product> productsByCateName = productDao.getProductsByCateName(cateName);
		return productsByCateName;
	}

	@Override
	public List<Product> getProductByPriceRange(float minPrice, float maxPrice) throws Exception {
		List<Product> productByPriceRange = productDao.getProductByPriceRange(minPrice, maxPrice);
		return productByPriceRange;
	}

	@Override
	public Product getProductById(String pId) throws Exception {
		Product productById = productDao.getProductById(pId);
		return productById;
	}

	@Override
	public boolean addProduct(String productInfoString) throws Exception {
		String[] split = productInfoString.split(",");
		Product product = new Product(split[0], split[1], Float.parseFloat(split[2]), Integer.parseInt(split[3]),
				split[4], split[5]);
		boolean addProduct = productDao.addProduct(product);
		return addProduct;
	}

	@Override
	public boolean updateProductName(String pId, String pName) throws Exception {
		Product productById = productDao.getProductById(pId);
		productById.setpName(pName);
		boolean updateProduct = productDao.updateProduct(productById);
		return updateProduct;
	}

	@Override
	public boolean updateProductPrice(String pId, float price) throws Exception {
		Product productById = productDao.getProductById(pId);
		productById.setPrice(price);
		boolean updateProduct = productDao.updateProduct(productById);
		return updateProduct;
	}

	@Override
	public boolean updateProductStock(String pId, int stockNum) throws Exception {
		Product productById = productDao.getProductById(pId);
		productById.setStockNum(stockNum);
		boolean updateProduct = productDao.updateProduct(productById);
		return updateProduct;
	}

	@Override
	public boolean updateProductStatus(String pId, String status) throws Exception {
		Product productById = productDao.getProductById(pId);
		productById.setStatus(status);
		boolean updateProduct = productDao.updateProduct(productById);
		return updateProduct;
	}

}
