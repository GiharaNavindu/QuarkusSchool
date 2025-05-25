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
        if (course.getName() == null || course.getName().isEmpty()) {
            throw new IllegalArgumentException("empty Course name");
        }
        courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAllCourses();
    }

    public Course getCourseById(String id) {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new NotFoundException("Student with ID " + id + " not found");
        }
        return course;
    }

    @Transactional
    public void updateCoure(String id, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(id);
        if (existingCourse == null) {
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

        existingCourse.setName(updatedCourse.getName());
        existingCourse.setDuration(updatedCourse.getDuration());
        existingCourse.setModules(updatedCourse.getModules());
        existingCourse.setEnrollments(updatedCourse.getEnrollments());
        courseRepository.update(existingCourse);
    }

    @Transactional
    public void deleteLecturer(String id) {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new NotFoundException("Course with ID " + id + " not found");
        }
        courseRepository.delete(id);
    }

}
