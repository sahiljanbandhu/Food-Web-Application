package com.jsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dao.StaffDao;
import com.jsp.dto.Staff;

@Component
public class StaffService {

	@Autowired
	StaffDao staffDao;
	
	public String saveStaff(Staff staff) {
		return staffDao.saveStaff(staff);
	}

	public Staff getStaff(int id) {
		return staffDao.getStaff(id);
	}
	
	public Staff findByEmailAndPassword(String email, String password) {
		return staffDao.findByEmailAndPassword(email, password);
	}


}
