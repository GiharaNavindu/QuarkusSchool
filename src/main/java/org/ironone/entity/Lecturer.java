package org.ironone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.*;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Lecturer")
public class Lecturer {
    @Id
    @Column(name = "Lecturer_Id", length = 20)
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

//    @Column(name = "User_Id", nullable = false, unique=true)
//    private String userId;

    @OneToMany(mappedBy = "lecturer")
    @JsonIgnore
    private List<Lecture> lectures;

    @OneToMany(mappedBy = "lecturer")
    @JsonIgnore
    private List<Module> modules;


}