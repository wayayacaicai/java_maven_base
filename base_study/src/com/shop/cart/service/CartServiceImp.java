package com.shop.cart.service;

import java.util.List;

import com.shop.cart.dao.CartDao;
import com.shop.cart.pojo.CartItem;
import com.shop.product.dao.ProductDao;
import com.shop.product.dao.ProductDaoImp;
import com.shop.product.pojo.Product;

public class CartServiceImp implements CartService {
	CartDao cartDao = null;
	ProductDao ProductDao = new ProductDaoImp();

	@Override
	public boolean addItemToCart(String userId, String pId, int buyNum) throws Exception {
		boolean addItemToCart = false;

		// 先判断购物车数据库中是否有该用户
		boolean checkIfUserExist = cartDao.checkIfUserExist(userId);
		// 如果该用户确实已存在于购物车数据库
		if (checkIfUserExist) {
			// 再判断该商品是否存在于购物项中
			boolean checkIfItemExist = cartDao.checkIfItemExist(userId, pId);
			if (checkIfItemExist) {
				// 先取出这个人已有的这个购物项数据
				CartItem item = cartDao.getItemById(userId, pId);
				// 然后将之前的数量+本次的数量，通过dao，写入数据库
				cartDao.updateItemBuyNum(userId, pId, buyNum + item.getBuyNum());
			} else {
				// 通过Pid先从商品管理类中的dao获取商品信息
				Product productById = ProductDao.getProductById(pId);
				// 构造一个购物项实体对象，并填入商品数据
				CartItem item = new CartItem();
				item.set(pId, productById.getpName(), productById.getPrice(), buyNum);
				// 再利用购物车dao将这个购物项添加到已有的购物车数据表中
				addItemToCart = cartDao.addItemToCart(userId, item);
			}
			// 如果该用户在购物车数据库中不存在
		} else {
			// 通过Pid先从商品管理类中的dao获取商品信息
			Product productById = ProductDao.getProductById(pId);
			// 构造一个购物项实体对象，并填入商品数据
			CartItem item = new CartItem();
			item.set(pId, productById.getpName(), productById.getPrice(), buyNum);
			// 再利用购物车dao将这个购物项新增到购物车中
			addItemToCart = cartDao.addNewUserItemToCart(userId, item);
		}

		return addItemToCart;
	}

	@Override
	public boolean removeItemFromCart(String userId, String pId) throws Exception {
		boolean removeItemFromCart = cartDao.removeItemFromCart(userId, pId);
		return removeItemFromCart;
	}

	@Override
	public boolean updateItemBuyNum(String userId, String pId, int buyNum) throws Exception {
		boolean updateItemBuyNum = cartDao.updateItemBuyNum(userId, pId, buyNum);
		return updateItemBuyNum;
	}

	@Override
	public List<CartItem> getAllItems(String userId) throws Exception {
		List<CartItem> allItems = cartDao.getAllItems(userId);
		return allItems;
	}

}
