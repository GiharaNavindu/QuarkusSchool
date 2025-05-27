package org.ironone.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.ironone.entity.Module;

import jakarta.enterprise.context.ApplicationScoped;
import org.ironone.entity.Student;

import java.util.List;

@ApplicationScoped
public class ModuleRepository implements PanacheRepository {

    public Module findById(String id) {
        return (Module) find("moduleId", id).firstResult();
    }

    public Module findByLecturerId(String lecturerId) {
        return (Module) find("lecturer.lecturerId", lecturerId).firstResult();
    }

    public List<Module> findAllModules() {
        return listAll();
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
}
