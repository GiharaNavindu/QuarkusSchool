package org.ironone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Column(name = "Module_Id", length = 20)
    @NotBlank(message = "Module ID is required")
    @Size(max = 20, message = "Module ID must be at most 20 characters")
    private String moduleId;

    @Column(name = "Name", nullable = false)
    @NotBlank(message = "Module name is required")
    @Size(max = 100, message = "Module name must be at most 100 characters")
    private String name;

    @Column(name = "Number_Of_Credits")
    @NotNull(message = "Number of credits is required")
    private Integer numberOfCredits;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Lecturer_Id")
    private Lecturer lecturer;

    @JsonIgnore
    @ManyToMany(mappedBy = "modules")
    private List<Course> courses;

    @JsonIgnore
    @OneToMany(mappedBy = "module")
    private List<Lecture> lectures;
}