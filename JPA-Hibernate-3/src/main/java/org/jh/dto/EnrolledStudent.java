package org.jh.dto;

import org.jh.entities.Enrollment;
import org.jh.entities.Student;

public record EnrolledStudent(Student student, Enrollment enrollment) {
}
