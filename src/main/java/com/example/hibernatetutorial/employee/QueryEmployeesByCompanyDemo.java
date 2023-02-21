package com.example.hibernatetutorial.employee;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.example.hibernatetutorial.entity.Employee;

public class QueryEmployeesByCompanyDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			String hql = "FROM Employee E WHERE E.company = 'SBE'";
			Query<Employee> query = session.createQuery(hql, Employee.class);
			List<Employee> results = query.getResultList();

			session.close();

			System.out.println("Query results:");
			if (results != null) {
				for (Employee employee : results) {
					System.out.println(employee);
				}
			}
		} finally {
			factory.close();
		}
	}

}
