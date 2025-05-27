package org.ironone.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.ironone.dto.AttendanceData;
import org.ironone.service.AttendanceService;

import java.util.List;

@Path("/api/attendance")
@Produces(MediaType.APPLICATION_JSON)
public class AttendanceResource {

    @Inject
    AttendanceService attendanceService;

    @GET
    @Path("/{studentId}")
    public List<AttendanceData> getAttendanceByStudentId(@PathParam("studentId") String studentId) {
        return attendanceService.getAttendanceByStudentId(studentId);
    }
}