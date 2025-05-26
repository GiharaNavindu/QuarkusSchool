package org.ironone.resources;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ironone.entity.Student;
import org.ironone.service.StudentService;

import java.util.List;

@Path("/api/student")
@jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
@jakarta.ws.rs.Consumes(MediaType.APPLICATION_JSON)
public class StudenResource {

    @Inject
    StudentService studentService;

    //POST
    @POST
    @Transactional
    public Response createStudent(Student student) {
        studentService.createStudent(student);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    @GET
    @Transactional
    public List<Student> getAllStudent(){
        studentService.getAllStudents();
        return studentService.getAllStudents();
    }

    @GET
    @Path("/{id}")

    public Student getStudentById(@PathParam("id") String id){
        studentService.getStudentById(id);
        return studentService.getStudentById(id);
    }

    @PUT
    @Path("/{id}")
    public Student updateStudent(@PathParam("id") String id, Student updatedStudent){
        studentService.updateStudent(id, updatedStudent);
        return studentService.getStudentById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id")String id){
        studentService.deleteStudent(id);
        return Response.status(Response.Status.NO_CONTENT).entity(id).build();
    }
}