package org.ironone.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.List;

import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Lecture_Id")
    private int lectureId;

    @Column(name = "Venue")
    private String venue;

    @Column(name = "Attendance_Count")
    private Integer attendanceCount;

    @Column(name = "Time", nullable = false)
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "Lecturer_Id")
    private Lecturer lecturer;

    @ManyToOne
    @JoinColumn(name = "Module_Id")
    private Module module;





    @ManyToMany(mappedBy = "lectures")
    @JsonIgnore
    private List<Student> students;


}