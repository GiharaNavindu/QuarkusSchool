package org.ironone.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.ironone.entity.Lecturer;

import java.util.List;


@ApplicationScoped
public class LecturerRepository  implements PanacheRepository<Lecturer> {

    // Method to find a lecturer by their ID
    public Lecturer findById(String id) {
        return find("lecturerId", id).firstResult();
    }

    // Method to retrieve all lecturers
    public List<Lecturer> findAllLecturers() {
        return listAll();
    }

    // Method to save a new lecturer
    public void save(Lecturer lecturer) {
        persist(lecturer);
    }

    // Method to update an existing lecturer
    public void update(Lecturer lecturer) {
        persist(lecturer);
    }

    // Method to delete a lecturer by their ID
    public void delete(String id) {
        delete("lecturerId", id);
    }

}
