package org.ironone.dto;

import org.ironone.entity.Lecture;
import org.ironone.entity.Student;

public class MarkAttendanceDTO {
    private Student student; // Only include studentId in JSON payload
    private Lecture lecture; // Only include lectureId in JSON payload
    private boolean attended;

    // Getters and Setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }
}