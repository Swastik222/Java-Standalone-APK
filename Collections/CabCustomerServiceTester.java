package com.sm.collections.arrayList;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;

public class CabCustomerServiceTester {

	public static void main(String[] args) {
		
		CustomerService service = new CustomerService();
		
		try {
		Customer c1 = new Customer(101,"Pranav","Manjeera Square","SR Nagar",4,"9898989898");
		service.printBill(c1);
		service.addCustomer(c1);
		
		Customer c2 = new Customer(102,"Rohit","Manjeera Square","SR Nagar",4,"9898989899");
		service.printBill(c2);
		service.addCustomer(c2);
		
		Customer c3 = new Customer(101,"Pranav","Manjeera Square","SR Nagar",6,"9898989898");
		service.printBill(c3);
		service.addCustomer(c3);
		
		Customer c4 = new Customer(102,"Rohit","Manjeera Square","SR Nagar",6,"9898989899");
		service.printBill(c4);
		service.addCustomer(c4);
		
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
		}
		
		   // ❌ Validation Test Case 1
        try {
            Customer invalid1 = new Customer(-1, "Test", "Loc1", "Loc2", 5, "9876543212");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // ❌ Validation Test Case 2
        try {
            Customer invalid2 = new Customer(104, "", "Loc1", "Loc2", 5, "9876543212");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // ❌ Validation Test Case 3
        try {
            Customer invalid3 = new Customer(105, "Test", null, "Loc2", 5, "9876543212");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // ❌ Validation Test Case 4
        try {
            Customer invalid4 = new Customer(106, "Test", "Loc1", "", 5, "9876543212");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
        // ❌ Validation Test Case 5
        try {
            Customer invalid5 = new Customer(107, "Test", "Loc1", "Loc2", -5, "9876543212");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // ❌ Validation Test Case 6
        try {
            Customer invalid6 = new Customer(108, "Test", "Loc1", "Loc2", 5, "12345");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


		
	}

}


record Customer(int custId,String custName,String pickupLoacation, String dropLocation, int distance, String phone) {
	
	public Customer {
		if(custId <= 0) throw new IllegalArgumentException("Customer id must be postive");
		
		if(custName == null || custName.trim().isEmpty()) throw new IllegalArgumentException("Customer name cannot be null or blank.");
		
		if((pickupLoacation == null || pickupLoacation.trim().isEmpty()) || (dropLocation == null || pickupLoacation.trim().isEmpty())) throw new IllegalArgumentException("Pickup or Drop location cannot be null or blank.");
		
		if(distance <=0 ) throw new IllegalArgumentException("Distance cannot be negative");
		
		if(phone == null  || !phone.matches("\\d{10}")  ) throw new IllegalArgumentException("Phone number must be 10 digits only.");
		
		
		
	}
	
	
}

class CustomerService {
	private ArrayList<Customer> customers;
	
	   CustomerService() {
		   customers = new ArrayList<>();
	   }
	   
	   public void addCustomer(Customer cust)
	   {
		   if(isFirstCustomer(cust))
		   {
		   customers.add(cust);
		   }
		   else System.out.println("Customer with phone num "+cust.phone() + " is already exist !");
	   }
	   
	   private boolean isFirstCustomer(Customer cust)
	   {
		   for(Customer exist : customers)
		   {
			   if(exist.phone().equals(cust.phone()))
				   return false;
		   }
		   return  true;
		  
		   
	   }
	   
	   public double calculateBill(Customer cust)
	   {
		   if(isFirstCustomer(cust))
		   {
			   return 0.00;
		   }
		   int distance = cust.distance();
		   if(distance <= 4) return 80.0;
		   else {
			   return 80.0 + (6.0 * distance);
		   }
	   }
	   
	   public void printBill(Customer cust)
	   {
		   double bill = calculateBill(cust);
		   System.out.println(cust.custName() + " Please pay your bill of Rs. "+bill);
	   }
	   
	   
}