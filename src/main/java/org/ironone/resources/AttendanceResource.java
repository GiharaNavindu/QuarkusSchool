package org.ironone.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ironone.dto.AttendanceData;
import org.ironone.dto.MarkAttendanceDTO;
import org.ironone.dto.UpdateAttendanceDTO;
import org.ironone.entity.Attendance;
import org.ironone.service.AttendanceService;

import java.util.List;

@Path("/api/attendance")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AttendanceResource {

    @Inject
    AttendanceService attendanceService;

    @GET
    @Path("/{studentId}")
    public List<AttendanceData> getAttendanceByStudentId(@PathParam("studentId") String studentId) {
        return attendanceService.getAttendanceByStudentId(studentId);
    }

    @POST
    @Path("/mark")
    @Transactional
    public Response markAttendance(MarkAttendanceDTO dto) {
        attendanceService.markAttendance(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/lecture/{lectureId}")
    public List<Attendance> getAttendanceByLectureId(@PathParam("lectureId") int lectureId) {
        return attendanceService.getAttendanceByLectureId(lectureId);
    }

    @PUT
    @Path("/{attendanceId}")
    @Transactional
    public Response updateAttendance(@PathParam("attendanceId") Long attendanceId, UpdateAttendanceDTO dto) {
        try {
            attendanceService.updateAttendance(attendanceId, dto.isAttended(), dto.getMarkedAt());
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{attendanceId}")
    @Transactional
    public Response deleteAttendance(@PathParam("attendanceId") Long attendanceId) {
        try {
            attendanceService.deleteAttendance(attendanceId);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}