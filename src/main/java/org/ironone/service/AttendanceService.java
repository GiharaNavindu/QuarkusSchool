//package org.ironone.service;
//
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
//import org.ironone.dto.AttendanceData;
//import org.ironone.dto.MarkAttendanceDTO;
//import org.ironone.entity.Attendance;
//import org.ironone.repository.AttendanceRepository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@ApplicationScoped
//public class AttendanceService {
//
//    @Inject
//    AttendanceRepository attendanceRepository;
//
//    public List<AttendanceData> getAttendanceByStudentId(String studentId) {
//        return attendanceRepository.findAttendanceByStudentId(studentId);
//    }
//
//    @Transactional
//    public void markAttendance(MarkAttendanceDTO dto) {
//        Attendance attendance = new Attendance();
//        attendance.setStudent(dto.getStudent());
//        attendance.setLecture(dto.getLecture());
//        attendance.setAttended(dto.isAttended());
//        attendance.setMarkedAt(LocalDateTime.now());
//        attendanceRepository.persist(attendance);
//    }
//
//    public List<Attendance> getAttendanceByLectureId(int lectureId) {
//        return attendanceRepository.findByLectureId(lectureId);
//    }
//
//    @Transactional
//    public void updateAttendance(Long attendanceId, boolean attended, LocalDateTime markedAt) {
//        Attendance attendance = attendanceRepository.findById(attendanceId);
//        if (attendance == null) {
//            throw new IllegalArgumentException("Attendance record not found");
//        }
//        attendanceRepository.updateAttendance(attendanceId, attended, markedAt);
//    }
//
//    @Transactional
//    public void deleteAttendance(Long attendanceId) {
//        if (!attendanceRepository.deleteAttendance(attendanceId)) {
//            throw new IllegalArgumentException("Attendance record not found");
//        }
//    }
//}


package org.ironone.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.ironone.dto.AttendanceData;
import org.ironone.dto.MarkAttendanceDTO;
import org.ironone.entity.Attendance;
import org.ironone.entity.Lecture;
import org.ironone.entity.Student;
import org.ironone.repository.AttendanceRepository;
import org.ironone.repository.LectureRepository;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class AttendanceService {

    @Inject
    AttendanceRepository attendanceRepository;

    @Inject
    LectureRepository lectureRepository;

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

    @Transactional
    public void updateAttendance(Long attendanceId, boolean attended, LocalDateTime markedAt) {
        Attendance attendance = attendanceRepository.findById(attendanceId);
        if (attendance == null) {
            throw new IllegalArgumentException("Attendance record not found");
        }
        attendanceRepository.updateAttendance(attendanceId, attended, markedAt);
    }

    @Transactional
    public void deleteAttendance(Long attendanceId) {
        if (!attendanceRepository.deleteAttendance(attendanceId)) {
            throw new IllegalArgumentException("Attendance record not found");
        }
    }

    // New method to get enrolled students for a lecture
    public List<Student> getEnrolledStudentsForLecture(int lectureId) {
        Lecture lecture = lectureRepository.findById(String.valueOf(lectureId));
        if (lecture == null) {
            throw new IllegalArgumentException("Lecture with ID " + lectureId + " not found");
        }
        return attendanceRepository.findEnrolledStudentsByLectureId(lectureId);
    }

    // New method to mark attendance for multiple students
    @Transactional
    public void markMultipleAttendances(int lectureId, List<MarkAttendanceDTO> dtos) {
        Lecture lecture = lectureRepository.findById(String.valueOf(lectureId));
        if (lecture == null) {
            throw new IllegalArgumentException("Lecture with ID " + lectureId + " not found");
        }
        for (MarkAttendanceDTO dto : dtos) {
            // Check if attendance record already exists
            Attendance existing = findAttendanceByStudentAndLecture(dto.getStudent().getStudentId(), lectureId);
            if (existing != null) {
                // Update existing record
                attendanceRepository.updateAttendance(existing.getAttendanceId(), dto.isAttended(), LocalDateTime.now());
            } else {
                // Create new record
                Attendance attendance = new Attendance();
                attendance.setStudent(dto.getStudent());
                attendance.setLecture(lecture);
                attendance.setAttended(dto.isAttended());
                attendance.setMarkedAt(LocalDateTime.now());
                attendanceRepository.persist(attendance);
            }
        }
    }

    // Helper method to find existing attendance record
    private Attendance findAttendanceByStudentAndLecture(String studentId, int lectureId) {
        return attendanceRepository.find("student.studentId = ?1 and lecture.lectureId = ?2", studentId, lectureId)
                .firstResult();
    }

    // New method to get attendance records with student details
    public List<Attendance> getAttendanceWithStudentsByLectureId(int lectureId) {
        Lecture lecture = lectureRepository.findById(String.valueOf(lectureId));
        if (lecture == null) {
            throw new IllegalArgumentException("Lecture with ID " + lectureId + " not found");
        }
        return attendanceRepository.findAttendanceWithStudentsByLectureId(lectureId);
    }
}