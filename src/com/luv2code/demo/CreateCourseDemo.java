package com.luv2code.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.demo.entity.Course;
import com.luv2code.demo.entity.Instructor;
import com.luv2code.demo.entity.InstructorDetail;

public class CreateCourseDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			Course course1 = new Course("Russian A1");
			Course course2 = new Course("Russian A2");
			
			session.beginTransaction();
			Instructor theInstructor =  session.get(Instructor.class, 1);

			theInstructor.add(course1);
			theInstructor.add(course2);
			
			session.save(course1);
			session.save(course2);
			
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
