package org.ironone.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;
import org.ironone.dto.CourseDTO;
import org.ironone.entity.Course;
import org.ironone.repository.CourseRepository;

import jakarta.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CourseService {

    @Inject
    CourseRepository courseRepository;

    @Inject
    Validator validator;

    @Transactional
    public CourseDTO createCourse(CourseDTO courseDTO) {
        validate(courseDTO);
        Course course = toEntity(courseDTO);
        courseRepository.save(course);
        return toDTO(course);
    }

    public List<CourseDTO> getAllCourses(int offset, int limit, String sortBy, String sortDir, String filterName) {
        return courseRepository.findAllCourses(offset, limit, sortBy, sortDir, filterName)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public long getCourseCount(String filterName) {
        return courseRepository.countCourses(filterName);
    }

    public CourseDTO getCourseById(String id) {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new NotFoundException("Course with ID " + id + " not found");
        }
        return toDTO(course);
    }

    @Transactional
    public CourseDTO updateCourse(String id, CourseDTO updatedCourseDTO) {
        validate(updatedCourseDTO);
        Course existingCourse = courseRepository.findById(id);
        if (existingCourse == null) {
            throw new NotFoundException("Course with ID " + id + " not found");
        }
        existingCourse.setName(updatedCourseDTO.getName());
        existingCourse.setDuration(updatedCourseDTO.getDuration());
        courseRepository.update(existingCourse);
        return toDTO(existingCourse);
    }

    @Transactional
    public void deleteCourse(String id) {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new NotFoundException("Course with ID " + id + " not found");
        }
        courseRepository.delete(id);
    }

    private CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setCourseId(course.getCourseId());
        dto.setName(course.getName());
        dto.setDuration(course.getDuration());
        return dto;
    }

    private Course toEntity(CourseDTO dto) {
        Course course = new Course();
        course.setCourseId(dto.getCourseId());
        course.setName(dto.getName());
        course.setDuration(dto.getDuration());
        return course;
    }

    private void validate(CourseDTO courseDTO) {
        var violations = validator.validate(courseDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}