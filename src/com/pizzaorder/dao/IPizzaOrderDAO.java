package com.pizzaorder.dao;

import com.pizzaorder.entity.Customer;
import com.pizzaorder.entity.PizzaOrder;
import com.pizzaorder.exception.PizzaException;

public interface IPizzaOrderDAO {

	// to add order
	public int placeOrder(Customer customer, PizzaOrder pizza) throws PizzaException;

	// to get order
	public PizzaOrder getOrderDetails(int orderId) throws PizzaException;
}
