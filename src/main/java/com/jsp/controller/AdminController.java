package com.jsp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.dto.Admin;
import com.jsp.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	@RequestMapping("/createAdmin")
	public ModelAndView createAdmin() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("adminmodel", new Admin());
		modelAndView.setViewName("createadmin.jsp");
		return modelAndView;
	}

	@RequestMapping("/saveAdmin")
	public ModelAndView saveAdmin(@ModelAttribute Admin admin) {
		String message = adminService.saveAdmin(admin);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("result", message);
		modelAndView.setViewName("adminmessage.jsp");
		return modelAndView;
	}

	@RequestMapping("/adminLogin")
	public void adminLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		Admin admin = adminService.findByEmailAndPassword(email, password);

		PrintWriter printWriter = resp.getWriter();
		HttpSession httpSession=req.getSession();
		resp.setContentType("text/html");
		if (admin != null) {

			httpSession.setAttribute("adminid", admin.getId());
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("adminhome.jsp");
			requestDispatcher.forward(req, resp);
		}

		else {
			printWriter.println("<center><h1>INVALID CREDENTIALS</h1></center>");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("adminlogin.jsp");
			requestDispatcher.include(req, resp);
		}

	}
}
