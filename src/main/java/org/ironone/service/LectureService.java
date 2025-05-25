package org.ironone.service;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.ironone.Entity.Lecture;
import org.ironone.Entity.Student;
import org.ironone.Repository.LectureRepository;


import java.util.List;

public class LectureService {

    @Inject
    LectureRepository lectureRepository;

    @Transactional
    public void createLecture(Lecture lecture) {
        // Basic validation
        if (lecture.getLectureId() == 0 ) {
            throw new IllegalArgumentException("Venue cannot be null or empty");
        }

        lectureRepository.save(lecture);
    }

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAllLectures();
    }

    public Lecture getLectureById(String id) {
        Lecture lecture = lectureRepository.findById(id);
        if (lecture == null) {
            throw new NotFoundException("Lecture with ID " + id + " not found");
        }
        return lecture;
    }

    @Transactional
    public void updateLecture(String id, Lecture updatedLecture) {
        Lecture existingLecture = lectureRepository.findById(id);
        if (existingLecture == null) {
            throw new NotFoundException("Student with ID " + id + " not found");

        }
        existingLecture.setLecturer(updatedLecture.getLecturer());
        existingLecture.setModule(updatedLecture.getModule());
        existingLecture.setVenue(updatedLecture.getVenue());
        existingLecture.setTime(updatedLecture.getTime());
        existingLecture.setAttendanceCount(updatedLecture.getAttendanceCount());
        existingLecture.setStudents(updatedLecture.getStudents());
        lectureRepository.update(existingLecture);
    }

    @Transactional
    public void deleteLecture(String id) {
        Lecture lecture = lectureRepository.findById(id);
        if (lecture == null) {
            throw new NotFoundException("Student with ID " + id + " not found");
        }
        lectureRepository.delete(id);
    }
}
