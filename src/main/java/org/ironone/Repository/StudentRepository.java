package org.ironone.Repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.ironone.Entity.Student;

import java.util.List;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {

    public Student findById(String id) {
        return find("studentId", id).firstResult();
    }

    public List<Student> findAllStudents() {
        return listAll();
    }

    public void save(Student student) {
        persist(student);
    }

    public void update(Student student) {
        persist(student);
    }

    public void delete(String id) {
        delete("studentId", id);
    }
}