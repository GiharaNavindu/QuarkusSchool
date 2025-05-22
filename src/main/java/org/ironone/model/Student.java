package org.ironone.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Student_Id")
    private Long studentId;

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

    @Column(name = "Address")
    private String address;

    @Column(name = "Batch")
    private String batch;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Enrolls> enrollments;

    @ManyToMany
    @JoinTable(
            name = "Has",
            joinColumns = @JoinColumn(name = "Student_Id"),
            inverseJoinColumns = @JoinColumn(name = "Lecture_Id")
    )
    private List<Lecture> lectures;

    // Getters and Setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
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
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = batch; }
    public List<Enrolls> getEnrollments() { return enrollments; }
    public void setEnrollments(List<Enrolls> enrollments) { this.enrollments = enrollments; }
    public List<Lecture> getLectures() { return lectures; }
    public void setLectures(List<Lecture> lectures) { this.lectures = lectures; }
}