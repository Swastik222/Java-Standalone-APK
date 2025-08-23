package com.sm.collections.project;

import java.util.*;

public class BookLibrary {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Library lib = new Library();

		while (true) {
			System.out.println("\n1. Add Book");
			System.out.println("2. Display Books");
			System.out.println("3. Update Quantity");
			System.out.println("4. Delete Book");
			System.out.println("5. Exit");
			System.out.print("Choose an option: ");

			int choice = sc.nextInt();
			sc.nextLine(); // consume newline

			switch (choice) {
			case 1:
				System.out.print("Enter Title: ");
				String title = sc.nextLine();
				System.out.print("Enter Author: ");
				String author = sc.nextLine();
				System.out.print("Enter ISBN: ");
				long isbn = sc.nextLong();
				System.out.print("Enter Price: ");
				double price = sc.nextDouble();
				System.out.print("Enter Quantity: ");
				int qty = sc.nextInt();

				Book b = new Book(title, author, isbn, price, qty);
				lib.addBook(b);
				break;

			case 2:
				lib.displayAllBooks();
				break;

			case 3:
				System.out.print("Enter ISBN: ");
				long updateIsbn = sc.nextLong();
				lib.updateBookQuantityByISBN(updateIsbn);
				break;

			case 4:
				System.out.print("Enter Book Title : ");
				String delTitle = sc.nextLine();
				lib.deleteBookByTitle(delTitle);
				break;

			case 5:
				System.out.println("Exiting from the Application. Thank you!!!");
				System.exit(0);
				break;

			default:
				System.out.println("Invalid choice. Try again.");
			}
		}
	}
}

class Book {
	private String title;
	private String author;
	private long isbn;
	private double price;
	private int quantity;

	// Parameterized Constructor
	public Book(String title, String author, long isbn, double price, int quantity) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.quantity = quantity;
	}

	// Getters and Setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// Display Method
	public String displayBook() {
		return "(title=" + title + ", author=" + author + ", isbn=" + isbn + ", price=" + price + ", quantity="
				+ quantity + ")";
	}
}

class Library {
	private ArrayList<Book> books;

	// No-arg constructor
	public Library() {
		books = new ArrayList<>();
	}

	// 1. Add Book
	public void addBook(Book book) {
		if (searchBookByISBN(book.getIsbn()) == null) {
			books.add(book);
			System.out.println("Book added successfully.");
		} else {
			System.out.println("Book with the same ISBN " + book.getIsbn() + " already exists.");
		}
	}

	// 2. Helper method (private) - Search by ISBN
	private Book searchBookByISBN(long isbn) {
		for (Book b : books) {
			if (b.getIsbn() == isbn) {
				return b;
			}
		}
		return null;
	}

	// 3. Display all books
	public void displayAllBooks() {
		if (books.isEmpty()) {
			System.out.println("No books available in the library.");
		} else {
			for (Book b : books) {
				System.out.println(b.displayBook());
			}
		}
	}

	// 4. Update Quantity
	public void updateBookQuantityByISBN(long isbn) {
		Book b = searchBookByISBN(isbn);
		if (b != null) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Updating quantity of book : ");
			System.out.print("Enter new Quantity: ");
			int newQty = sc.nextInt();
			if (newQty <= 0) {
				System.out.println("Invalid quantity.");
			} else {
				b.setQuantity(newQty);
				System.out.println("Quantity updated successfully. new quantity = " + newQty);
			}
		} else {
			System.out.println("Book with ISBN " + isbn + " not found.");
		}
	}

	// 5. Delete book by Title
	public void deleteBookByTitle(String title) {
		if (title == null || title.trim().isEmpty()) {
			System.out.println("Book Title cannot be blank");
			return;
		}
		Iterator<Book> it = books.iterator();
		while (it.hasNext()) {
			Book b = it.next();
			if (b.getTitle().equalsIgnoreCase(title)) {
				it.remove();
				System.out.println(title + " book has removed successfully...");
				return;
			}
		}
		System.out.println("Book with title " + title + " not found.");
	}
}
