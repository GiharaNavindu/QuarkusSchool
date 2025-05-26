package org.ironone.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "Student_Id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "Course_Id")
    private Course course;
}


