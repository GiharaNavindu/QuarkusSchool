package org.ironone.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Enrolls")
public class Enrolls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Enrollment_Id")
    private Long enrollmentId;

    @Column(name = "Enrollment_Date", nullable = false)
    private LocalDate enrollmentDate;

    @ManyToOne
    @JoinColumn(name = "Student_Id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "Course_Id", nullable = false)
    private Course course;

    // Getters and Setters
    public Long getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(Long enrollmentId) { this.enrollmentId = enrollmentId; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}
