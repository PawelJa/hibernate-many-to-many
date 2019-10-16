package com.springhibenrate.demo;

import com.springhibenrate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesForMaryDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get the student Mary from db
            Student student = session.get(Student.class, 2);
            System.out.println("Retrieved student: " + student);

            // create more courses
            Course course1 = new Course("Rubik's Cube - How to speed cube");
            Course course2 = new Course("Atari 2600 - Game development");

            // add Mary to courses
            course1.addStudent(student);
            course2.addStudent(student);

            // save the courses
            System.out.println("\nSaving the crouses...");
            session.save(course1);
            session.save(course2);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done !");

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            // handle connection leak issue
            session.close();
            sessionFactory.close();
        }
    }
}
