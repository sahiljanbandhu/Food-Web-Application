package com.jsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dao.AdminDao;
import com.jsp.dto.Admin;

@Component
public class AdminService {

	@Autowired
	AdminDao adminDao;

	public String saveAdmin(Admin admin) {
		return adminDao.saveAdmin(admin);
	}

	public Admin getAdmin(int id) {
		return adminDao.getAdmin(id);
	}

	public Admin findByEmailAndPassword(String email, String password) {
		return adminDao.findByEmailAndPassword(email, password);
	}

}
