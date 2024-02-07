package com.pizzaorder.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pizzaorder.entity.Customer;
import com.pizzaorder.entity.PizzaOrder;
import com.pizzaorder.exception.PizzaException;
import com.pizzaorder.service.IPizzaOrderService;
import com.pizzaorder.service.PizzaOrderService;

public class OrderSearchTest {

	IPizzaOrderService service;

	@Before
	public void setUp() {
		service = new PizzaOrderService();
	}

	// right inputs
	@Test
	public void orderTest() {
		Customer customer = new Customer();
		customer.setAddress("malad");
		customer.setCustName("Tushar");
		customer.setPhone("8286703935");

		PizzaOrder pizza = new PizzaOrder();
		pizza.setToppingName("Mushroom");
		pizza.setTotalPrice(400);

		try {
			int orderId = service.placeOrder(customer, pizza);
			PizzaOrder getorder = service.getOrderDetails(orderId);
			Assert.assertNotNull(getorder);
		} catch (PizzaException e) {
			System.out.println(e.getMessage());
		}
	}

	// passing wrong inputs
	// should catch and print message from dao
	@Test
	public void orderTest2() {
		Customer customer = new Customer();
		customer.setAddress("malad");
		customer.setCustName("Tushar");
		customer.setPhone("8286703935");

		PizzaOrder pizza = new PizzaOrder();
		pizza.setToppingName("Mushroom");
		pizza.setTotalPrice(400);

		try {
			int orderId = service.placeOrder(customer, pizza);
			PizzaOrder getorder = service.getOrderDetails(orderId + 1);// wrong
																		// id
			Assert.assertNotNull(getorder);
		} catch (PizzaException e) {
			System.out.println(e.getMessage());
		}
	}

	// should set total price same
	@Test
	public void orderTestPrice() {
		Customer customer = new Customer();
		customer.setAddress("malad");
		customer.setCustName("Tushar");
		customer.setPhone("8286703935");

		PizzaOrder pizza = new PizzaOrder();
		pizza.setToppingName("Mushroom");
		pizza.setTotalPrice(400);// passing the price

		try {
			int orderId = service.placeOrder(customer, pizza);
			PizzaOrder getorder = service.getOrderDetails(orderId);
			Assert.assertEquals(400, getorder.getTotalPrice(), 0);
		} catch (PizzaException e) {
			System.out.println(e.getMessage());
		}
	}

	@After
	public void destroy() {
		service = new PizzaOrderService();
	}
}
