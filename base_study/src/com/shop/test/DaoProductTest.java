package com.shop.test;

import java.util.List;

import org.junit.Test;

import com.shop.constant.UserVipLevelConstant;
import com.shop.product.dao.ProductDao;
import com.shop.product.dao.ProductDaoImp;
import com.shop.product.pojo.Product;
import com.shop.user.dao.UserDaoImp;
import com.shop.user.pojo.User;

public class DaoProductTest {
	// @Test
	public void testAddProduct() throws Exception {
		Product p1 = new Product("1", "商品1", 20.0f, 3, "0", "1");
		Product p2 = new Product("2", "商品2", 15.0f, 2, "1", "1");
		Product p3 = new Product("3", "商品3", 13.0f, 1, "2", "1");
		Product p4 = new Product("4", "商品4", 25.0f, 6, "1", "1");
		Product p5 = new Product("5", "商品5", 23.0f, 4, "0", "1");

		ProductDaoImp productDao = new ProductDaoImp();
		productDao.addProduct(p1);
		productDao.addProduct(p2);
		productDao.addProduct(p3);
		productDao.addProduct(p4);
		productDao.addProduct(p5);

		Product product = productDao.getProductById("3");
		System.out.println(product);

	}

	@Test
	public void testShowProductRangeAge() throws Exception {
		ProductDaoImp productDao = new ProductDaoImp();
		// 根据商品价格查询商品
		// List<Product> lProducts= productDao.getProductByPriceRange(12.0f,
		// 22.0f);
		// System.out.println(lProducts);

		// 根据商品类别
		Product productById = productDao.getProductById("2");
		System.out.println(productById);

	}
}
