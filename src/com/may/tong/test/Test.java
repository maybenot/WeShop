package com.may.tong.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.may.tong.enetities.services.Buyer;
import com.may.tong.services.BuyerService;

public class Test {
	private ApplicationContext cxt = null;
	private EntityManagerFactory entityManagerFactory = null;

	@Before
	public void init() {
		cxt = new ClassPathXmlApplicationContext("applicationContext.xml");
		entityManagerFactory = cxt.getBean(EntityManagerFactory.class);
	}

	@org.junit.Test
	public void test() {
		DataSource dataSource = cxt.getBean(DataSource.class);
		System.out.println(dataSource);

	}

	@org.junit.Test
	public void test2() {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		 
	}

	@org.junit.Test
	public void testSaveBuyer() {

		BuyerService b = cxt.getBean(BuyerService.class);
		Buyer buyer=new Buyer();
		buyer.setUserName("BB");
		buyer.setPassword("123");
		buyer.setGender(1);
		
         b.addBuyer(buyer);
	}
	@org.junit.Test
	public void testDate() {
		
		 Date date=new Date();
		 System.out.println(date.getMinutes());
	}
	
	
    

}
