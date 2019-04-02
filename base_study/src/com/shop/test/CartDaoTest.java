package com.shop.test;

import org.junit.Test;

import com.shop.cart.dao.CartDaoImp;
import com.shop.cart.pojo.CartItem;

public class CartDaoTest {
	@Test
	public void testAddNewUserItem() throws Exception{
		CartDaoImp cartDaoImp = new CartDaoImp();
		CartItem cartItem = new CartItem("p01", "zhangsan", 5.9f, 5);
		
		cartDaoImp.addNewUserItemToCart("zhangsan", cartItem);
		
	}
	@Test
	public void testGetItemById() throws Exception{
		CartDaoImp cartDaoImp = new CartDaoImp();
	
		CartItem itemById = cartDaoImp.getItemById("zhangsan", "p01");
		System.out.println(itemById);
	}
}
