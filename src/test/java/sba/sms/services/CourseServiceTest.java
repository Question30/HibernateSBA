package sba.sms.services;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;

import javax.swing.*;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class CourseServiceTest {

    private static Session session;

    @BeforeAll
    public static void init(){
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Test
    public void testCreateCourse(){
        session.beginTransaction();
        Course course = new Course("JAVA", "Steven Hulse");
        session.persist(course);
        session.getTransaction().commit();
        assertTrue("Testing create course", course.getName().equals("JAVA"));
    }


    @Test
    public void testGetCourseById(){
        session.beginTransaction();
        Course course = new Course("JAVA", "Steven Hulse");
        session.persist(course);
        Course course1 = session.get(Course.class, 1 );
        session.getTransaction().commit();
        assertTrue("Success", course1.getId() == 1);
    }

    @Test
    public void testGetAllCourses() {
        session.beginTransaction();
        Course course = new Course("JAVA", "Steven Hulse");
        session.persist(course);
        List<Course> courses = session.createQuery("FROM Course",
                Course.class).getResultList();
        session.getTransaction().commit();
        System.out.println(courses);
        assertTrue("huh", !courses.isEmpty());
    }


    @AfterAll
    public static void shutDown(){if(session != null) session.close();}
}
