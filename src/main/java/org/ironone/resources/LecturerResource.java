package org.ironone.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ironone.dto.LecturerDTO;
import org.ironone.service.LecturerService;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Path("/api/lecturer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LecturerResource {

    @Inject
    LecturerService lecturerService;

    @POST
    @Transactional
    public Response createLecturer(@Valid LecturerDTO lecturer) {
        LecturerDTO created = lecturerService.createLecturer(lecturer);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public Map<String, Object> getAllLecturers(
            @QueryParam("offset") @DefaultValue("0") int offset,
            @QueryParam("limit") @DefaultValue("10") int limit,
            @QueryParam("sortBy") String sortBy,
            @QueryParam("sortDir") String sortDir,
            @QueryParam("filterName") String filterName) {
        List<LecturerDTO> lecturers = lecturerService.getAllLecturers(offset, limit, sortBy, sortDir, filterName);
        long total = lecturerService.getLecturerCount(filterName);
        Map<String, Object> response = new HashMap<>();
        response.put("data", lecturers);
        response.put("total", total);
        return response;
    }

    @GET
    @Path("/{id}")
    public LecturerDTO getLecturerById(@PathParam("id") String id) {
        return lecturerService.getLecturerById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public LecturerDTO updateLecturer(@PathParam("id") String id, @Valid LecturerDTO updatedLecturer) {
        return lecturerService.updateLecturer(id, updatedLecturer);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteLecturer(@PathParam("id") String id) {
        lecturerService.deleteLecturer(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}