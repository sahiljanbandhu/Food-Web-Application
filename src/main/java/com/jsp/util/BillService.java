package com.jsp.util;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.jsp.dto.FoodItem;

@Component
public class BillService {

	public double calculate(int quantity, double price) {
		return quantity * price;
	}

	public double totalBill(ArrayList<FoodItem> items) {

		double totalAmount = 0;
		for (FoodItem item : items) {

			double cost = item.getQuantity() * item.getPrice();
			totalAmount = totalAmount + cost;

		}
		return totalAmount;
	}
}
