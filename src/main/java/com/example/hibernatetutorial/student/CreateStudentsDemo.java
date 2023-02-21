package com.example.hibernatetutorial.student;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernatetutorial.entity.Student;
import com.example.hibernatetutorial.utils.DateUtils;

public class CreateStudentsDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			// create 3 student objects
			Student student1 = new Student("John", "Doe", "test@test.fr", DateUtils.parseDate("31/12/1998"));
			Student student2 = new Student("Mary", "Public", "test@test.fr", DateUtils.parseDate("31/12/1998"));
			Student student3 = new Student("Bonita", "Applebaum", "test@test.fr", DateUtils.parseDate("31/12/1998"));

			session.beginTransaction();

			// replace save by persist because save is deprecated
			session.persist(student1);
			session.persist(student2);
			session.persist(student3);

			session.getTransaction().commit();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
