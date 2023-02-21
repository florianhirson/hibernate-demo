package com.example.hibernatetutorial.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernatetutorial.entity.Instructor;
import com.example.hibernatetutorial.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate-one-to-one.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			// create objects
			int instructorDetailId = 2;

			// begin transaction
			session.beginTransaction();

			// retrieve data from db
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, instructorDetailId);

			System.out.println("Instructor Detail: " + instructorDetail);
			System.out.println("Instructor: " + instructorDetail.getInstructor());

			// remove the associated object reference
			// break bi-directional link
			instructorDetail.getInstructor().setInstructorDetail(null);

			// delete instructor detail from db
			session.remove(instructorDetail);

			session.getTransaction().commit();

			System.out.println("Instructor Detail removed!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close(); // prevent connection leak issue
			factory.close();
		}
	}

}
