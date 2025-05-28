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
@Table(name = "Lecturer")
public class Lecturer {
    @Id
    @Column(name = "Lecturer_Id", length = 20)
    @NotBlank(message = "Lecturer ID is required")
    @Size(max = 20, message = "Lecturer ID must be at most 20 characters")
    private String lecturerId;

    @Column(name = "First_Name", nullable = false)
    @NotBlank(message = "First name is required")
    @Size(max = 255, message = "First name must be at most 255 characters")
    private String firstName;

    @Column(name = "Last_Name", nullable = false)
    @NotBlank(message = "Last name is required")
    @Size(max = 255, message = "Last name must be at most 255 characters")
    private String lastName;

    @Column(name = "Email", nullable = false)
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    @Size(max = 255, message = "Email must be at most 255 characters")
    private String email;

    @Column(name = "Age")
    private Integer age;

    @Column(name = "DOB")
    private String dob;

    @Column(name = "Degree")
    @Size(max = 100, message = "Degree must be at most 100 characters")
    private String degree;

    @JsonIgnore
    @OneToMany(mappedBy = "lecturer")
    private List<Module> modules;

    @JsonIgnore
    @OneToMany(mappedBy = "lecturer")
    private List<Lecture> lectures;

    //private String userId;
}