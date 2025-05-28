package org.ironone.entity;


import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Attendance_Id")
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "Student_Id", nullable = false)
    @NotNull(message = "Student is required")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "Lecture_Id", nullable = false)
    @NotNull(message = "Lecture is required")
    private Lecture lecture;

    @Column(name = "Attended", nullable = false)
    private boolean attended;

    @Column(name = "Marked_At")
    private LocalDateTime markedAt;
}
