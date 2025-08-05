package com.sm.multithreading.CoronaVaccineApp;


public class CoronaVaccineApp {
 public static void main(String[] args) {
     User user1 = new User("Ramesh", 18, true);
     User user2 = new User("Suresh", 16, false);

     Thread thread1 = new Thread(() -> {
         try {
             user1.bookDose();
         } catch (RuntimeException e) {
             System.out.println(e.getMessage());
         }
     });

     Thread thread2 = new Thread(() -> {
    	    try {
    	        user2.bookDose();
    	    } catch (RuntimeException e) {
    	        System.out.println(e.getMessage());
    	    }
    	});

     thread1.start();
     thread2.start();

     try {
		thread1.join();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
     try {
		thread2.join();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
 }
}
