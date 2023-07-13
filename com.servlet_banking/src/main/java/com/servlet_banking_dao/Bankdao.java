package com.servlet_banking_dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.servlet_banking_dto.BankAccount;

public class Bankdao {
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("dev");
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	EntityTransaction transaction=entityManager.getTransaction();
	


	public void save(BankAccount account) {
	transaction.begin();
	entityManager.persist(account);
	transaction.commit();
	}



	public  List<BankAccount> fetchAll() {
		Query query= entityManager.createQuery("select x from BankAccount x");
		List<BankAccount> list=query.getResultList();
		return list;	
	}



	public BankAccount find(long acno) {
		BankAccount account=entityManager.find(BankAccount.class, acno);
		return account;
	}



	public void update(BankAccount account) {
		transaction.begin();
		entityManager.merge(account);
		transaction.commit();
		
	}
	

}
