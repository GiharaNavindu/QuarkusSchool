package org.ironone.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ironone.dto.ProgressData;
import org.ironone.dto.StudentDTO;
import org.ironone.service.StudentService;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Path("/api/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudenResource {

    @Inject
    StudentService studentService;

    @POST
    @Transactional
    public Response createStudent(@Valid StudentDTO student) {
        StudentDTO created = studentService.createStudent(student);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public Map<String, Object> getAllStudent(
            @QueryParam("offset") @DefaultValue("0") int offset,
            @QueryParam("limit") @DefaultValue("10") int limit,
            @QueryParam("sortBy") String sortBy,
            @QueryParam("sortDir") String sortDir,
            @QueryParam("filterName") String filterName) {
        List<StudentDTO> students = studentService.getAllStudents(offset, limit, sortBy, sortDir, filterName);
        long total = studentService.getStudentCount(filterName);
        Map<String, Object> response = new HashMap<>();
        response.put("data", students);
        response.put("total", total);
        return response;
    }

    @GET
    @Path("/{id}")
    public StudentDTO getStudentById(@PathParam("id") String id) {
        return studentService.getStudentById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public StudentDTO updateStudent(@PathParam("id") String id, @Valid StudentDTO updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteStudent(@PathParam("id") String id) {
        studentService.deleteStudent(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}/progress")
    public ProgressData getStudentProgress(@PathParam("id") String id) {
        return studentService.getStudentProgress(id);
    }
}