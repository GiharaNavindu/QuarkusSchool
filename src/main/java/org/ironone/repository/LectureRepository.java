package org.ironone.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.ironone.dto.UpcomingLectureDTO;
import org.ironone.entity.Lecture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class LectureRepository implements PanacheRepository<Lecture> {
    public Lecture findById(String id) {
        return find("lectureId", Integer.parseInt(id)).firstResult();
    }

    public List<Lecture> findAllLectures(int offset, int limit, String sortBy, String sortDir, String filterModuleId) {
        Sort sort = Sort.by(sortBy == null ? "time" : sortBy, sortDir != null && sortDir.equalsIgnoreCase("desc") ? Sort.Direction.Descending : Sort.Direction.Ascending);
        if (filterModuleId != null && !filterModuleId.isEmpty()) {
            return find("module.moduleId = :moduleId", sort, Parameters.with("moduleId", filterModuleId)).page(offset / limit, limit).list();
        }
        return findAll(sort).page(offset / limit, limit).list();
    }

    public long countLectures(String filterModuleId) {
        if (filterModuleId != null && !filterModuleId.isEmpty()) {
            return count("module.moduleId = ?1", filterModuleId);
        }
        return count();
    }

    public List<UpcomingLectureDTO> findUpcomingLecturesByStudentId(String studentId) {
        String query = """
            select distinct l from Lecture l
            join l.students s
            where s.studentId = :studentId
            and l.time >= :currentDate
            order by l.time
        """;
        return find(query, Parameters.with("studentId", studentId).and("currentDateTime", LocalDateTime.now()))
                .stream()
                .map(lecture -> {
                    UpcomingLectureDTO up = new UpcomingLectureDTO();
                    up.setLectureId(lecture.getLectureId());
                    up.setModuleName(lecture.getModule().getName());
                    up.setVenue(lecture.getVenue());
                    up.setTime(lecture.getTime());
                    return up;
                })
                .collect(Collectors.toList());
    }

    public void save(Lecture lecture) {
        persist(lecture);
    }

    public void update(Lecture lecture) {
        persist(lecture);
    }

    public void delete(String id) {
        delete("lectureId", Integer.parseInt(id));
    }
}