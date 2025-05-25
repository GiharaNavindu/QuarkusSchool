package org.ironone.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.ironone.Entity.Student;
import org.ironone.Repository.StudentRepository;

import java.util.List;

@ApplicationScoped
public class StudentService {

    @Inject
    StudentRepository studentRepository;

    @Transactional
    public void createStudent(Student student) {
        // Basic validation
        if (student.getStudentId() == null || student.getStudentId().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        if (student.getEmail() == null || !student.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAllStudents();
    }

    public Student getStudentById(String id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new NotFoundException("Student with ID " + id + " not found");
        }
        return student;
    }

    @Transactional
    public void updateStudent(String id, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id);
        if (existingStudent == null) {
            throw new NotFoundException("Student with ID " + id + " not found");

        }
//        existingStudent.setFirstName(updatedStudent.getFirstName());
//        existingStudent.setLastName(updatedStudent.getLastName());
//        existingStudent.setEmail(updatedStudent.getEmail());
//        existingStudent.setAge(updatedStudent.getAge());
//        existingStudent.setDob(updatedStudent.getDob());
//        existingStudent.setAddress(updatedStudent.getAddress());
//        existingStudent.setBatch(updatedStudent.getBatch());
//        studentRepository.update(existingStudent);
    }

    @Transactional
    public void deleteStudent(String id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new NotFoundException("Student with ID " + id + " not found");
        }
        studentRepository.delete(id);
    }
}
