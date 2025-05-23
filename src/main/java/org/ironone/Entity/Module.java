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



    @OneToMany(mappedBy = "module")
    private List<Lecture> lectures;


}
