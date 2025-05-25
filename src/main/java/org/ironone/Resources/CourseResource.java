package org.ironone.Resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.ironone.Entity.Course;
import org.ironone.Entity.Lecture;
import org.ironone.service.CourseService;
import org.ironone.service.LectureService;

import java.util.List;

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
    public Course updateCoourse(@PathParam("id") String id, Course updatedCourse){
        courseService.updateCoure(id, updatedCourse);
        return courseService.getCourseById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCourse(@PathParam("id")String id){
        courseService.deleteCourseg(id);
        return Response.status(Response.Status.NO_CONTENT).entity(id).build();
    }
}
