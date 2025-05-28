package org.ironone.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ironone.dto.AttendanceData;
//import org. SnowdenService;
import org.ironone.dto.MarkAttendanceDTO;
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
}