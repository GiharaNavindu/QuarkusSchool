package org.ironone.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.ironone.dto.AttendanceData;
import org.ironone.dto.MarkAttendanceDTO;
import org.ironone.entity.Attendance;
import org.ironone.repository.AttendanceRepository;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class AttendanceService {

    @Inject
    AttendanceRepository attendanceRepository;

    public List<AttendanceData> getAttendanceByStudentId(String studentId) {
        return attendanceRepository.findAttendanceByStudentId(studentId);
    }

    @Transactional
    public void markAttendance(MarkAttendanceDTO dto) {
        Attendance attendance = new Attendance();
        attendance.setStudent(dto.getStudent());
        attendance.setLecture(dto.getLecture());
        attendance.setAttended(dto.isAttended());
        attendance.setMarkedAt(LocalDateTime.now());
        attendanceRepository.persist(attendance);
    }

    public List<Attendance> getAttendanceByLectureId(int lectureId) {
        return attendanceRepository.findByLectureId(lectureId);
    }
}