package com.sm.collections.hashmap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


public class StudentDataManagement {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Map<String,Integer> hm = new HashMap<>();
		
		
		
		
		while(true)
		{
			System.out.println("------ Student Management Menu ------\r\n"
					+ "1. Add Student\r\n"
					+ "2. Update Marks\r\n"
					+ "3. Delete Student\r\n"
					+ "4. Search Student\r\n"
					+ "5. Display All Students\r\n"
					+ "6. Show Topper\r\n"
					+ "7. Show Lowest Scorer\r\n"
					+ "8. Show Total and Average Marks\r\n"
					+ "9. Exit\r\n"
					+ "");

			System.out.print("Enter your choice : ");
			int choice = sc.nextInt();
			sc.nextLine();
			System.out.println();
			System.out.println();
			switch(choice)
			{
			case 1: System.out.print("Enter student name : ");
					String name = sc.nextLine();
					System.out.print("\nEnter Student Marks : ");
					int marks = sc.nextInt();
					sc.nextLine();
					hm.put(name, marks);
					System.out.println("Marks updated.");
					break;
			
			case 2 : System.out.print("Enter student name to update the marks : ");
					name = sc.nextLine();
					System.out.print("\nEnter new marks : ");
					
					marks = sc.nextInt();
					sc.nextLine();
					if(!hm.containsKey(name)) {
						System.out.println("Name not found");
					}
					else {

					hm.put(name, marks);
					}
					break;
			case 3 : System.out.println("Enter Student name to Delete");
			         name = sc.nextLine();
			         hm.remove(name);
			         System.out.println("Student deleted.");
			         break;
			case 4 : System.out.println("Enter Student name to Search");
					name = sc.nextLine();
					if(hm.containsKey(name))
					{
						System.out.println(name+" scored "+hm.get(name));
					}
					else {
						System.out.println(name + " not found");
					}
					break;
			case 5 : System.out.println("---- Student Records ----");
					hm.forEach((k,v)->System.out.println(k+"  =>  "+v));
					break;
					
			case 6 : 
				Entry<String,Integer> max= Collections.max(hm.entrySet(),(x,y)->x.getValue()-y.getValue());
					System.out.println("Topper is : "+max);
					break;
					
			case 7 : Entry<String,Integer> min = Collections.min(hm.entrySet(),(x,y)->x.getValue()-y.getValue());
					System.out.println("Lowest : "+min);
					break;
					
			case 8 : 
					int count=0;
					int total = 0;
					for(Integer v : hm.values())
					{
						count++;
						total = total+v;
					}
					int avg = total/count;
					System.out.println("Total Marks : "+total);
					System.out.println("Average Marks : "+avg);
					break;
					
			case 9 : System.out.println("Exiting... Thank you");
				System.exit(0);

			
			}
		}
		
		

	}

}
