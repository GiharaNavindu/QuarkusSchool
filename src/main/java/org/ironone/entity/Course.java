//package org.ironone.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import java.util.List;
//
//import lombok.*;
//
//
//@Data
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "Course")
//public class Course {
//    @Id
//    @Column(name = "Course_Id", length = 20)
//    private String courseId;
//
//    @Column(name = "Name", nullable = false)
//    private String name;
//
//    @Column(name = "Duration")
//    private Integer duration;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "course")
//    private List<Enrolls> enrollments;
//
//
//
//    @ManyToMany
//    @JoinTable(
//            name = "Course_Module",
//            joinColumns = @JoinColumn(name = "Course_Id"),
//            inverseJoinColumns = @JoinColumn(name = "Module_Id")
//    )
//    @JsonIgnore
//    private List<Module> modules;
//
//
//}


package org.ironone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Course ID is required")
    @Size(max = 20, message = "Course ID must be at most 20 characters")
    private String courseId;

    @Column(name = "Name", nullable = false)
    @NotBlank(message = "Course name is required")
    @Size(max = 100, message = "Course name must be at most 100 characters")
    private String name;

    @Column(name = "Duration")
    private Integer duration;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Enrolls> enrollments;

    @ManyToMany
    @JoinTable(
            name = "Course_Module",
            joinColumns = @JoinColumn(name = "Course_Id"),
            inverseJoinColumns = @JoinColumn(name = "Module_Id")
    )
    @JsonIgnore
    private List<Module> modules;
}