package com.luv2code.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.demo.entity.Course;
import com.luv2code.demo.entity.Instructor;
import com.luv2code.demo.entity.InstructorDetail;

public class DeleteCoursesDemo {


	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			Instructor instructor = session.get(Instructor.class, 1);
			
			System.out.println("\nInstructor : " + instructor);
			System.out.println("\nCourses : " + instructor.getCourses());

			session.getTransaction().commit();
			
			
			session= factory.getCurrentSession();
			session.beginTransaction();
			Course course= session.get(Course.class, 5);
	
			System.out.println("\nDeleting course : " + course);			
			session.delete(course);
			session.getTransaction().commit();
			

			session= factory.getCurrentSession();
			session.beginTransaction();		
			instructor = session.get(Instructor.class, 1);
			
			System.out.println("\nCourses : " + instructor.getCourses());
			session.getTransaction().commit();
			
		}
		catch(Exception exp) {
			exp.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}
}
	

