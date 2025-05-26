package org.ironone.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import org.ironone.Entity.Enrolls;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;


@ApplicationScoped
public class EnrolllsRepository implements PanacheRepository<Enrolls> {

    // Method to find an enrollment by its ID
    public Enrolls findById(Long id) {
        return find("enrollmentId", id).firstResult();
    }

    // Method to retrieve all enrollments
    public List<Enrolls> findAllEnrollments() {
        return listAll();
    }

    // Method to save a new enrollment
    public void save(Enrolls enrolls) {
        persist(enrolls);
    }

    // Method to update an existing enrollment
    public void update(Enrolls enrolls) {
        persist(enrolls);
    }

    // Method to delete an enrollment by its ID
    public void delete(Long id) {
        delete("enrollmentId", id);
    }
}
