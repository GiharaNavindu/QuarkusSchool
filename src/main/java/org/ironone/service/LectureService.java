package org.ironone.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;
import org.ironone.dto.LectureDTO;
import org.ironone.dto.UpcomingLectureDTO;
import org.ironone.entity.Lecture;
import org.ironone.entity.Lecturer;
import org.ironone.entity.Module;
import org.ironone.repository.LectureRepository;
import org.ironone.repository.LecturerRepository;
import org.ironone.repository.ModuleRepository;

import jakarta.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class LectureService {

    @Inject
    LectureRepository lectureRepository;

    @Inject
    LecturerRepository lecturerRepository;

    @Inject
    ModuleRepository moduleRepository;

    @Inject
    Validator validator;

    @Transactional
    public LectureDTO createLecture(LectureDTO lectureDTO) {
        validate(lectureDTO);
        Lecture lecture = toEntity(lectureDTO);
        lectureRepository.save(lecture);
        return toDTO(lecture);
    }

    public List<LectureDTO> getAllLectures(int offset, int limit, String sortBy, String sortDir, String filterModuleId) {
        return lectureRepository.findAllLectures(offset, limit, sortBy, sortDir, filterModuleId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public long getLectureCount(String filterModuleId) {
        return lectureRepository.countLectures(filterModuleId);
    }

    public LectureDTO getLectureById(String id) {
        Lecture lecture = lectureRepository.findById(id);
        if (lecture == null) {
            throw new NotFoundException("Lecture with ID " + id + " not found");
        }
        return toDTO(lecture);
    }

    @Transactional
    public LectureDTO updateLecture(String id, LectureDTO updatedDTO) {
        validate(updatedDTO);
        Lecture existingLecture = lectureRepository.findById(id);
        if (existingLecture == null) {
            throw new NotFoundException("Lecture with ID " + id + " not found");
        }
        existingLecture.setVenue(updatedDTO.getVenue());
        existingLecture.setTime(updatedDTO.getTime());
        existingLecture.setLecturer(getLecturer(updatedDTO.getLecturerId()));
        existingLecture.setModule(getModule(updatedDTO.getModuleId()));
        lectureRepository.update(existingLecture);
        return toDTO(existingLecture);
    }

    @Transactional
    public void deleteLecture(String id) {
        Lecture lecture = lectureRepository.findById(id);
        if (lecture == null) {
            throw new NotFoundException("Lecture with ID " + id + " not found");
        }
        lectureRepository.delete(id);
    }

    public List<UpcomingLectureDTO> getUpcomingLecturesByStudentId(String studentId) {
        return lectureRepository.findUpcomingLecturesByStudentId(studentId)
                .stream()
                .map(lecture -> {
                    UpcomingLectureDTO dto = new UpcomingLectureDTO();
                    dto.setLectureId(lecture.getLectureId());
                    dto.setModuleName(lecture.getModuleName());
                    dto.setVenue(lecture.getVenue());
                    dto.setTime(lecture.getTime());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private LectureDTO toDTO(Lecture lecture) {
        LectureDTO dto = new LectureDTO();
        dto.setLectureId(lecture.getLectureId());
        dto.setVenue(lecture.getVenue());
        dto.setTime(lecture.getTime());
        dto.setLecturerId(lecture.getLecturer().getLecturerId());
        dto.setModuleId(lecture.getModule().getModuleId());
        return dto;
    }

    private Lecture toEntity(LectureDTO dto) {
        Lecture lecture = new Lecture();
        lecture.setLectureId(dto.getLectureId());
        lecture.setVenue(dto.getVenue());
        lecture.setTime(dto.getTime());
        lecture.setLecturer(getLecturer(dto.getLecturerId()));
        lecture.setModule(getModule(dto.getModuleId()));
        return lecture;
    }

    private Lecturer getLecturer(String lecturerId) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId);
        if (lecturer == null) {
            throw new NotFoundException("Lecturer with ID " + lecturerId + " not found");
        }
        return lecturer;
    }

    private Module getModule(String moduleId) {
        Module module = moduleRepository.findById(moduleId);
        if (module == null) {
            throw new NotFoundException("Module with ID " + moduleId + " not found");
        }
        return module;
    }

    private void validate(LectureDTO lectureDTO) {
        var violations = validator.validate(lectureDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}