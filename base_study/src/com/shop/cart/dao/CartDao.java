package com.shop.cart.dao;

/**
 * 购物车数据访问层接口定义
 * */
import java.util.List;

import com.shop.cart.pojo.CartItem;

public interface CartDao {
	// 此方法，用于购物车数据库中已有该用户的购物项
	public boolean addItemToCart(String userId, CartItem item) throws Exception;

	// 此方法，用户这个用户第一次购物
	public boolean addNewUserItemToCart(String userId, CartItem item) throws Exception;

	public boolean removeItemFromCart(String userId, String pId) throws Exception;

	public boolean updateItemBuyNum(String userId, String pId, int buyNum) throws Exception;

	public List<CartItem> getAllItems(String userId) throws Exception;

	// 判断数据库是否存在某个人的购物车
	public boolean checkIfUserExist(String userId) throws Exception;

	// 判断某个人的购物车中是否有指定的商品
	public boolean checkIfItemExist(String userId, String pId) throws Exception;

	// 根据指定的userid和商品id从购物车中取出购物项
	public CartItem getItemById(String userId, String pId) throws Exception;

}
