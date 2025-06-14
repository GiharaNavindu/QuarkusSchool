//package org.ironone.repository;
//
//import io.quarkus.hibernate.orm.panache.PanacheRepository;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import org.ironone.dto.AttendanceData;
//import org.ironone.entity.Attendance;
//import org.ironone.entity.Lecture;
//import org.ironone.entity.Module;
//import org.ironone.repository.ModuleRepository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@ApplicationScoped
//public class AttendanceRepository implements PanacheRepository<Attendance> {
//
//    @Inject
//    ModuleRepository moduleRepository;
//
//    public List<AttendanceData> findAttendanceByStudentId(String studentId) {
//        List<Module> modules = moduleRepository.find("select distinct m from Module m join m.lectures l join l.students s where s.studentId = ?1", studentId).list();
//        return modules.stream().map(module -> {
//            List<Lecture> lectures = module.getLectures();
//            long attended = count("student.studentId = ?1 and lecture.module = ?2 and attended = true", studentId, module);
//            long total = lectures.size();
//            AttendanceData data = new AttendanceData();
//            data.setModuleId(module.getModuleId());
//            data.setModuleName(module.getName());
//            data.setAttendedLectures((int) attended);
//            data.setTotalLectures((int) total);
//            return data;
//        }).collect(Collectors.toList());
//    }
//
//    public List<Attendance> findByLectureId(int lectureId) {
//        return find("lecture.lectureId = ?1", lectureId).list();
//    }
//
//    public Attendance findById(Long attendanceId) {
//        return find("attendanceId", attendanceId).firstResult();
//    }
//
//    public void updateAttendance(Long attendanceId, boolean attended, LocalDateTime markedAt) {
//        update("set attended = ?1, markedAt = ?2 where attendanceId = ?3", attended, markedAt, attendanceId);
//    }
//
//    public boolean deleteAttendance(Long attendanceId) {
//        return delete("attendanceId = ?1", attendanceId) > 0;
//    }
//}

package org.ironone.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ironone.dto.AttendanceData;
import org.ironone.entity.Attendance;
import org.ironone.entity.Lecture;
import org.ironone.entity.Module;
import org.ironone.entity.Student;
import org.ironone.repository.ModuleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AttendanceRepository implements PanacheRepository<Attendance> {

    @Inject
    ModuleRepository moduleRepository;

//    public List<AttendanceData> findAttendanceByStudentId(String studentId) {
//        List<Module> modules = moduleRepository.find("select distinct m from Module m join m.lectures l join l.students s where s.studentId = ?1", studentId).list();
//        return modules.stream().map(module -> {
//            List<Lecture> lectures = module.getLectures();
//            long attended = count("student.studentId = ?1 and lecture.module = ?2 and attended = true", studentId, module);
//            long total = lectures.size();
//            AttendanceData data = new AttendanceData();
//            data.setModuleId(module.getModuleId());
//            data.setModuleName(module.getName());
//            data.setAttendedLectures((int) attended);
//            data.setTotalLectures((int) total);
//            return data;
//        }).collect(Collectors.toList());
//    }

    public List<AttendanceData> findAttendanceByStudentId(String studentId) {
        // Fetch distinct modules that the student with the given ID has attended.
        // It joins Module -> Lecture -> Students to find related modules.
        List<Module> modules = moduleRepository.find(
                "select distinct m from Module m join m.lectures l join l.students s where s.studentId = ?1",
                studentId
        ).list();
        //process each module to compute attendance data
        // Process each module to compute attendance data
        return modules.stream().map(module -> {
                    // Get all lectures associated with the current module
                    List<Lecture> lectures = module.getLectures();

                    // Count how many lectures the student has attended in this module
                    long attended = count(
                            "student.studentId = ?1 and lecture.module = ?2 and attended = true",
                            studentId,
                            module
                    );

                    // Get total number of lectures for the module
                    long total = lectures.size();

                    // Create a new AttendanceData object to hold attendance info
                    AttendanceData data = new AttendanceData();

                    // Set module ID in the attendance data
                    data.setModuleId(module.getModuleId());

                    // Set module name in the attendance data
                    data.setModuleName(module.getName());

                    // Set the number of attended lectures (cast to int)
                    data.setAttendedLectures((int) attended);

                    // Set the total number of lectures (cast to int)
                    data.setTotalLectures((int) total);

                    // Return the populated AttendanceData object for this module
                    return data;
                })
                // Collect all AttendanceData objects into a list and return
                .collect(Collectors.toList());
    }





    public List<Attendance> findByLectureId(int lectureId) {
        return find("lecture.lectureId = ?1", lectureId).list();
    }

    public Attendance findById(Long attendanceId) {
        return find("attendanceId", attendanceId).firstResult();
    }

    public void updateAttendance(Long attendanceId, boolean attended, LocalDateTime markedAt) {
        update("set attended = ?1, markedAt = ?2 where attendanceId = ?3", attended, markedAt, attendanceId);
    }

    public boolean deleteAttendance(Long attendanceId) {
        return delete("attendanceId = ?1", attendanceId) > 0;
    }

    // New method to fetch students enrolled in a lecture
    public List<Student> findEnrolledStudentsByLectureId(int lectureId) {
        return getEntityManager()
                .createQuery("""
                    select distinct s from Student s
                    join s.enrollments e
                    join e.course c
                    join c.modules m
                    join m.lectures l
                    where l.lectureId = :lectureId
                    """, Student.class)
                .setParameter("lectureId", lectureId)
                .getResultList();
    }

    // New method to fetch attendance records for a lecture with student details
    public List<Attendance> findAttendanceWithStudentsByLectureId(int lectureId) {
        return find("select a from Attendance a where a.lecture.lectureId = :lectureId",
                Parameters.with("lectureId", lectureId)).list();
    }
}