package org.ironone.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @Column(name = "Course_Id", length=20)
    private String courseId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Duration")
    private Integer duration;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Module> modules;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Enrolls> enrollments;

    // Getters and Setters
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }
    public List<Module> getModules() { return modules; }
    public void setModules(List<Module> modules) { this.modules = modules; }
    public List<Enrolls> getEnrollments() { return enrollments; }
    public void setEnrollments(List<Enrolls> enrollments) { this.enrollments = enrollments; }
}