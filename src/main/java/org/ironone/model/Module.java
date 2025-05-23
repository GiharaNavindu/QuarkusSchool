package org.ironone.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Module")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Module_Id")
    private Long moduleId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Number_Of_Credits")
    private Integer numberOfCredits;

    @ManyToOne
    @JoinColumn(name = "Course_Id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private List<Lecture> lectures;

    // Getters and Setters
    public Long getModuleId() { return moduleId; }
    public void setModuleId(Long moduleId) { this.moduleId = moduleId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getNumberOfCredits() { return numberOfCredits; }
    public void setNumberOfCredits(Integer numberOfCredits) { this.numberOfCredits = numberOfCredits; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
    public List<Lecture> getLectures() { return lectures; }
    public void setLectures(List<Lecture> lectures) { this.lectures = lectures; }
}
