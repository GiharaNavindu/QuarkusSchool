package org.ironone.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;
import org.ironone.dto.ProgressData;
import org.ironone.dto.StudentDTO;
import org.ironone.entity.Enrolls;
import org.ironone.entity.Student;
import org.ironone.repository.EnrolllsRepository;
import org.ironone.repository.StudentRepository;

import jakarta.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class StudentService {

    @Inject
    StudentRepository studentRepository;

    @Inject
    EnrolllsRepository enrollsRepository;

    @Inject
    Validator validator;

    @Transactional
    public StudentDTO createStudent(StudentDTO studentDTO) {
        validate(studentDTO);
        Student student = toEntity(studentDTO);
        studentRepository.save(student);
        return toDTO(student);
    }

    public List<StudentDTO> getAllStudents(int offset, int limit, String sortBy, String sortDir, String filterName) {
        return studentRepository.findAllStudents(offset, limit, sortBy, sortDir, filterName)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public long getStudentCount(String filterName) {
        return studentRepository.countStudents(filterName);
    }

    public StudentDTO getStudentById(String id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new NotFoundException("Student with ID " + id + " not found");
        }
        return toDTO(student);
    }

    @Transactional
    public StudentDTO updateStudent(String id, StudentDTO updatedStudentDTO) {
        validate(updatedStudentDTO);
        Student existingStudent = studentRepository.findById(id);
        if (existingStudent == null) {
            throw new NotFoundException("Student with ID " + id + " not found");
        }
        existingStudent.setFirstName(updatedStudentDTO.getFirstName());
        existingStudent.setLastName(updatedStudentDTO.getLastName());
        existingStudent.setEmail(updatedStudentDTO.getEmail());
        existingStudent.setAge(updatedStudentDTO.getAge());
        existingStudent.setDob(updatedStudentDTO.getDob());
        existingStudent.setAddress(updatedStudentDTO.getAddress());
        existingStudent.setBatch(updatedStudentDTO.getBatch());
        studentRepository.update(existingStudent);
        return toDTO(existingStudent);
    }

    @Transactional
    public void deleteStudent(String id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new NotFoundException("Student with ID " + id + " not found");
        }
        studentRepository.delete(id);
    }

    public ProgressData getStudentProgress(String studentId) {
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            throw new NotFoundException("Student with ID " + studentId + " not found");
        }
        List<Enrolls> enrollments = enrollsRepository.find("student.studentId = ?1", studentId).list();
        int completedCredits = enrollments.stream()
                .flatMap(enroll -> enroll.getCourse().getModules().stream())
                .mapToInt(module -> module.getNumberOfCredits() != null ? module.getNumberOfCredits() : 0)
                .sum();
        ProgressData progress = new ProgressData();
        progress.setCompletedCredits(completedCredits);
        progress.setTotalCredits(120); // Configurable per course in future
        return progress;
    }

    private StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setStudentId(student.getStudentId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setAge(student.getAge());
        dto.setDob(student.getDob());
        dto.setAddress(student.getAddress());
        dto.setBatch(student.getBatch());
        return dto;
    }

    private Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setStudentId(dto.getStudentId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        student.setDob(dto.getDob());
        student.setAddress(dto.getAddress());
        student.setBatch(dto.getBatch());
        return student;
    }

    private void validate(StudentDTO studentDTO) {
        var violations = validator.validate(studentDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}