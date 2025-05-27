package org.ironone.repository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.ironone.entity.Lecture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.ironone.dto.UpcomingLecture;


@ApplicationScoped
public class LectureRepository implements PanacheRepository<Lecture>{
    // Method to find a lecture by its ID
    public Lecture findById(String id) {
        return find("lectureId", id).firstResult();
    }

    // Method to retrieve all lectures
    public List<Lecture> findAllLectures() {
        return listAll();
    }

    // Method to save a new lecture
    public void save(Lecture lecture) {
        persist(lecture);
    }

    // Method to update an existing lecture
    public void update(Lecture lecture) {
        persist(lecture);
    }

    // Method to delete a lecture by its ID
    public void delete(String id) {
        delete("lectureId", id);
    }

    public List<UpcomingLecture> findUpcomingLecturesByStudentId(String studentId) {
        LocalDateTime now = LocalDateTime.now();
        List<Lecture> lectures = find("select l from Lecture l join l.students s where s.studentId = ?1 and l.time > ?2", studentId, now).list();
        return lectures.stream().map(lecture -> {
            UpcomingLecture upcoming = new UpcomingLecture();
            upcoming.setLectureId(lecture.getLectureId());
            upcoming.setModuleName(lecture.getModule().getName());
            upcoming.setVenue(lecture.getVenue());
            upcoming.setTime(lecture.getTime().toString());
            return upcoming;
        }).collect(Collectors.toList());
    }


}


