package org.ironone.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Module")
public class Module {
    @Id
    @Column(name = "Module_Id", length = 50)
    private String moduleId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Number_of_Credits")
    private Integer numberOfCredits;

    @ManyToOne
    @JoinColumn(name = "Lecturer_Id")
    private Lecturer lecturer;

    @ManyToMany(mappedBy = "modules")
    private List<Course> courses;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(Integer numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    @OneToMany(mappedBy = "module")
    private List<Lecture> lectures;


}
