package org.ironone.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ironone.dto.EnrollsDTO;
import org.ironone.service.EnrollService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/enroll")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnrollsResource {

    @Inject
    EnrollService enrollService;

    @POST
    @Transactional
    public Response createEnrollment(@Valid EnrollsDTO enroll) {
        EnrollsDTO created = enrollService.createEnroll(enroll);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public Map<String, Object> getAllEnrollments(
            @QueryParam("offset") @DefaultValue("0") int offset,
            @QueryParam("limit") @DefaultValue("10") int limit,
            @QueryParam("sortBy") String sortBy,
            @QueryParam("sortDir") String sortDir,
            @QueryParam("filterCourseName") String filterCourseName) {
        List<EnrollsDTO> enrollments = enrollService.getAllEnrolls(offset, limit, sortBy, sortDir, filterCourseName);
        long total = enrollService.getEnrollCount(filterCourseName);
        Map<String, Object> response = new HashMap<>();
        response.put("data", enrollments);
        response.put("total", total);
        return response;
    }

    @GET
    @Path("/{id}")
    public EnrollsDTO getEnrollmentById(@PathParam("id") Long id) {
        return enrollService.getEnrollById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public EnrollsDTO updateEnrollment(@PathParam("id") Long id, @Valid EnrollsDTO updatedEnroll) {
        return enrollService.updateEnroll(id, updatedEnroll);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteEnrollment(@PathParam("id") Long id) {
        enrollService.deleteEnroll(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/student/{studentId}")
    public List<EnrollsDTO> getEnrollmentsByStudentId(@PathParam("studentId") String studentId) {
        return enrollService.getEnrollmentsByStudentId(studentId);
    }
}