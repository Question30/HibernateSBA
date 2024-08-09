package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Course is a POJO, configured as a persistent class that represents (or maps to) a table
 * name 'course' in the database. A Course object contains fields that represent course
 * information and a mapping of 'courses' that indicate an inverse or referencing side
 * of the relationship. Implement Lombok annotations to eliminate boilerplate code.
 */

@Entity
@Table(name = "course ")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courses_id")
    private int courses_id;

    @Column(name = "course_name", nullable = false, length = 50)
    private String courseName;

    @Column(name = "instructor_name", nullable = false, length = 50)
    private String instructor_name;

    @Column(name = "students")
    private Set<Student> students;

    @ManyToMany(mappedBy = "course")
    public Set<Student> getStudents() {
        return students;
    }
}
