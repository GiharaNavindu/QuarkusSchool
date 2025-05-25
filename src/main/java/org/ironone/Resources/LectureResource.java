package org.ironone.Resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.ironone.Entity.Lecture;
import org.ironone.Entity.Lecturer;
import org.ironone.service.LectureService;

import java.util.List;

public class LectureResource {

    @Inject
    LectureService lectureService;


    @POST
    @Transactional
    public Response createLecture(Lecture lecture) {
        lectureService.createLecture(lecture);
        return Response.status(Response.Status.CREATED).entity(lecture).build();
    }

    @GET
    @Transactional
    public List<Lecture> getAllLectures(){
        lectureService.getAllLectures();
        return lectureService.getAllLectures();
    }

    @GET
    @Path("/{id}")
    public Lecture getLectureById(@PathParam("id") String id){
        lectureService.getLectureById(id);
        return lectureService.getLectureById(id);
    }

    @PUT
    @Path("/{id}")
    public Lecture updateLecture(@PathParam("id") String id, Lecture updatedLecture){
        lectureService.updateLecture(id, updatedLecture);
        return lectureService.getLectureById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLecture(@PathParam("id")String id){
        lectureService.deleteLecture(id);
        return Response.status(Response.Status.NO_CONTENT).entity(id).build();
    }
}
