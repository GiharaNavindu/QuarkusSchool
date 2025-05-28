package org.ironone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @Column(name = "Student_Id", length = 64)
    @NotBlank(message = "Student ID is required")
    @Size(max = 64, message = "Student ID must be at most 64 characters")
    private String studentId;

    @Column(name = "First_Name")
    @NotBlank(message = "First name is required")
    @Size(max = 255, message = "First name must be at most 255 characters")
    private String firstName;

    @Column(name = "Last_Name")
    @NotBlank(message = "Last name is required")
    @Size(max = 255, message = "Last name must be at most 255 characters")
    private String lastName;

    @Column(name = "Email")
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    @Size(max = 255, message = "Email must be at most 255 characters")
    private String email;

    @Column(name = "Age")
    private Integer age;

    @Column(name = "DOB")
    private LocalDate dob;

    @Column(name = "Address")
    @Size(max = 255, message = "Address must be at most 255 characters")
    private String address;

    @Column(name = "Batch")
    @Size(max = 50, message = "Batch must be at most 50 characters")
    private String batch;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Enrolls> enrollments;

    @ManyToMany
    @JoinTable(
            name = "Lecture_Student",
            joinColumns = @JoinColumn(name = "Student_Id"),
            inverseJoinColumns = @JoinColumn(name = "Lecture_Id")
    )
    @JsonIgnore
    private List<Lecture> lectures;

    //private String userId;

}