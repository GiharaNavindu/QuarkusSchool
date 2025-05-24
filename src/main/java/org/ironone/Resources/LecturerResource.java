package org.ironone.Resources;


import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ironone.Entity.Lecturer;
import org.ironone.Entity.Student;
import org.ironone.service.LecturerService;
import org.ironone.service.StudentService;

import java.util.List;

@Path("/lecturer")
//@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LecturerResource {

    @Inject
    LecturerService lecturerService;

    //POST
    @POST
    @Transactional
    public Response createLecturer(Lecturer lecturer) {
        LecturerService.createStudent(lecturer);
        return Response.status(Response.Status.CREATED).entity(lecturer).build();
    }

    @GET
    @Transactional

    public List<Student> getAllStudent(){
        LecturerService.getAllStudents();
        return LecturerService.getAllStudents();
    }

    @GET
    @Path("/{id}")

    public Student getStudentById(@PathParam("id") String id){
        LecturerService.getStudentById(id);
        return LecturerService.getStudentById(id);
    }

    @PUT
    @Path("/{id}")
    public Student updateStudent(@PathParam("id") String id, Student updatedStudent){
        LecturerService.updateStudent(id, updatedStudent);
        return LecturerService.getStudentById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id")String id){
        LecturerService.deleteStudent(id);
        return Response.status(Response.Status.NO_CONTENT).entity(id).build();
    }
}
