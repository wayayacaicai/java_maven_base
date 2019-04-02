package com.shop.product.service;

import java.util.List;

import com.shop.product.pojo.Product;

public interface ProductService {
	// 查询所有商品
	public List<Product> getAllProducts() throws Exception;

	// 按类别查询商品
	public List<Product> getProductsByCateName(String cateName) throws Exception;

	// 按价格查询商品
	public List<Product> getProductByPriceRange(float minPrice, float maxPrice) throws Exception;

	// 根据商品id查询商品
	public Product getProductById(String pId) throws Exception;

	// 根据商品字符串信息添加商品
	public boolean addProduct(String productInfoString) throws Exception;

	// 修改商品名称
	public boolean updateProductName(String pId, String pName) throws Exception;

	// 修改商品价格
	public boolean updateProductPrice(String pId, float price) throws Exception;

	// 修改商品库存量
	public boolean updateProductStock(String pId, int stockNum) throws Exception;

	// 修改商品状态（0：上架 1：下架 2：删除）
	public boolean updateProductStatus(String pId, String status) throws Exception;
}
