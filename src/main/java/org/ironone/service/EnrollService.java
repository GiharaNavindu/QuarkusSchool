package org.ironone.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;
import org.ironone.dto.EnrollsDTO;
import org.ironone.entity.Course;
import org.ironone.entity.Enrolls;
import org.ironone.entity.Student;
import org.ironone.repository.CourseRepository;
import org.ironone.repository.EnrolllsRepository;
import org.ironone.repository.StudentRepository;

import jakarta.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EnrollService {

    @Inject
    EnrolllsRepository enrolllsRepository;

    @Inject
    StudentRepository studentRepository;

    @Inject
    CourseRepository courseRepository;

    @Inject
    Validator validator;

    @Transactional
    public EnrollsDTO createEnroll(EnrollsDTO enrollDTO) {
        validate(enrollDTO);
        Enrolls enroll = toEntity(enrollDTO);
        enrolllsRepository.save(enroll);
        return toDTO(enroll);
    }

    public List<EnrollsDTO> getAllEnrolls(int offset, int limit, String sortBy, String sortDir, String filterCourseName) {
        return enrolllsRepository.findAllEnrollments(offset, limit, sortBy, sortDir, filterCourseName)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public long getEnrollCount(String filterCourseName) {
        return enrolllsRepository.countEnrollments(filterCourseName);
    }

    public EnrollsDTO getEnrollById(Long id) {
        Enrolls enroll = enrolllsRepository.findById(id);
        if (enroll == null) {
            throw new NotFoundException("Enrollment with ID " + id + " not found");
        }
        return toDTO(enroll);
    }

    @Transactional
    public EnrollsDTO updateEnroll(Long id, EnrollsDTO updatedEnrollDTO) {
        validate(updatedEnrollDTO);
        Enrolls existingEnroll = enrolllsRepository.findById(id);
        if (existingEnroll == null) {
            throw new NotFoundException("Enrollment with ID " + id + " not found");
        }
        existingEnroll.setStudent(getStudent(updatedEnrollDTO.getStudentId()));
        existingEnroll.setCourse(getCourse(updatedEnrollDTO.getCourseId()));
        existingEnroll.setEnrollmentDate(updatedEnrollDTO.getEnrollmentDate());
        enrolllsRepository.update(existingEnroll);
        return toDTO(existingEnroll);
    }

    @Transactional
    public void deleteEnroll(Long id) {
        Enrolls enroll = enrolllsRepository.findById(id);
        if (enroll == null) {
            throw new NotFoundException("Enrollment with ID " + id + " not found");
        }
        enrolllsRepository.delete(id);
    }

    public List<EnrollsDTO> getEnrollmentsByStudentId(String studentId) {
        return enrolllsRepository.findByStudentId(studentId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private EnrollsDTO toDTO(Enrolls enroll) {
        EnrollsDTO dto = new EnrollsDTO();
        dto.setEnrollmentId(enroll.getEnrollmentId());
        dto.setEnrollmentDate(enroll.getEnrollmentDate());
        dto.setStudentId(enroll.getStudent().getStudentId());
        dto.setCourseId(enroll.getCourse().getCourseId());
        return dto;
    }

    private Enrolls toEntity(EnrollsDTO dto) {
        Enrolls enroll = new Enrolls();
        enroll.setEnrollmentId(dto.getEnrollmentId());
        enroll.setEnrollmentDate(dto.getEnrollmentDate());
        enroll.setStudent(getStudent(dto.getStudentId()));
        enroll.setCourse(getCourse(dto.getCourseId()));
        return enroll;
    }

    private Student getStudent(String studentId) {
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            throw new NotFoundException("Student with ID " + studentId + " not found");
        }
        return student;
    }

    private Course getCourse(String courseId) {
        Course course = courseRepository.findById(courseId);
        if (course == null) {
            throw new NotFoundException("Course with ID " + courseId + " not found");
        }
        return course;
    }

    private void validate(EnrollsDTO enrollDTO) {
        var violations = validator.validate(enrollDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}