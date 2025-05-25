package org.ironone.Repository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.ironone.Entity.Lecture;

import java.util.List;

public class LectureRepository implements PanacheRepository<Lecture>{
    // Method to find a lecture by its ID
    public Lecture findById(String id) {
        return find("lectureId", id).firstResult();
    }

    // Method to retrieve all lectures
    public List<Lecture> findAllLectures() {
        return listAll();
    }

    // Method to save a new lecture
    public void save(Lecture lecture) {
        persist(lecture);
    }

    // Method to update an existing lecture
    public void update(Lecture lecture) {
        persist(lecture);
    }

    // Method to delete a lecture by its ID
    public void delete(String id) {
        delete("lectureId", id);
    }
}
