package com.shop.product.dao;

import java.util.List;

import com.shop.product.pojo.Product;

public interface ProductDao {
	// 查询所有商品
	public List<Product> getAllProducts() throws Exception;

	// 按类别查询商品
	public List<Product> getProductsByCateName(String cateName) throws Exception;

	// 按价格查询商品
	public List<Product> getProductByPriceRange(float minPrice, float maxPrice) throws Exception;

	// 根据商品id查询商品
	public Product getProductById(String pId) throws Exception;

	// 添加商品到数据库
	public boolean addProduct(Product p) throws Exception;

	// 更新商品到数据库
	public boolean updateProduct(Product p) throws Exception;
}
