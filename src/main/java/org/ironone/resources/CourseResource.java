package org.ironone.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ironone.entity.Course;
import org.ironone.service.CourseService;

import java.util.List;

@Path("/api/course")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseResource {
    @Inject
    CourseService courseService;


    @POST
    @Transactional
    public Response createCourse(Course course) {
        courseService.createCourse(course);
        return Response.status(Response.Status.CREATED).entity(course).build();
    }

    @GET
    @Transactional
    public List<Course> getAllCourses(){
        courseService.getAllCourses();
        return courseService.getAllCourses();
    }

    @GET
    @Path("/{id}")
    public Course getCourseById(@PathParam("id") String id){
        courseService.getCourseById(id);
        return courseService.getCourseById(id);
    }

    @PUT
    @Path("/{id}")
    public Course updateCourse(@PathParam("id") String id, Course updatedCourse){
        courseService.updateCourse(id, updatedCourse);
        return courseService.getCourseById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCourse(@PathParam("id")String id){
        courseService.deleteCourse(id);
        return Response.status(Response.Status.NO_CONTENT).entity(id).build();
    }
}
