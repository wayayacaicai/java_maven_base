package com.shop.product.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.shop.constant.DataBasePathConstant;
import com.shop.product.pojo.Product;
import com.shop.utils.FileOperateUtils;

public class ProductDaoImp implements ProductDao {
	FileOperateUtils<Product> fop = new FileOperateUtils<>();

	@Override
	public List<Product> getAllProducts() throws Exception {
		List<Product> alp = new ArrayList<>();

		HashMap<String, Product> mapFromFile = fop.getMapFromFile(DataBasePathConstant.PRODUCT_DATA_PATH);
		Collection<Product> values = mapFromFile.values();

		for (Product product : values) {
			alp.add(product);
		}

		return alp;
	}

	@Override
	public List<Product> getProductsByCateName(String cateName) throws Exception {
		List<Product> alp = new ArrayList<>();

		HashMap<String, Product> mapFromFile = fop.getMapFromFile(DataBasePathConstant.PRODUCT_DATA_PATH);
		Collection<Product> values = mapFromFile.values();
		for (Product product : values) {
			if (product.getCategoryName().contains(cateName)) {
				alp.add(product);
			}
		}
		return alp;

	}

	@Override
	public List<Product> getProductByPriceRange(float minPrice, float maxPrice) throws Exception {
		List<Product> alp = new ArrayList<>();

		HashMap<String, Product> mapFromFile = fop.getMapFromFile(DataBasePathConstant.PRODUCT_DATA_PATH);
		Collection<Product> values = mapFromFile.values();
		for (Product product : values) {
			if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
				alp.add(product);
			}
		}
		return alp;
	}

	@Override
	public Product getProductById(String pId) throws Exception {
		HashMap<String, Product> mapFromFile = fop.getMapFromFile(DataBasePathConstant.PRODUCT_DATA_PATH);

		return mapFromFile.get(pId);
	}

	@Override
	public boolean addProduct(Product p) throws Exception {
		HashMap<String, Product> mapFromFile = fop.getMapFromFile(DataBasePathConstant.PRODUCT_DATA_PATH);
		mapFromFile.put(p.getpId(), p);
		fop.setMapToFile(mapFromFile, DataBasePathConstant.PRODUCT_DATA_PATH);
		return true;
	}

	@Override
	public boolean updateProduct(Product p) throws Exception {
		HashMap<String, Product> mapFromFile = fop.getMapFromFile(DataBasePathConstant.PRODUCT_DATA_PATH);
		mapFromFile.put(p.getpId(), p);
		fop.setMapToFile(mapFromFile, DataBasePathConstant.PRODUCT_DATA_PATH);
		return true;
	}

}
