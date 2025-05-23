package org.ironone.Entity;

import jakarta.persistence.*;
import java.util.List;

import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Course")
public class Course {
    @Id
    @Column(name = "Course_Id", length = 20)
    private String courseId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Duration")
    private Integer duration;

    @OneToMany(mappedBy = "course")
    private List<Enrolls> enrollments;

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<Enrolls> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrolls> enrollments) {
        this.enrollments = enrollments;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @ManyToMany
    @JoinTable(
            name = "Course_Module",
            joinColumns = @JoinColumn(name = "Course_Id"),
            inverseJoinColumns = @JoinColumn(name = "Module_Id")
    )
    private List<Module> modules;


}