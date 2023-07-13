package com.servlet_banking_dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.servlet_banking_dto.Customer;

public class Customerdao {
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("dev");
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction=entityManager.getTransaction();

	public List<Customer> check(long mobile) {
		//return entityManager.createQuery("select x from Customer x where mobile=?1").setParameter(1, mobile).getResultList();
		Query query=entityManager.createQuery("select x from Customer x where mobile=?1");
		Query query2=query.setParameter(1, mobile);
		List<Customer> list=query2.getResultList();
		return list;
		
	}

	public List<Customer> check(String email) {
		Query query=entityManager.createQuery("select x from Customer x where email=?1");
		Query query2=query.setParameter(1, email);
		List<Customer> list=query2.getResultList();
		return list;
	}

	public void save(Customer customer) {
		entityTransaction.begin();
		entityManager.persist(customer);
		entityTransaction.commit();
		
	}

	public Customer login(int custid) {
		Customer customer=entityManager.find(Customer.class,custid);
		return customer;
	}

	public void update(Customer customer) {
		entityTransaction.begin();
		entityManager.merge(customer);
		entityTransaction.commit();
		
	}

	
	

}
