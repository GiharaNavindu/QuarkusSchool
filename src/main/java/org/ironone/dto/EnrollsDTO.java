package org.ironone.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class EnrollsDTO {
    private Long enrollmentId;
    private LocalDate enrollmentDate;

    @NotBlank(message = "Student ID is required")
    private String studentId;

    @NotBlank(message = "Course ID is required")
    private String courseId;



    // Getters and Setters
    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}