package org.ironone.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ironone.dto.AttendanceData;
import org.ironone.entity.Attendance;
import org.ironone.entity.Lecture;
import org.ironone.entity.Module;
import org.ironone.repository.ModuleRepository;


import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AttendanceRepository implements PanacheRepository<Attendance> {

    @Inject
    ModuleRepository moduleRepository;

    public List<AttendanceData> findAttendanceByStudentId(String studentId) {
        List<Module> modules = moduleRepository.find("select distinct m from Module m join m.lectures l join l.students s where s.studentId = ?1", studentId).list();
        return modules.stream().map(module -> {
            List<Lecture> lectures = module.getLectures();
            long attended = count("student.studentId = ?1 and lecture.module = ?2 and attended = true", studentId, module);
            long total = lectures.size();
            AttendanceData data = new AttendanceData();
            data.setModuleId(module.getModuleId());
            data.setModuleName(module.getName());
            data.setAttendedLectures((int) attended);
            data.setTotalLectures((int) total);
            return data;
        }).collect(Collectors.toList());
    }

    public List<Attendance> findByLectureId(int lectureId) {
        return find("lecture.lectureId = ?1", lectureId).list();
    }
}