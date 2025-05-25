package org.ironone.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.ironone.Entity.Lecture;
import org.ironone.Entity.Lecturer;
import org.ironone.Entity.Student;
import org.ironone.Repository.LecturerRepository;
import org.ironone.Repository.StudentRepository;

import java.util.List;

@ApplicationScoped
public class LecturerService {

    @Inject
    LecturerRepository lecturerRepository;

    @Transactional
    public void createLecturer(Lecturer leturer) {
        // Basic validation
        if (leturer.getLecturerId() == null || leturer.getLecturerId().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        if (leturer.getEmail() == null || !leturer.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        LecturerRepository.save(leturer);
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
