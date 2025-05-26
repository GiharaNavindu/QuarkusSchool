package org.ironone.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.ironone.Entity.Enrolls;
import org.ironone.Repository.EnrolllsRepository;

import java.util.List;


@ApplicationScoped
public class EnrollService {
    @Inject
    EnrolllsRepository enrolllsRepository;

    @Transactional
    public void createEnroll(Enrolls enroll) {
        // Basic validation
        if (enroll.getEnrollmentId() == null){
            throw new IllegalArgumentException("CourseId cannot be null or empty");
        }

        enrolllsRepository.save(enroll);
    }

    public List<Enrolls> getAllEnrolls() {
        return enrolllsRepository.findAllEnrollments();
    }

    public Enrolls getEnrollById(Long id) {
        Enrolls enrolls = enrolllsRepository.findById(id);
        if (enrolls == null) {
            throw new NotFoundException("Lecture with ID " + id + " not found");
        }
        return enrolls;
    }

    @Transactional
    public void updateEnroll(Long id, Enrolls updatedEnroll) {
        Enrolls existingEnroll = enrolllsRepository.findById(id);
        if (enrolllsRepository == null) {
            throw new NotFoundException("Student with ID " + id + " not found");

        }
        existingEnroll.setStudent(updatedEnroll.getStudent());
        existingEnroll.setCourse(updatedEnroll.getCourse());
//        existingEnroll.setLecture(updatedEnroll.getLecture());
        existingEnroll.setEnrollmentDate(updatedEnroll.getEnrollmentDate());
        enrolllsRepository.update(existingEnroll);
    }

    @Transactional
    public void deleteEnroll(Long id) {
        Enrolls enroll = enrolllsRepository.findById(id);
        if (enroll == null) {
            throw new NotFoundException("Student with ID " + id + " not found");
        }
        enrolllsRepository.delete(id);
    }
}
