package org.ironone.Entity;

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getAttendanceCount() {
        return attendanceCount;
    }

    public void setAttendanceCount(Integer attendanceCount) {
        this.attendanceCount = attendanceCount;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    @ManyToMany(mappedBy = "lectures")
    private List<Student> students;


}