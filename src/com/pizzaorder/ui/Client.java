package com.pizzaorder.ui;

import java.time.LocalDate;
import java.util.Scanner;

import com.pizzaorder.entity.Customer;
import com.pizzaorder.entity.PizzaOrder;
import com.pizzaorder.exception.PizzaException;
import com.pizzaorder.service.IPizzaOrderService;
import com.pizzaorder.service.PizzaOrderService;

public class Client {
	public static void main(String[] args) {
		// create a objects of customer and pizzaorder
		Customer customer = null;
		PizzaOrder pizza = null;

		// instantiating service class
		IPizzaOrderService service = new PizzaOrderService();//create object 

		Scanner scan = new Scanner(System.in);

		System.out.println("Welcome to JustEat Pizza");
		System.out.println("Select one of the below options.");

		// will store main menu choice
		String menuChoice = "";
		

		// infinite loop until user exits its
		for (;;) {
			 customer = new Customer();
			 pizza = new PizzaOrder();
			while (true) // to get main menu choice
				
			{
				System.out.println("1. Place Order\n2. Display order\n3. Exit");
				try {
					menuChoice = scan.next();
					System.out.println(menuChoice);
					boolean isValid = service.validateMenuChoice(menuChoice);
					if (isValid)
						break;
				} catch (PizzaException e) {
					System.out.println(e.getMessage());
				}
			}// end of main menu while loop

			if (menuChoice.equals("1"))// place order
			{
				while (true)// set customername
				{
					System.out
							.println("Enter Your First Name With 1st letter Capital");
					String name = scan.next();
					try {
						boolean isValid = service.validateName(name);//changed
						if (isValid) {
							customer.setCustName(name);
							break;
						}
					} catch (PizzaException e) {
						System.out.println(e.getMessage());
					}
				}// end of customername

				while (true)// set address
				{
					System.out.println("Enter address");
					String address = scan.next();
					try {
						boolean isValid = service.validateAddress(address);
						if (isValid) {
							customer.setAddress(address);
							break;
						}
					} catch (PizzaException e) {
						//System.exit(0);//terminates program
						System.out.println(e.getMessage());
					}
				}// end of address

				while (true)// set mobile number
				{
					System.out.println("Enter 10 digit mobile number");
					String mobileNumber = scan.next();
					try {
						boolean isValid = service.validateMobile(mobileNumber);
						if (isValid) {
							customer.setPhone(mobileNumber);
							break;
						}
					} catch (PizzaException e) {
						e.printStackTrace();//prints error
						System.out.println(e.getMessage());
					}
				}// end of mobile number

				while (true)// select topping
				{
					System.out.println("Select a Toppings");
					System.out.println("Pizza Topping\tPrice");
					System.out.println("1. Capsicum\t30");
					System.out.println("2. Mushroom\t50");
					System.out.println("3. Jalapeno\t70");
					System.out.println("4. Paneer\t85");

					try {
						String toppingChoice = scan.next();
						// check if valid inputs
						boolean isValid = service.validateToppingChoice(toppingChoice);
						if (isValid) {
							switch (toppingChoice) {
							// set prices
							case "1":
								pizza.setToppingName("Capsicum");
								pizza.setTotalPrice(350 + 30);
								break;
							case "2":
								pizza.setToppingName("Mushroom");
								pizza.setTotalPrice(350 + 50);
								break;
							case "3":
								pizza.setToppingName("Jalapeno");
								pizza.setTotalPrice(350 + 70);
								break;
							case "4":
								pizza.setToppingName("Paneer");
								pizza.setTotalPrice(350 + 85);
								break;
							default:
								break;
							}
							break;
						}

					} catch (PizzaException e) {
						// if invalid inputs of toppings
						System.out.println(e.getMessage());
					}
				}// end of topping

				// order the pizza
				try {
					//sset current date
					pizza.setOrderDate(LocalDate.now());
					//get orderid if sucessfully order placed
					int orderID = service.placeOrder(customer, pizza);
					
					//print the result
					System.out.println("Pizza Order Successfully placed with Order id : "+ orderID);
					System.out.println("Order date : " + LocalDate.now());
					System.out.println("Total Bill : "+pizza.getTotalPrice());
				} catch (PizzaException e) {
					System.out.println(e.getMessage());
				}
			}// end of 1st main choice

			else if (menuChoice.equals("2"))// start of second choice
			{
				try {
					//get order id from user
					System.out.println("Enter Order ID");
					String orderID = scan.next();
					//get data of order
					PizzaOrder getPizza = service.getOrderDetails(Integer.parseInt(orderID));
					
					//print the result using toString method of PizzaOrder
					System.out.println("Your order details are as follows");
					System.out.println(getPizza);
				} catch (PizzaException e) {
					System.out.println(e.getMessage());
				} catch (NumberFormatException e) {
					System.out.println("Enter valid Number");
				}
				
			}// end of second choice
			else // exit
			{
				System.out.println("Thank You. Visit Again.");
				break;
			}
			
		}// end of for loop
	}// end of main method
}
