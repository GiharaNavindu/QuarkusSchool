package org.ironone.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Lecturer")
public class Lecturer {
    @Id
    @Column(name = "Lecturer_Id", length=20)
    private String lecturerId;

    @Column(name = "First_Name", nullable = false)
    private String firstName;

    @Column(name = "Last_Name", nullable = false)
    private String lastName;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Age")
    private Integer age;

    @Column(name = "DOB", nullable = false)
    private LocalDate dob;

    @Column(name = "Degree")
    private String degree;

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL)
    private List<Lecture> lectures;

    // Getters and Setters
    public Long getLecturerId() { return lecturerId; }
    public void setLecturerId(Long lecturerId) { this.lecturerId = lecturerId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }
    public List<Lecture> getLectures() { return lectures; }
    public void setLectures(List<Lecture> lectures) { this.lectures = lectures; }
}