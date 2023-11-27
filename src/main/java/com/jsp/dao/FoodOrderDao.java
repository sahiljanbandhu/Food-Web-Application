package com.jsp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dto.FoodOrder;

@Component
public class FoodOrderDao {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	public String saveOrder(FoodOrder order) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.persist(order);
		entityTransaction.commit();
		return order.getCustomerName() + " 's order is saved";
	}

	public FoodOrder getOrder(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.find(FoodOrder.class, id);

	}

	public List<FoodOrder> getAllOrders() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		String sql = "Select f From FoodOrder f";
		Query query = entityManager.createQuery(sql);
		List<FoodOrder> orders = query.getResultList();
		return orders;
	}

}
