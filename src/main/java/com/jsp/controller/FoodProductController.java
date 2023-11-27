package com.jsp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.dto.Admin;
import com.jsp.dto.FoodProduct;
import com.jsp.service.AdminService;
import com.jsp.service.FoodProductService;

@Controller
public class FoodProductController {

	@Autowired
	FoodProductService foodProductService;

	@Autowired
	AdminService adminService;

	@RequestMapping("/createProduct")
	public ModelAndView createProduct() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("productmodel", new FoodProduct());
		modelAndView.setViewName("createproduct.jsp");
		return modelAndView;
	}

	@RequestMapping("/saveProduct")
	public ModelAndView saveProduct(@ModelAttribute FoodProduct foodProduct, HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		int id = (int) httpSession.getAttribute("adminid");
		Admin admin = adminService.getAdmin(id);

		// setting admin object to product
		foodProduct.setAdmin(admin);

		String message = foodProductService.saveProduct(foodProduct);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("result", message);
		modelAndView.setViewName("createfoodproductback.jsp");
		return modelAndView;
	}

	@RequestMapping("/displayFoodProducts")
	public ModelAndView allProducts() {
		List<FoodProduct> foodProducts = foodProductService.getAllProducts();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allproducts", foodProducts);
		modelAndView.setViewName("displayfoodproducts.jsp");
		return modelAndView;
	}

	@RequestMapping("/deleteProduct")
	public ModelAndView deleteProduct(@RequestParam(name = "id") int id) {
		String message = foodProductService.delete(id);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("result", message);
		modelAndView.setViewName("deleteproductback.jsp");
		return modelAndView;
	}

	@RequestMapping("/update")
	public ModelAndView updateById(@RequestParam(name = "id") int id) {
		ModelAndView modelAndView = new ModelAndView("updatefoodproduct.jsp");
		
		
		modelAndView.addObject("foodProduct", new FoodProduct());
		return modelAndView;
	}

	@RequestMapping("/foodProductUpdated")
	public ModelAndView updatedFinally(@ModelAttribute FoodProduct product) {
		foodProductService.update(product, product.getId());
		ModelAndView modelAndView = new ModelAndView("updateproductback.jsp");
		modelAndView.addObject("updated", "foodProduct " + product.getId() + " updated");
		return modelAndView;
	}

	// <h2>${updated}</h2> displayfoodproduct.jsp

	// <h1>${updated}</h1> updatefoodproduct.jsp

}
