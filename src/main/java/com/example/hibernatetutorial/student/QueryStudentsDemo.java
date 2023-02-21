package com.example.hibernatetutorial.student;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.example.hibernatetutorial.entity.Student;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class QueryStudentsDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Student> cr = cb.createQuery(Student.class);
			Root<Student> root = cr.from(Student.class);
			cr.select(root);

			Query<Student> query = session.createQuery(cr);
			List<Student> results = query.getResultList();

			session.close();

			System.out.println("Query results:");
			if (results != null) {
				for (Student student : results) {
					System.out.println(student);
				}
			}
		} finally {
			factory.close();
		}
	}

}
