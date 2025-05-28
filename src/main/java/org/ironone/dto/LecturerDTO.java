package org.ironone.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;

public class LecturerDTO {
    @NotBlank(message = "Lecturer ID is required")
    @Size(max = 20, message = "Lecturer ID must be at most 20 characters")
    private String lecturerId;

    @NotBlank(message = "First name is required")
    @Size(max = 255, message = "First name must be at most 255 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 255, message = "Last name must be at most 255 characters")
    private String lastName;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    @Size(max = 255, message = "Email must be at most 255 characters")
    private String email;

    private Integer age;
    private LocalDate dob;
    private String degree;

    // Getters and Setters
    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}