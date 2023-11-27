package com.jsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.dto.Customer;
import com.jsp.dto.FoodItem;
import com.jsp.dto.FoodProduct;
import com.jsp.service.FoodProductService;
import com.jsp.util.BillService;

@Controller
public class FoodItemController {

	static int count = 0;

	@Autowired
	FoodProductService foodProductService;

	@Autowired
	BillService billService;

	@RequestMapping("/addItem")
	public ModelAndView addItem(@RequestParam int id) {
		FoodProduct foodProduct = foodProductService.getProduct(id);
		FoodItem foodItem = new FoodItem();

		foodItem.setItemName(foodProduct.getFoodName());
		foodItem.setPrice(foodProduct.getCost());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("foodItem", foodItem);
		modelAndView.setViewName("quantity.jsp");
		return modelAndView;
	}

	@RequestMapping("/toOrder")
	public ModelAndView toOrder(@ModelAttribute FoodItem foodItem, HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		Object o = httpSession.getAttribute("allitems");

		double totalCost = billService.calculate(foodItem.getQuantity(), foodItem.getPrice());
		foodItem.setTotalCost(totalCost);

		if (o == null) {

			ArrayList<FoodItem> items = new ArrayList();
			items.add(foodItem);
			httpSession.setAttribute("allitems", items);
		} else {

			ArrayList<FoodItem> items = (ArrayList) httpSession.getAttribute("allitems");
			items.add(foodItem);
			httpSession.setAttribute("allitems", items);
		}
		System.out.println(foodItem);

		ArrayList<FoodProduct> products = (ArrayList<FoodProduct>) foodProductService.getAllProducts();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allproducts", products);
		modelAndView.setViewName("displayfooditems.jsp");
		return modelAndView;

	}

	@RequestMapping("/cart")
	public ModelAndView cart(HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		ArrayList<FoodItem> items = (ArrayList<FoodItem>) httpSession.getAttribute("allitems");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("myitems", items);
		modelAndView.setViewName("cart.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/deleteItem")
	public ModelAndView deleteItem(@RequestParam int value,HttpServletRequest req) {

		HttpSession httpSession=req.getSession();
		ArrayList<FoodItem>items=(ArrayList<FoodItem>) httpSession.getAttribute("allitems");
		items.remove(value);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("myitems", items);
		modelAndView.setViewName("cart.jsp");
		return modelAndView;
	}

	
	

	@RequestMapping("/next")
	public ModelAndView next() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("customermodel", new Customer());
		modelAndView.setViewName("customer.jsp");
		return modelAndView;
	}

	@RequestMapping("/add")
	public void add(@RequestParam String food, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter printWriter = resp.getWriter();
		resp.setContentType("text/html");
		HttpSession httpSession = req.getSession();
		Object o = httpSession.getAttribute("fooditems");
		if (o == null) {
			ArrayList al = new ArrayList();
			al.add(food);
			httpSession.setAttribute("fooditems", al);
		} else {
			ArrayList al = (ArrayList) httpSession.getAttribute("fooditems");
			al.add(food);
			httpSession.setAttribute("fooditems", al);
		}
		printWriter.println("<center><h3>" + food + " is added" + "</h3></center>");
		RequestDispatcher dispatcher = req.getRequestDispatcher("displayfooditems.jsp");
		dispatcher.include(req, resp);

	}

}
