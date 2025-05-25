package org.ironone.service;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.ironone.Entity.Course;
import org.ironone.Entity.Lecturer;
import org.ironone.Repository.CourseRepository;

import java.util.List;

public class CourseService {

    @Inject
    CourseRepository courseRepository;

    @Transactional
    public void createCourse(Course course) {
        // Basic validation
        if (course.getCourseId() == null || course.getCourseId().isEmpty()) {
            throw new IllegalArgumentException("Course ID cannot be null or empty");
        }
        if (course.getEmail() == null || !leturer.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        lecturerRepository.save(leturer);
    }

    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAllLecturers();
    }

    public Lecturer getLecturerById(String id) {
        Lecturer lecturer = lecturerRepository.findById(id);
        if (lecturer == null) {
            throw new NotFoundException("Student with ID " + id + " not found");
        }
        return lecturer;
    }

    @Transactional
    public void updateLecturer(String id, Lecturer updatedLecturer) {
        Lecturer existingLecturer = lecturerRepository.findById(id);
        if (existingLecturer == null) {
            throw new NotFoundException("Student with ID " + id + " not found");

        }
//        existingLecturer.setFirstName(updatedStudent.getFirstName());
//        existingLecturer.setLastName(updatedStudent.getLastName());
//        existingLecturer.setEmail(updatedStudent.getEmail());
//        existingLecturer.setAge(updatedStudent.getAge());
//        existingLecturer.setDob(updatedStudent.getDob());
//        existingLecturer.setAddress(updatedStudent.getAddress());
//        existingLecturer.setBatch(updatedStudent.getBatch());
//        existingLecturer.update(existingLecturer);
    }

    @Transactional
    public void deleteLecturer(String id) {
        Lecturer lecturer = lecturerRepository.findById(id);
        if (lecturer == null) {
            throw new NotFoundException("Student with ID " + id + " not found");
        }
        lecturerRepository.delete(id);
    }

}
