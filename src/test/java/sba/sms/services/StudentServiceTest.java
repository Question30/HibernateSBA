package sba.sms.services;

import com.sun.source.tree.AssertTree;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sba.sms.models.Student;
import sba.sms.utils.CommandLine;
import sba.sms.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


class StudentServiceTest {

    private StudentService service = new StudentService();

    private static Session session;

    @BeforeAll
    public static void init(){session =
            HibernateUtil.getSessionFactory().openSession();}

    @Test
    public void testCreateStudent(){
        session.beginTransaction();
        Student student = new Student("test@gmail.com", "test guy", "password");
        session.persist(student);
        session.getTransaction().commit();
        assertTrue("test@gmail.com", student.getEmail().equals("test@gmail" +
                ".com"));
    }

    @Test
    public void testGetStudentByEmail(){
        session.beginTransaction();
        Student student = session.get(Student.class,"test@gmail.com" );
        session.getTransaction().commit();
        assertTrue("Success", student.getEmail().equals("test@gmail.com"));

    }

    @Test
    public void testValidateStudent(){
        session.beginTransaction();
        Student student = session.get(Student.class, "test@gmail.com");
        session.getTransaction().commit();
        assertNotNull(student);
        assertTrue("Success",
                student.getPassword().equals("password") && student.getEmail().equals("test@gmail.com"));
    }

    @Test
    public void testRegisterStudentToCourse(){
        session.beginTransaction();
        Student student = session.get(Student.class, "test@gmail.com");
        session.getTransaction().commit();
        assertTrue("Success", !student.getCourses().contains("JAVA"));
    }

    @Test
    public void testGetAllStudents(){
        session.beginTransaction();
        Student student = new Student("Sam@gmail.com", "soop", "password");
        session.persist(student);


        List<Student> students = session.createQuery("FROM Student",
                Student.class).getResultList();
        session.getTransaction().commit();
        System.out.println(students);
        assertTrue("huh",!students.isEmpty());
    }



    @AfterAll
    public static void shutDown(){if(session != null) session.close();}
}