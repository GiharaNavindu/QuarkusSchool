package org.ironone.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.ironone.entity.Enrolls;

import java.util.List;

@ApplicationScoped
public class EnrolllsRepository implements PanacheRepository<Enrolls> {
    public Enrolls findById(Long id) {
        return find("enrollmentId", id).firstResult();
    }

    public List<Enrolls> findAllEnrollments(int offset, int limit, String sortBy, String sortDir, String filterCourseName) {
        Sort sort = Sort.by(sortBy == null ? "enrollmentDate" : sortBy, sortDir != null && sortDir.equalsIgnoreCase("desc") ? Sort.Direction.Descending : Sort.Direction.Ascending);
        if (filterCourseName != null && !filterCourseName.isEmpty()) {
            return find("course.name like :name", sort, Parameters.with("name", "%" + filterCourseName + "%")).page(offset / limit, limit).list();
        }
        return findAll(sort).page(offset / limit, limit).list();
    }

    public long countEnrollments(String filterCourseName) {
        if (filterCourseName != null && !filterCourseName.isEmpty()) {
            return count("course.name like ?1", "%" + filterCourseName + "%");
        }
        return count();
    }

    public List<Enrolls> findByStudentId(String studentId) {
        return find("student.studentId = ?1", studentId).list();
    }

    public void save(Enrolls enroll) {
        persist(enroll);
    }

    public void update(Enrolls enroll) {
        persist(enroll);
    }

    public void delete(Long id) {
        delete("enrollmentId", id);
    }
}