package org.ironone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ModuleDTO {
    @NotBlank(message = "Module ID is required")
    @Size(max = 20, message = "Module ID must be at most 20 characters")
    private String moduleId;

    @NotBlank(message = "Module name is required")
    @Size(max = 100, message = "Module name must be at most 100 characters")
    private String name;

    @NotNull(message = "Number of credits is required")
    private Integer numberOfCredits;

    private String lecturerId;

    // Getters and Setters
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

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }
}