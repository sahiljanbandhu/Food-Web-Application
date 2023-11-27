package com.jsp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.dto.Customer;
import com.jsp.dto.FoodOrder;
import com.jsp.dto.FoodProduct;
import com.jsp.service.CustomerService;
import com.jsp.service.FoodOrderService;
import com.jsp.service.FoodProductService;

@Controller
public class FoodOrderController {

	@Autowired
	FoodProductService foodProductService;

	@Autowired
	CustomerService customerService;

	@Autowired
	FoodOrderService foodOrderService;

	@RequestMapping("/createOrder")
	public ModelAndView createOrder() {
		ArrayList<FoodProduct> products = (ArrayList<FoodProduct>) foodProductService.getAllProducts();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allproducts", products);
		modelAndView.setViewName("displayfooditems.jsp");
		return modelAndView;
	}

	@RequestMapping("/confirm")
	public ModelAndView confirmOrder(HttpServletRequest req) {

		HttpSession httpSession = req.getSession();

		Customer customer = (Customer) httpSession.getAttribute("customer");

		customerService.saveCustomer(customer);

		FoodOrder foodOrder = (FoodOrder) httpSession.getAttribute("myorder");
		foodOrderService.saveOrder(foodOrder);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ordermodel", foodOrder);
		modelAndView.setViewName("final.jsp");
		return modelAndView;
	}

	@RequestMapping("/refresh")
	public ModelAndView refresh(HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		httpSession.removeAttribute("customer");
		httpSession.removeAttribute("myorder");
		httpSession.removeAttribute("allitems");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createOrder");
		return modelAndView;
	}
}
