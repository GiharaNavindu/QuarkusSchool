package org.ironone.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.ironone.entity.Module;

import java.util.List;

@ApplicationScoped
public class ModuleRepository implements PanacheRepository<Module> {
    public Module findById(String id) {
        return find("moduleId", id).firstResult();
    }

    public List<Module> findAllModules(int offset, int limit, String sortBy, String sortDir, String filterName) {
        Sort sort = Sort.by(sortBy == null ? "name" : sortBy, sortDir != null && sortDir.equalsIgnoreCase("desc") ? Sort.Direction.Descending : Sort.Direction.Ascending);
        if (filterName != null && !filterName.isEmpty()) {
            return find("name like :name", sort, Parameters.with("name", "%" + filterName + "%")).page(offset / limit, limit).list();
        }
        return findAll(sort).page(offset / limit, limit).list();
    }

    public long countModules(String filterName) {
        if (filterName != null && !filterName.isEmpty()) {
            return count("name like ?1", "%" + filterName + "%");
        }
        return count();
    }

    public List<Module> findByLecturerId(String lecturerId) {
        return find("lecturer.lecturerId = ?1", lecturerId).list();
    }

    public void save(Module module) {
        persist(module);
    }

    public void update(Module module) {
        persist(module);
    }

    public void delete(String id) {
        delete("moduleId", id);
    }

    public List<Module> findByCourseId(String id) {
        return find("select distinct m from Module m join m.courses c where c.courseId = ?1", id).list();
    }





}