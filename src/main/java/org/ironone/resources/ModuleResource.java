package org.ironone.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ironone.entity.Lecturer;
import org.ironone.service.ModuleService;
import org.ironone.entity.Module;

import java.util.List;

@Path("/api/module")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ModuleResource {

    @Inject
    ModuleService moduleService;



    @POST
    @Transactional
    public Response createModule(Module module) {
        moduleService.createModule(module);
        return Response.status(Response.Status.CREATED).entity(module).build();
    }

    @GET
    @Transactional

    public List<Module> getAllModules(){
        moduleService.getAllModules();
        return moduleService.getAllModules();
    }

    @GET
    @Path("/{id}")
    public Module getModuleById(@PathParam("id") String id){
        moduleService.getModuleById(id);
        return moduleService.getModuleById(id);
    }

    @PUT
    @Path("/{id}")
    public Module updateLecturer(@PathParam("id") String id, Module updateModule){
        moduleService.updateModule(id, updateModule);
        return moduleService.getModuleById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLecturer(@PathParam("id")String id){
        moduleService.deleteModule(id);
        return Response.status(Response.Status.NO_CONTENT).entity(id).build();
    }

    @GET
    @Path("/lecturer/{lecturerId}")
    public List<Module> getModulesByLecturerId(@PathParam("lecturerId") String id) {
        return moduleService.getModuleByLectturerId(id);
    }
}
