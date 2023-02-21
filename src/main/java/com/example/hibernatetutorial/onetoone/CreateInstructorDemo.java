package com.example.hibernatetutorial.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernatetutorial.entity.Instructor;
import com.example.hibernatetutorial.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate-one-to-one.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			// create objects
//			Instructor instructor = new Instructor("Chad", "Darby", "chad.darby@luv2code.com");
//
//			InstructorDetail instructorDetail = new InstructorDetail("https://luv2code.com/youtube", "Luv 2 code");

			Instructor instructor = new Instructor("Florian", "Hirson", "florian.hirson@luv2code.com");

			InstructorDetail instructorDetail = new InstructorDetail("https://luv2code.com/youtube", "Luv 2 code");

			// associate the objects
			instructor.setInstructorDetail(instructorDetail);

			// begin transaction
			session.beginTransaction();

			// save objects
			System.out.println("Saving instructor: " + instructor);
			session.persist(instructor);

			session.getTransaction().commit();

			System.out.println("Done!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
