package org.ironone.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UpdateAttendanceDTO {
    @NotNull(message = "Attended status is required")
    private Boolean attended;

    @NotNull(message = "Marked at timestamp is required")
    private LocalDateTime markedAt;

    // Getters and Setters
    public Boolean isAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }

    public LocalDateTime getMarkedAt() {
        return markedAt;
    }

    public void setMarkedAt(LocalDateTime markedAt) {
        this.markedAt = markedAt;
    }
}