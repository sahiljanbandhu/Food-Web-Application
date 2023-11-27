package com.jsp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dao.FoodOrderDao;
import com.jsp.dto.FoodOrder;

@Component
public class FoodOrderService {

	@Autowired
	FoodOrderDao foodOrderDao;

	public String saveOrder(FoodOrder order) {
		return foodOrderDao.saveOrder(order);
	}

	public FoodOrder getOrder(int id) {
		return foodOrderDao.getOrder(id);
	}

	public List<FoodOrder> getAllOrders() {
		return foodOrderDao.getAllOrders();
	}
}
