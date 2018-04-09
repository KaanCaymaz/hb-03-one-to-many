package com.luv2code.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.demo.entity.Course;
import com.luv2code.demo.entity.Instructor;
import com.luv2code.demo.entity.InstructorDetail;

public class CreateInstructorDemo {
	
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			Instructor theInstructor = new Instructor("Kaan","Caymaz","kaancaymaz@hotmail.com");
			InstructorDetail theInstructorDetail = new InstructorDetail("kaancaymaz/youtube","russian");
			
			theInstructor.setInstructorDetail(theInstructorDetail);
			
			session.beginTransaction();
			session.save(theInstructor);
			session.getTransaction().commit();			
			
		}
		catch(Exception exp){
			exp.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
