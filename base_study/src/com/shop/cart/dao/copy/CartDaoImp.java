package com.shop.cart.dao.copy;

import java.io.File;
import java.sql.DatabaseMetaData;
import java.util.HashMap;
import java.util.List;

import com.shop.cart.pojo.CartItem;
import com.shop.constant.DataBasePathConstant;
import com.shop.utils.FileOperateUtils;

public class CartDaoImp implements CartDao {
	FileOperateUtils<HashMap<String, CartItem>> fos = new FileOperateUtils<>();
/***
 *在数据库中已有该用户的购物车中hashmap 
 */
	@Override
	public boolean addItemToCart(String userId, CartItem item) throws Exception {
		//先从数据文件中加载出整个购物车hashmap
		
		//然后判断购物车中是否存在该用户
			//如果不存在用户，则往购物车map中添加该用户和该商品即可
		
			//如果存在该用户，则再判断该用户的购物项中是否已存在该商品
				//如果存在，则添加数量即可
		
				//如果不存在，则放入一个新的购物项
		
		
		//然后将处理好的购物车hashmap写入数据文件
		return false;
	}

	@Override
	public boolean removeItemFromCart(String userId, String pId) throws Exception {

		return false;
	}

	@Override
	public boolean updateItemBuyNum(String userId, String pId, int buyNum) throws Exception {

		return false;
	}

	@Override
	public List<CartItem> getAllItems(String userId) throws Exception {

		return null;
	}

	@Override
	public boolean checkIfUserExist(String userId) throws Exception {
		
		return false;
	}

	@Override
	public boolean checkIfItemExist(String userId, String pId) throws Exception {
		
		return false;
	}

	@Override
	public boolean addNewUserItemToCart(String userId, CartItem item) throws Exception {
		File file = new File(DataBasePathConstant.CART_DATA_PATH);
		HashMap<String, HashMap<String, CartItem>> cartMap = null;
		if(file.exists()){
			//先从文件中加载出来整个购物车hashmap
			cartMap = fos.getMapFromFile(DataBasePathConstant.CART_DATA_PATH);
		}else{
			//如果该文件不存在则构造一个购物车hashmap
			cartMap = new HashMap<>();
		}
		
		//为该用户构造一个自己的购物项hashmap，并放入购物项
		HashMap<String, CartItem> userItems = new HashMap<>();
		userItems.put(item.getpId(), item);
		//将用户的id和他的购物项hashmap放入整个购物系统的数据库hashmap长
		cartMap.put(userId, userItems);
		//然后后将整个购物数据库写入文件
		fos.setMapToFile(cartMap, DataBasePathConstant.CART_DATA_PATH);
		
		
		return true;
	}
/**
 * 更具用户id和商品id取出用户的购物项数据
 **/
	@Override
	public CartItem getItemById(String userId, String pId) throws Exception {
		//先从数据库中读取整个购物车数据hashmap
		HashMap<String, HashMap<String, CartItem>> cartMap = fos.getMapFromFile(DataBasePathConstant.CART_DATA_PATH);
		HashMap<String, CartItem> userItems = cartMap.get(userId);
		CartItem cartItem = userItems.get(pId);
		
		return cartItem;
	}

}
