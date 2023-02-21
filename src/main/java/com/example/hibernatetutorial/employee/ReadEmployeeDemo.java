package com.example.hibernatetutorial.employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernatetutorial.entity.Employee;

public class ReadEmployeeDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			// retrieve an employee with the employee id primary key
			Employee retrievedEmployee = session.get(Employee.class, 1);

			System.out.println("Retrieved employee: " + retrievedEmployee);

			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
