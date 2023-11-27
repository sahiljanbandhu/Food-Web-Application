package com.jsp.controller;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.dto.Customer;
import com.jsp.dto.FoodItem;
import com.jsp.dto.FoodOrder;
import com.jsp.dto.Staff;
import com.jsp.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping("/saveCustomer")
	public ModelAndView saveCustomer(@ModelAttribute Customer customer, HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		Staff staff = (Staff) httpSession.getAttribute("staff");

		FoodOrder foodOrder = new FoodOrder();
		foodOrder.setStaff(staff);
		foodOrder.setCustomer(customer);
		foodOrder.setStaffName(staff.getStaffName());
		foodOrder.setCustomerName(customer.getCustomerName());

		LocalTime time = LocalTime.now();
		LocalDate date = LocalDate.now();

		foodOrder.setTime(Time.valueOf(time));
		foodOrder.setOrderDate(date);

		ArrayList<FoodItem> items = (ArrayList<FoodItem>) httpSession.getAttribute("allitems");
		int count = 0;
		

		foodOrder.setFoodItems(items);
		foodOrder.setNumberOfItems(items.size());

		foodOrder.setCustomer(customer);

		httpSession.setAttribute("myorder", foodOrder);
		httpSession.setAttribute("customer", customer);

		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ordermodel", foodOrder);
		modelAndView.setViewName("orderdetails.jsp");
		return modelAndView;
	}
}
