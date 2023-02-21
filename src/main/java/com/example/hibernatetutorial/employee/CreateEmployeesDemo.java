package com.example.hibernatetutorial.employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernatetutorial.entity.Employee;

public class CreateEmployeesDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			// create 3 employee objects
			Employee employee1 = new Employee("John", "Doe", "SBE");
			Employee employee2 = new Employee("Mary", "Public", "OPEN");
			Employee employee3 = new Employee("Bonita", "Applebaum", "Google");

			session.beginTransaction();

			// replace save by persist because save is deprecated
			session.persist(employee1);
			session.persist(employee2);
			session.persist(employee3);

			session.getTransaction().commit();

		} finally {
			factory.close();
		}
	}

}
