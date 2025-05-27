package org.ironone.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.ironone.entity.Lecture;
import org.ironone.entity.Module;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AttendanceRepository implements PanacheRepository<Lecture> {

    public List<AttendanceData> findAttendanceByStudentId(String studentId) {
        List<Module> modules = Module.<Module>find("select distinct m from Module m join m.lectures l join l.students s where s.studentId = ?1", studentId).list();
        return modules.stream().map(module -> {
            List<Lecture> lectures = Lecture.find("module = ?1", module).list();
            long attended = lectures.stream().filter(lecture -> lecture.getStudents().stream().anyMatch(s -> s.getStudentId().equals(studentId))).count();
            AttendanceData data = new AttendanceData();
            data.setModuleId(module.getModuleId());
            data.setModuleName(module.getName());
            data.setAttendedLectures((int) attended);
            data.setTotalLectures(lectures.size());
            return data;
        }).collect(Collectors.toList());
    }
}

public class AttendanceData {
    private String moduleId;
    private String moduleName;
    private int attendedLectures;
    private int totalLectures;

    public String getModuleId() { return moduleId; }
    public void setModuleId(String moduleId) { this.moduleId = moduleId; }
    public String getModuleName() { return moduleName; }
    public void setModuleName(String moduleName) { this.moduleName = moduleName; }
    public int getAttendedLectures() { return attendedLectures; }
    public void setAttendedLectures(int attendedLectures) { this.attendedLectures = attendedLectures; }
    public int getTotalLectures() { return totalLectures; }
    public void setTotalLectures(int totalLectures) { this.totalLectures = totalLectures; }
}