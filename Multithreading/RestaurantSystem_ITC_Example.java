package com.sm.multithreading;

import java.util.Scanner;

public class RestaurantSystem_ITC_Example {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Resturant name");
		String name = sc.nextLine();
		System.out.println("Enter Your order name");
		String orderName = sc.nextLine();
		Resturant res = new Resturant(name);
		Waiter waitter = new Waiter(res);
		Chef chef = new Chef(res);
		
		
		System.out.println("Welcome to "+res.getRestaurantName()+" Resturant");
		waitter.acceptOrder(orderName);
		waitter.start();
		chef.start();
		
		sc.close();
		
	}

}

class Resturant{
	private String name;
	private String order;
	private boolean isOrderReady = false;
	
	public Resturant(String name) {
		super();
		this.name = name;
	}
	
	public synchronized void placeOrder(String order)
	{
		this.order = order;
		 System.out.println("Waiter: Placed order for " + order);
	        try {
	            while (!isOrderReady) {
	                wait(); 
	            }
	            System.out.println("Waiter: Serving the " + this.order);
	        } catch (InterruptedException e) {
	        	System.err.println("Order get interrupted"+e);	        }

	}
	
	public synchronized void prepareOrder()
	{
		System.out.println("Chef: Preparing  "+ order);
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			System.err.println("Order get interrupted"+e);
		}
		isOrderReady = true;
		System.out.println("Chef: "+this.order+" is ready!");
		notify();
		
	}
	
	public String getRestaurantName()
	{
		return this.name;
	}
	
	
}

class Waiter extends Thread{
	private Resturant resturant;
	private String orderName;
	
	public Waiter(Resturant resturant)
	{
		this.resturant = resturant;
	}
	
	public void acceptOrder(String orderName)
	{
		this.orderName = orderName;
	}
	
	public void run()
	{
		 resturant.placeOrder(orderName);
	}
	
}

class Chef extends Thread{
	private Resturant resturant;
	
	public Chef(Resturant resturant)
	{
		this.resturant = resturant;
		
	}
	
	public void run()
	{
		resturant.prepareOrder();
	}
}


