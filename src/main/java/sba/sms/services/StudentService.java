package sba.sms.services;

import jakarta.persistence.TypedQuery;
import lombok.extern.java.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * StudentService is a concrete class. This class implements the
 * StudentI interface, overrides all abstract service methods and
 * provides implementation for each method. Lombok @Log used to
 * generate a logger file.
 */

public class StudentService implements StudentI{

    SessionFactory factory = null;
    Session session = null;
    Transaction transaction = null;

    @Override
    public List<Student> getAllStudents() {
        try{
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "FROM student";
            TypedQuery<Student> query = session.createNamedQuery(hql,
                    Student.class);
            List<Student> students = query.getResultList();

            transaction.commit();

            return students;

        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            factory.close();
            session.close();
        }

        return null;
    }

    @Override
    public void createStudent(Student student) {
        try{
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();

            session.persist(student);

            transaction.commit();

        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            factory.close();
            session.close();
        }
    }

    @Override
    public Student getStudentByEmail(String email) {

        try{
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();

           Student student = session.get(Student.class, email);

            transaction.commit();
            return student;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            factory.close();
            session.close();
        }

        return null;
    }

    @Override
    public boolean validateStudent(String email, String password) {
        return false;
    }

    @Override
    public void registerStudentToCourse(String email, int courseId) {

    }

    @Override
    public List<Course> getStudentCourses(String email) {
        return List.of();
    }
}
