package org.ironone.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;
import org.ironone.dto.ModuleDTO;
import org.ironone.entity.Course;
import org.ironone.entity.Lecturer;
import org.ironone.entity.Module;
import org.ironone.repository.CourseRepository;
import org.ironone.repository.LecturerRepository;
import org.ironone.repository.ModuleRepository;

import jakarta.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ModuleService {

    @Inject
    ModuleRepository moduleRepository;

    @Inject
    LecturerRepository lecturerRepository;

    @Inject
    CourseRepository courseRepository;

    @Inject
    Validator validator;

    @Transactional
    public ModuleDTO createModule(ModuleDTO moduleDTO) {
        validate(moduleDTO);
        Module module = toEntity(moduleDTO);
        if (moduleDTO.getLecturerId() != null) {
            Lecturer lecturer = lecturerRepository.findById(moduleDTO.getLecturerId());
            if (lecturer == null) {
                throw new NotFoundException("Lecturer with ID " + moduleDTO.getLecturerId() + " not found");
            }
            module.setLecturer(lecturer);
        }
        moduleRepository.save(module);
        return toDTO(module);
    }

    public List<ModuleDTO> getAllModules(int offset, int limit, String sortBy, String sortDir, String filterName) {
        return moduleRepository.findAllModules(offset, limit, sortBy, sortDir, filterName)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public long getModuleCount(String filterName) {
        return moduleRepository.countModules(filterName);
    }

    public ModuleDTO getModuleById(String id) {
        Module module = moduleRepository.findById(id);
        if (module == null) {
            throw new NotFoundException("Module with ID " + id + " not found");
        }
        return toDTO(module);
    }

    @Transactional
    public ModuleDTO updateModule(String id, ModuleDTO updatedModuleDTO) {
        validate(updatedModuleDTO);
        Module existingModule = moduleRepository.findById(id);
        if (existingModule == null) {
            throw new NotFoundException("Module with ID " + id + " not found");
        }
        existingModule.setName(updatedModuleDTO.getName());
        existingModule.setNumberOfCredits(updatedModuleDTO.getNumberOfCredits());
        if (updatedModuleDTO.getLecturerId() != null) {
            Lecturer lecturer = lecturerRepository.findById(updatedModuleDTO.getLecturerId());
            if (lecturer == null) {
                throw new NotFoundException("Lecturer with ID " + updatedModuleDTO.getLecturerId() + " not found");
            }
            existingModule.setLecturer(lecturer);
        }
        moduleRepository.update(existingModule);
        return toDTO(existingModule);
    }

    @Transactional
    public void deleteModule(String id) {
        Module module = moduleRepository.findById(id);
        if (module == null) {
            throw new NotFoundException("Module with ID " + id + " not found");
        }
        moduleRepository.delete(id);
    }

    public List<ModuleDTO> getModuleByLecturerId(String id) {
        return moduleRepository.findByLecturerId(id)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void assignModuleToCourse(String moduleId, String courseId) {
        Module module = moduleRepository.findById(moduleId);
        if (module == null) {
            throw new NotFoundException("Module with ID " + moduleId + " not found");
        }
        Course course = courseRepository.findById(courseId);
        if (course == null) {
            throw new NotFoundException("Course with ID " + courseId + " not found");
        }
        if (!course.getModules().contains(module)) {
            course.getModules().add(module);
            courseRepository.update(course);
        }
    }

    private ModuleDTO toDTO(Module module) {
        ModuleDTO dto = new ModuleDTO();
        dto.setModuleId(module.getModuleId());
        dto.setName(module.getName());
        dto.setNumberOfCredits(module.getNumberOfCredits());
        dto.setLecturerId(module.getLecturer() != null ? module.getLecturer().getLecturerId() : null);
        return dto;
    }

    private Module toEntity(ModuleDTO dto) {
        Module module = new Module();
        module.setModuleId(dto.getModuleId());
        module.setName(dto.getName());
        module.setNumberOfCredits(dto.getNumberOfCredits());
        return module;
    }

    private void validate(ModuleDTO moduleDTO) {
        var violations = validator.validate(moduleDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}