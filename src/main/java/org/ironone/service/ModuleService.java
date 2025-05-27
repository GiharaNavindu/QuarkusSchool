package org.ironone.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.ironone.entity.Student;
import org.ironone.repository.ModuleRepository;
import org.ironone.entity.Module;

import java.util.List;

@ApplicationScoped
public class ModuleService {

    @Inject
    ModuleRepository moduleRepository;

    @Transactional
    public Module createModule(Module module) {
        if(module.getModuleId()==null || module.getName() ==null || module.getNumberOfCredits() ==null)
            throw new IllegalArgumentException("Module Id, Name and Number of Credits cannot be null");


        moduleRepository.persist(module);
        return module;
    }

    public List<Module> getAllModules(){
        return moduleRepository.findAllModules();
    }

    public Module getModuleById(String id){
        Module module = moduleRepository.findById(id);
        if(module ==null){
            throw new IllegalArgumentException("Module with Id "+id+" not found");
        }
        return module;
    }

    @Transactional
    public void updateModule(String id, Module updatedModule) {
        Module existingModule = moduleRepository.findById(id);
        if (existingModule == null) {
            throw new NotFoundException("Student with ID " + id + " not found");

        }
        existingModule.setName(updatedModule.getName());
        existingModule.setModuleId(updatedModule.getModuleId());
        existingModule.setLecturer(updatedModule.getLecturer());
        existingModule.setLectures(updatedModule.getLectures());
        existingModule.setNumberOfCredits(updatedModule.getNumberOfCredits());
    }

    @Transactional
    public void deleteModule(String id) {
        Module module = moduleRepository.findById(id);
        if (module == null) {
            throw new NotFoundException("Student with ID " + id + " not found");
        }
        moduleRepository.delete(id);
    }



    public List<Module> getModuleByLectturerId(String id) {
        Module module = moduleRepository.findByLecturerId(id);

        if(module==null){
            throw new IllegalArgumentException("Lecturer with Id "+id+" not found");
        }
        return moduleRepository.find("lecturer.lecturerId = ?1", id).list();
    }
}
