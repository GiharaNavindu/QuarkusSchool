package org.ironone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
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
    @Size(max = 100, message = "Venue must be at most 100 characters")
    private String venue;

    @Column(name = "Time", nullable = false)
    @NotNull(message = "Time is required")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "Lecturer_Id")
    @NotNull(message = "Lecturer is required")
    private Lecturer lecturer;





    @ManyToOne
    @JoinColumn(name = "Module_Id")
    @NotNull(message = "Module is required")
    private Module module;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "Lecture_Student",
            joinColumns = @JoinColumn(name = "Lecture_Id"),
            inverseJoinColumns = @JoinColumn(name = "Student_Id")
    )
    private List<Student> students;
}