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
import com.jsp.dto.Staff;
import com.jsp.service.AdminService;
import com.jsp.service.StaffService;

@Controller
public class StaffController {

	@Autowired
	StaffService staffService;

	@Autowired
	AdminService adminService;

	@RequestMapping("/createStaff")
	public ModelAndView createStaff() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("staffmodel", new Staff());
		modelAndView.setViewName("createstaff.jsp");
		return modelAndView;
	}

	@RequestMapping("/saveStaff")
	public ModelAndView saveStaff(@ModelAttribute Staff staff, HttpServletRequest req) {

		HttpSession httpSession = req.getSession();
		int id = (int) httpSession.getAttribute("adminid");
		Admin admin = adminService.getAdmin(id);
		staff.setAdmin(admin);
		String message = staffService.saveStaff(staff);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("result", message);
		modelAndView.setViewName("savestaffback.jsp");
		return modelAndView;
	}

	@RequestMapping("/staffLogin")
	public void adminLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		Staff staff = staffService.findByEmailAndPassword(email, password);

		PrintWriter printWriter = resp.getWriter();
		HttpSession httpSession = req.getSession();
		resp.setContentType("text/html");
		if (staff != null) {

			httpSession.setAttribute("staff", staff);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("staffhome.jsp");
			requestDispatcher.forward(req, resp);
		}

		else {
			printWriter.println("<center><h1>INVALID CREDENTIALS</h1></center>");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("stafflogin.jsp");
			requestDispatcher.include(req, resp);
		}

	}

}
