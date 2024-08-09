package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Student is a POJO, configured as a persistent class that represents (or maps to) a table
 * name 'student' in the database. A Student object contains fields that represent student
 * login credentials and a join table containing a registered student's email and course(s)
 * data. The Student class can be viewed as the owner of the bi-directional relationship.
 * Implement Lombok annotations to eliminate boilerplate code.
 */

@Entity
@Table(name = "student")
@Data
public class Student {
        @Id
        @Column(name = "email", length = 50)
        private  String email;

        @Column(name = "name",  nullable = false ,length = 50)
        private String name;

        @Column(name = "password", nullable = false, length = 50)
        private String password;

        @ManyToMany(targetEntity = Course.class, fetch = FetchType.EAGER,
                cascade = {CascadeType.DETACH, CascadeType.REMOVE,
                        CascadeType.MERGE, CascadeType.PERSIST})
        @JoinTable(name = "student_course",
                joinColumns = {@JoinColumn(name = "email")},
                inverseJoinColumns = {@JoinColumn(name = "courses_id")}
        )
        private Set<Course> courses;


    }



