package com.jsp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dto.Admin;

@Component
public class AdminDao {

	@Autowired
	EntityManagerFactory entityManagerFactory;

//	EntityManager entityManager = entityManagerFactory.createEntityManager();
//	EntityTransaction entityTransaction = entityManager.getTransaction();

	public String saveAdmin(Admin admin) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.persist(admin);
		entityTransaction.commit();
		return admin.getEmail() + " is saved";
		// return admin;
	}

	public Admin getAdmin(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.find(Admin.class, id);

	}

	public Admin findByEmailAndPassword(String email, String password) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Query q = entityManager.createQuery("select s from Admin s where email=?1 and password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);

		try {
			return (Admin) q.getSingleResult();
		} catch (NoResultException e) {

			e.printStackTrace();
		}
		return null;

	}

}
