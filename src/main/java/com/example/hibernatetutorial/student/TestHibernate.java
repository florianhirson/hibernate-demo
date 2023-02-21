package com.example.hibernatetutorial.student;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernatetutorial.entity.Student;
import com.example.hibernatetutorial.utils.DateUtils;

public class TestHibernate {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			Student student = new Student("Paul", "Wall", "test@test.fr", DateUtils.parseDate("31/12/1998"));

			session.beginTransaction();

			// replace save by persist because save is deprecated
			session.persist(student);

			session.getTransaction().commit();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
