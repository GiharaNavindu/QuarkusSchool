package org.ironone.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ironone.dto.CourseDTO;
import org.ironone.service.CourseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/course")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseResource {
    @Inject
    CourseService courseService;

    @POST
    @Transactional
    public Response createCourse(@Valid CourseDTO course) {
        CourseDTO created = courseService.createCourse(course);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public Map<String, Object> getAllCourses(
            @QueryParam("offset") @DefaultValue("0") int offset,
            @QueryParam("limit") @DefaultValue("10") int limit,
            @QueryParam("sortBy") String sortBy,
            @QueryParam("sortDir") String sortDir,
            @QueryParam("filterName") String filterName) {
        List<CourseDTO> courses = courseService.getAllCourses(offset, limit, sortBy, sortDir, filterName);
        long total = courseService.getCourseCount(filterName);
        Map<String, Object> response = new HashMap<>();
        response.put("data", courses);
        response.put("total", total);
        return response;
    }

    @GET
    @Path("/{id}")
    public CourseDTO getCourseById(@PathParam("id") String id) {
        return courseService.getCourseById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public CourseDTO updateCourse(@PathParam("id") String id, @Valid CourseDTO updatedCourse) {
        return courseService.updateCourse(id, updatedCourse);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCourse(@PathParam("id") String id) {
        courseService.deleteCourse(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}