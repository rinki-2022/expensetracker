package com.learnandgrow.expensetrackerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class ExpensetrackerapiApplication {

	public static void main(String[] args) {
		hello();
		SpringApplication.run(ExpensetrackerapiApplication.class, args);
		hello();
	}

	public static  void hello() {
		String url = "jdbc:mysql://localhost:3306/expensetracker"; // your db name
		String user = "root"; // your username
		String password = "admin123"; // your password

		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			if (connection != null) {
				System.out.println("Connection successful!");
				connection.close();
			}
		} catch (Exception e) {
			System.out.println("Connection failed!");
			e.printStackTrace();
		}
	}

}
