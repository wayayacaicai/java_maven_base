package com.demo.productPurchase;

import java.util.ArrayList;

import javax.crypto.spec.OAEPParameterSpec;

import org.junit.Test;

public class ProductServiceTest {
	/*
	 * 测试商品添加功能
	 */
	@Test
	public void testAdd() {
		ProductServiceImpl ps = new ProductServiceImpl();
		Product p4 = new Product(4, "足球", 15.5f, 16);
		ps.addProduct(p4);

		// 得到所有商品
		ArrayList<Product> aProducts = ps.getAllProducts();
		for (Product product : aProducts) {
			System.out.println(product);
		}
		System.out.println("---------------");
	}

	/*
	 * 测试通过id查找商品
	 */
	@Test
	public void testGetById() {
		ProductServiceImpl ps = new ProductServiceImpl();
		Product product = ps.getProductById(2);
		System.out.println(product);

		System.out.println("----------------");
	}

	/*
	 * 测试通过name查找商品
	 */
	@Test
	public void testGetByName() {
		ProductServiceImpl ps = new ProductServiceImpl();
		Product product = ps.getProductByName("篮球");
		System.out.println(product);

		System.out.println("----------------");
	}

	/*
	 * 测试价格范围内的商品
	 */
	@Test
	public void testRangePrice() {
		ProductServiceImpl ps = new ProductServiceImpl();
		ArrayList<Product> ap = ps.getProductsRangePrice(20.5f, 35f);
		for (Product product : ap) {
			System.out.println(product);
		}
		System.out.println("--------------");
	}

	/*
	 * 测试删除商品
	 */
	@Test
	public void testDeleteProduct() {
		ProductServiceImpl ps = new ProductServiceImpl();
		ps.deleteProductById(1);
		System.out.println(ps.getAllProducts());
		System.out.println("-------------");
	}

	/*
	 * 测试修改商品单价
	 */
	@Test
	public void testUpdatePrice() {
		ProductServiceImpl ps = new ProductServiceImpl();
		ps.updateProductPriceById(1, 21.5f);
		System.out.println(ps.getAllProducts());
		System.out.println("-------------");
	}

}
