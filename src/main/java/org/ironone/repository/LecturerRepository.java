package org.ironone.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.ironone.entity.Lecturer;

import java.util.List;

@ApplicationScoped
public class LecturerRepository implements PanacheRepository<Lecturer> {
    public Lecturer findById(String id) {
        return find("lecturerId", id).firstResult();
    }

    public List<Lecturer> findAllLecturers(int offset, int limit, String sortBy, String sortDir, String filterName) {
        Sort sort = Sort.by(sortBy == null ? "firstName" : sortBy, sortDir != null && sortDir.equalsIgnoreCase("desc") ? Sort.Direction.Descending : Sort.Direction.Ascending);
        if (filterName != null && !filterName.isEmpty()) {
            return find("firstName like :name or lastName like :name", sort, Parameters.with("name", "%" + filterName + "%")).page(offset / limit, limit).list();
        }
        return findAll(sort).page(offset / limit, limit).list();
    }

    public long countLecturers(String filterName) {
        if (filterName != null && !filterName.isEmpty()) {
            return count("firstName like ?1 or lastName like ?1", "%" + filterName + "%");
        }
        return count();
    }

    public void save(Lecturer lecturer) {
        persist(lecturer);
    }

    public void update(Lecturer lecturer) {
        persist(lecturer);
    }

    public void delete(String id) {
        delete("lecturerId", id);
    }
}