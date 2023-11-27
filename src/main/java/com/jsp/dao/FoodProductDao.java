package com.jsp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dto.FoodProduct;

@Component
public class FoodProductDao {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	public String saveProduct(FoodProduct foodProduct) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.persist(foodProduct);
		entityTransaction.commit();
		return foodProduct.getFoodName() + " is saved";
	}

	public FoodProduct getProduct(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.find(FoodProduct.class, id);

	}

	public List<FoodProduct> getAllProducts() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		String sql = "Select s From FoodProduct s";
		Query query = entityManager.createQuery(sql);
		List<FoodProduct> products = query.getResultList();
		return products;
	}

	public String delete(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		FoodProduct foodProduct = entityManager.find(FoodProduct.class, id);
		if (foodProduct != null) {
			entityTransaction.begin();
			entityManager.remove(foodProduct);
			entityTransaction.commit();
			return foodProduct.getFoodName() + " is deleted";

		}
		return null;

	}

	public void update( FoodProduct foodProduct,int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		FoodProduct foodProduct1 = entityManager.find(FoodProduct.class, id);
		if (foodProduct1 != null) {
			entityTransaction.begin();
			if (foodProduct.getFoodName() != null) {
				foodProduct1.setFoodName(foodProduct.getFoodName());
			}
			if (foodProduct.getFoodType() != null) {
				foodProduct1.setFoodType(foodProduct.getFoodType());
			}
			if (foodProduct.getDescription() != null) {
				foodProduct1.setDescription(foodProduct.getDescription());
			}
			if (foodProduct.getCost() != 0) {
				foodProduct1.setCost(foodProduct.getCost());
			}

			entityManager.merge(foodProduct1);
			entityTransaction.commit();
			
		}
		
	}
	

}
