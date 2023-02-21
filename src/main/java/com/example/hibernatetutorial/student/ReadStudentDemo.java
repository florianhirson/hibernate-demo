package com.example.hibernatetutorial.student;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernatetutorial.entity.Student;
import com.example.hibernatetutorial.utils.DateUtils;

public class ReadStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			Student student1 = new Student("John", "Doe", "test@test.fr", DateUtils.parseDate("31/12/1998"));

			session.beginTransaction();
			session.persist(student1);
			session.getTransaction().commit();

			// needs a new session and new transaction after commit()
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve a student with the student id primary key
			Student retrievedStudent = session.get(Student.class, student1.getId());

			System.out.println("Retrieved student: " + retrievedStudent);

			session.getTransaction().commit();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
