package org.jh.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate enrollmentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @ManyToOne
    private Student student;

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", enrollmentDate=" + enrollmentDate +
//                ", course=" + course.toString() +
//                ", student=" + student.toString() +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
