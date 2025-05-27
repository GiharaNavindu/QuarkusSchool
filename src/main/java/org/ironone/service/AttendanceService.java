package org.ironone.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.ironone.dto.AttendanceData;
import org.ironone.repository.AttendanceRepository;

import java.util.List;

@ApplicationScoped
public class AttendanceService {

    @Inject
    AttendanceRepository attendanceRepository;

    public List<AttendanceData> getAttendanceByStudentId(String studentId) {
        return attendanceRepository.findAttendanceByStudentId(studentId);
    }
}