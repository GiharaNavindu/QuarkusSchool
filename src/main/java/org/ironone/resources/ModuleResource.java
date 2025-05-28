package org.ironone.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ironone.dto.ModuleDTO;
import org.ironone.service.ModuleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/module")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ModuleResource {

    @Inject
    ModuleService moduleService;

    @POST
    @Transactional
    public Response createModule(@Valid ModuleDTO module) {
        ModuleDTO created = moduleService.createModule(module);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public Map<String, Object> getAllModules(
            @QueryParam("offset") @DefaultValue("0") int offset,
            @QueryParam("limit") @DefaultValue("10") int limit,
            @QueryParam("sortBy") String sortBy,
            @QueryParam("sortDir") String sortDir,
            @QueryParam("filterName") String filterName) {
        List<ModuleDTO> modules = moduleService.getAllModules(offset, limit, sortBy, sortDir, filterName);
        long total = moduleService.getModuleCount(filterName);
        Map<String, Object> response = new HashMap<>();
        response.put("data", modules);
        response.put("total", total);
        return response;
    }

    @GET
    @Path("/{id}")
    public ModuleDTO getModuleById(@PathParam("id") String id) {
        return moduleService.getModuleById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public ModuleDTO updateModule(@PathParam("id") String id, @Valid ModuleDTO updatedModule) {
        return moduleService.updateModule(id, updatedModule);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteModule(@PathParam("id") String id) {
        moduleService.deleteModule(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/lecturer/{lecturerId}")
    public List<ModuleDTO> getModulesByLecturerId(@PathParam("lecturerId") String id) {
        return moduleService.getModuleByLecturerId(id);
    }

    @POST
    @Path("/assign")
    @Transactional
    public Response assignModuleToCourse(@QueryParam("moduleId") String moduleId, @QueryParam("courseId") String courseId) {
        moduleService.assignModuleToCourse(moduleId, courseId);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/course")
    @Transactional
    public Response getModulesByCourseId(@QueryParam("courseId") String courseId) {
        moduleService.getModuleByCourseId(courseId);
        return Response.status(Response.Status.OK).build();
    }


}