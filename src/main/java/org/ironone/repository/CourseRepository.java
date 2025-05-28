package org.ironone.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.ironone.entity.Course;

import java.util.List;

@ApplicationScoped
public class CourseRepository implements PanacheRepository<Course> {
    public Course findById(String id) {
        return find("courseId", id).firstResult();
    }

    public List<Course> findAllCourses(int offset, int limit, String sortBy, String sortDir, String filterName) {
        Sort sort = Sort.by(sortBy == null ? "name" : sortBy, sortDir != null && sortDir.equalsIgnoreCase("desc") ? Sort.Direction.Descending : Sort.Direction.Ascending);
        if (filterName != null && !filterName.isEmpty()) {
            return find("name like :name", sort, Parameters.with("name", "%" + filterName + "%")).page(offset / limit, limit).list();
        }
        return findAll(sort).page(offset / limit, limit).list();
    }

    public long countCourses(String filterName) {
        if (filterName != null && !filterName.isEmpty()) {
            return count("name like ?1", "%" + filterName + "%");
        }
        return count();
    }

    public void save(Course course) {
        persist(course);
    }

    public void update(Course course) {
        persist(course);
    }

    public void delete(String id) {
        delete("courseId", id);
    }
}