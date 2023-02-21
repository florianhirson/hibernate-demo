package com.example.hibernatetutorial.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernatetutorial.entity.Instructor;
import com.example.hibernatetutorial.entity.InstructorDetail;

public class DeleteInstructorDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate-one-to-one.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			// create objects

			// begin transaction
			session.beginTransaction();

			int instructorId = 1;

			// retrieve instructor
			Instructor instructor = session.get(Instructor.class, instructorId);

			// save objects
			System.out.println("Deleting instructor: " + instructor);
			session.remove(instructor);

			session.getTransaction().commit();

			System.out.println("Done!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
