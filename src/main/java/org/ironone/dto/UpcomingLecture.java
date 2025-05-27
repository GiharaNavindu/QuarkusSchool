package org.ironone.dto;
import lombok.*;

@Data@Getter@Setter@NoArgsConstructor@AllArgsConstructor


public class UpcomingLecture {
    private int lectureId;
    private String moduleName;
    private String venue;
    private String time;


}