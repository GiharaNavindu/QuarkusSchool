package org.ironone.resources;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ironone.entity.Lecturer;
import org.ironone.service.LecturerService;

import java.util.List;

@Path("/api/lecturer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LecturerResource {

    @Inject
    LecturerService lecturerService;

    //POST
    @POST
    @Transactional
    public Response createLecturer(Lecturer lecturer) {
        lecturerService.createLecturer(lecturer);
        return Response.status(Response.Status.CREATED).entity(lecturer).build();
    }

    @GET
    @Transactional

    public List<Lecturer> getAllLecturers(){
        lecturerService.getAllLecturers();
        return lecturerService.getAllLecturers();
    }

    @GET
    @Path("/{id}")

    public Lecturer getLecturerById(@PathParam("id") String id){
        lecturerService.getLecturerById(id);
        return lecturerService.getLecturerById(id);
    }

    @PUT
    @Path("/{id}")
    public Lecturer updateLecturer(@PathParam("id") String id, Lecturer updatedLecturer){
        lecturerService.updateLecturer(id, updatedLecturer);
        return lecturerService.getLecturerById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLecturer(@PathParam("id")String id){
        lecturerService.deleteLecturer(id);
        return Response.status(Response.Status.NO_CONTENT).entity(id).build();
    }
}
