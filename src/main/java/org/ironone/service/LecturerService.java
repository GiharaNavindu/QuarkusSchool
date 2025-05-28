package org.ironone.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;
import org.ironone.dto.LecturerDTO;
import org.ironone.entity.Lecturer;
import org.ironone.repository.LecturerRepository;

import jakarta.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class LecturerService {

    @Inject
    LecturerRepository lecturerRepository;

    @Inject
    Validator validator;

    @Transactional
    public LecturerDTO createLecturer(LecturerDTO lecturerDTO) {
        validate(lecturerDTO);
        Lecturer lecturer = toEntity(lecturerDTO);
        lecturerRepository.save(lecturer);
        return toDTO(lecturer);
    }

    public List<LecturerDTO> getAllLecturers(int offset, int limit, String sortBy, String sortDir, String filterName) {
        return lecturerRepository.findAllLecturers(offset, limit, sortBy, sortDir, filterName)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public long getLecturerCount(String filterName) {
        return lecturerRepository.countLecturers(filterName);
    }

    public LecturerDTO getLecturerById(String id) {
        Lecturer lecturer = lecturerRepository.findById(id);
        if (lecturer == null) {
            throw new NotFoundException("Lecturer with ID " + id + " not found");
        }
        return toDTO(lecturer);
    }

    @Transactional
    public LecturerDTO updateLecturer(String id, LecturerDTO updatedLecturerDTO) {
        validate(updatedLecturerDTO);
        Lecturer existingLecturer = lecturerRepository.findById(id);
        if (existingLecturer == null) {
            throw new NotFoundException("Lecturer with ID " + id + " not found");
        }
        existingLecturer.setFirstName(updatedLecturerDTO.getFirstName());
        existingLecturer.setLastName(updatedLecturerDTO.getLastName());
        existingLecturer.setEmail(updatedLecturerDTO.getEmail());
        existingLecturer.setAge(updatedLecturerDTO.getAge());
        existingLecturer.setDob(updatedLecturerDTO.getDob());
        existingLecturer.setDegree(updatedLecturerDTO.getDegree());
        lecturerRepository.update(existingLecturer);
        return toDTO(existingLecturer);
    }

    @Transactional
    public void deleteLecturer(String id) {
        Lecturer lecturer = lecturerRepository.findById(id);
        if (lecturer == null) {
            throw new NotFoundException("Lecturer with ID " + id + " not found");
        }
        lecturerRepository.delete(id);
    }

    private LecturerDTO toDTO(Lecturer lecturer) {
        LecturerDTO dto = new LecturerDTO();
        dto.setLecturerId(lecturer.getLecturerId());
        dto.setFirstName(lecturer.getFirstName());
        dto.setLastName(lecturer.getLastName());
        dto.setEmail(lecturer.getEmail());
        dto.setAge(lecturer.getAge());
        dto.setDob(lecturer.getDob());
        dto.setDegree(lecturer.getDegree());
        return dto;
    }

    private Lecturer toEntity(LecturerDTO dto) {
        Lecturer lecturer = new Lecturer();
        lecturer.setLecturerId(dto.getLecturerId());
        lecturer.setFirstName(dto.getFirstName());
        lecturer.setLastName(dto.getLastName());
        lecturer.setEmail(dto.getEmail());
        lecturer.setAge(dto.getAge());
        lecturer.setDob(dto.getDob());
        lecturer.setDegree(dto.getDegree());
        return lecturer;
    }

    private void validate(LecturerDTO lecturerDTO) {
        var violations = validator.validate(lecturerDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}