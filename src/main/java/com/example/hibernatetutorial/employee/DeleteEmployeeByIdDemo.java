package com.example.hibernatetutorial.employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernatetutorial.entity.Employee;

public class DeleteEmployeeByIdDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

//			Employee myEmployee = session.get(Employee.class, 4);
//
//			session.remove(myEmployee);
//
//			session.getTransaction().commit();

			int numberOfDeletedEntities = session.createMutationQuery( // use mutationQuery instead of query to
																		// update/delete data
					"delete Employee " +
							"WHERE id = :id ")
					.setParameter("id", 3)
					.executeUpdate();

			System.out.println("Number of deleted entities: " + numberOfDeletedEntities);

		} finally {
			session.close();
			factory.close();
		}
	}

}
