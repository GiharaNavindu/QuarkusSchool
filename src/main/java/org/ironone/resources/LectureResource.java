package org.ironone.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ironone.dto.LectureDTO;
import org.ironone.dto.UpcomingLectureDTO;
import org.ironone.service.LectureService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/lecture")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class LectureResource {

    @Inject
    LectureService lectureService;

    @POST
    @Transactional
    public Response createLecture(@Valid LectureDTO lectureDTO) {
        LectureDTO created = lectureService.createLecture(lectureDTO);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public Map<String, Object> getAllLectures(
            @QueryParam("offset") @DefaultValue("0") int offset,
            @QueryParam("limit") @DefaultValue("10") int limit,
            @QueryParam("sortBy") String sortBy,
            @QueryParam("sortDir") String sortDir,
            @QueryParam("filterModule") String filterModuleId) {
        List<LectureDTO> lectures = lectureService.getAllLectures(offset, limit, sortBy, sortDir, filterModuleId);
        long total = lectureService.getLectureCount(filterModuleId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", lectures);
        response.put("total", total);
        return response;
    }

    @GET
    @Path("/{id}")
    public LectureDTO getLectureById(@PathParam("id") String id) {
        return lectureService.getLectureById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public LectureDTO updateLecture(@PathParam("id") String id, @Valid LectureDTO updatedLecture) {
        return lectureService.updateLecture(id, updatedLecture);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteLecture(@PathParam("id") String id) {
        lectureService.deleteLecture(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/student/{studentId}/upcoming")
    public List<UpcomingLectureDTO> getUpcomingLecturesByStudentId(@PathParam("studentId") String studentId) {
        return lectureService.getUpcomingLecturesByStudentId(studentId);
    }
}