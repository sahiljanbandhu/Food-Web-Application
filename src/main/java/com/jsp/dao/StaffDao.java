package com.jsp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dto.Staff;

@Component
public class StaffDao {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	public String saveStaff(Staff staff) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.persist(staff);
		entityTransaction.commit();
		return staff.getStaffName() + " is saved";
	}

	public Staff getStaff(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.find(Staff.class, id);
	}
	
	public Staff findByEmailAndPassword(String email, String password) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Query q = entityManager.createQuery("select s from Staff s where email=?1 and password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);

		try {
			return (Staff) q.getSingleResult();
		} catch (NoResultException e) {

			e.printStackTrace();
		}
		return null;

	}


}
