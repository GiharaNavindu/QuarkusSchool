package org.ironone.dto;

import lombok.*;

@Data
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class AttendanceData {
    private String moduleId;
    private String moduleName;
    private int attendedLectures;
    private int totalLectures;


}