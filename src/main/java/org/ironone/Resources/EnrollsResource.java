package org.ironone.Resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ironone.Entity.Enrolls;
import org.ironone.Entity.Lecture;
import org.ironone.service.EnrollService;
import org.ironone.service.LectureService;

import java.util.List;


@Path("api/enroll")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnrollsResource {

    @Inject
    EnrollService enrollsService;


    @POST
    @Transactional
    public Response createEnrollment(Enrolls enroll) {
        enrollsService.createEnroll(enroll);
        return Response.status(Response.Status.CREATED).entity(enroll).build();
    }

    @GET
    @Transactional
    public List<Enrolls> getAllEnrollments(){
        enrollsService.getAllEnrolls();
        return enrollsService.getAllEnrolls();
    }

    @GET
    @Path("/{id}")
    public Enrolls getEnrollmentById(@PathParam("id") Long id){
        enrollsService.getEnrollById(id);
        return enrollsService.getEnrollById(id);
    }

    @PUT
    @Path("/{id}")
    public Enrolls updateEnrollment(@PathParam("id") Long id, Enrolls UpdatedEnroll){
        enrollsService.updateEnroll(id, UpdatedEnroll);
        return enrollsService.getEnrollById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEnrollment(@PathParam("id")Long id){
        enrollsService.deleteEnroll(id);
        return Response.status(Response.Status.NO_CONTENT).entity(id).build();
    }

}
