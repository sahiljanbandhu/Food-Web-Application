package com.jsp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dao.FoodProductDao;
import com.jsp.dto.FoodProduct;

@Component
public class FoodProductService {

	@Autowired
	FoodProductDao foodProductDao;

	public String saveProduct(FoodProduct foodProduct) {
		return foodProductDao.saveProduct(foodProduct);
	}

	public FoodProduct getProduct(int id) {
		return foodProductDao.getProduct(id);
	}

	public List<FoodProduct> getAllProducts() {
		return foodProductDao.getAllProducts();
	}

	public String delete(int id) {
		return foodProductDao.delete(id);
	}

	public void update(FoodProduct foodProduct,int id) {
		 foodProductDao.update(foodProduct,id);
	}

}
