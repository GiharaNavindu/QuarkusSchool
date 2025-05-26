package org.ironone.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.ironone.entity.Course;

import java.util.List;


@ApplicationScoped
public class CourseRepository implements PanacheRepository<Course> {
    public Course findById(String id) {
        return find("courseId", id).firstResult();
    }

    // Method to retrieve all lectures
    public List<Course> findAllCourses() {
        return listAll();
    }

    // Method to save a new lecture
    public void save(Course course) {
        persist(course);
    }

    // Method to update an existing lecture
    public void update(Course course) {
        persist(course);
    }

    // Method to delete a lecture by its ID
    public void delete(String id) {
        delete("courseId", id);
    }
}
