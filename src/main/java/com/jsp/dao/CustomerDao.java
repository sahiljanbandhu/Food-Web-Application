package com.jsp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dto.Customer;

@Component
public class CustomerDao {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	public String saveCustomer(Customer customer) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.persist(customer);
		entityTransaction.commit();
		return customer.getCustomerName() + " is saved";
	}

	public Customer getCustomer(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.find(Customer.class, id);
	}
}
