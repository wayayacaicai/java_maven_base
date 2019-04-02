package com.shop.cart.service;

import java.util.List;

import com.shop.cart.pojo.CartItem;

public interface CartService {

	public boolean addItemToCart(String userId, String pId, int buyNum) throws Exception;

	public boolean removeItemFromCart(String userId, String pId) throws Exception;

	public boolean updateItemBuyNum(String userId, String pId, int buyNum) throws Exception;

	public List<CartItem> getAllItems(String userId) throws Exception;
}
