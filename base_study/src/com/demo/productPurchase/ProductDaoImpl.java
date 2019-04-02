package com.demo.productPurchase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ProductDaoImpl implements ProductDao {

	@Override
	public void addProduct(Product p) {
		ProductDatabase.hMap.put(p.getpId(), p);
	}

	@Override
	public Set<Product> getAllProducts() {
		Collection<Product> cProducts = ProductDatabase.hMap.values();
		Set<Product> pSet = new HashSet<>();
		for (Product product : cProducts) {
			pSet.add(product);
		}

		return pSet;
	}

	@Override
	public Product getProductById(int pId) {
		Product product = ProductDatabase.hMap.get(pId);

		return product;
	}

	@Override
	public Product getProductByName(String pName) {
		Collection<Product> cp = ProductDatabase.hMap.values();
		for (Product product : cp) {
			if (product.getpName().equals(pName)) {
				return product;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Product> getProductsRangePrice(float minPrice, float maxPrice) {
		Collection<Product> cp = ProductDatabase.hMap.values();
		ArrayList<Product> al = new ArrayList<>();
		for (Product product : cp) {
			if (product.getpPrice() >= minPrice && product.getpPrice() <= maxPrice) {
				al.add(product);
			}
		}

		return al;
	}

	@Override
	public void deleteProductById(int pId) {
		ProductDatabase.hMap.remove(pId);
	}

	@Override
	public void updateProduct(Product p) {
		ProductDatabase.hMap.put(p.getpId(), p);
	}

}
