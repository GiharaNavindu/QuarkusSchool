//package org.ironone.Resources;
//
//
//import io.smallrye.jwt.build.Jwt;
//import jakarta.enterprise.inject.Produces;
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
//import jakarta.ws.rs.Consumes;
//import jakarta.ws.rs.POST;
//import jakarta.ws.rs.Path;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import org.ironone.Entity.Lecturer;
//import org.ironone.Entity.Users;
//import org.ironone.Repository.LecturerRepository;
//import org.ironone.Repository.UserRepository;
//import org.mindrot.jbcrypt.BCrypt;
//
//import java.security.Timestamp;
//import java.util.UUID;
//
//@Path("/api/auth")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class AuthResource {
//
//    @Inject
//    UserRepository userRepository;
//
//    @Inject
//    LecturerRepository lecturerRepository;
//
//    @POST
//    @Path("/login")
//    @Transactional
//    public Response login(UserCredentials credentials) {
//        Users user = userRepository.findByEmail(credentials.email);
//        //if the user isnt there or bcrypt pawds are not matching
//        if (user == null || !BCrypt.checkpw(credentials.password, user.getPassword())) {
//            return Response.status(Response.Status.UNAUTHORIZED)
//                    .entity(new ErrorResponse("Invalid email or password"))
//                    .build();
//        }
//
//        // Update last login
////        user.setLastLogin(new Timestamp(System.currentTimeMillis()));
//        userRepository.persist(user);
//
//        // Generate JWT
//        String token = Jwt.issuer("student-management")
//                .upn(user.getEmail())
//                .groups(user.getRole())
//                .sign();
//
//        return Response.ok(new LoginResponse(token)).build();
//    }
//
//    @POST
//    @Path("/register")
//    @Transactional
//    public Response register(LecturerRegistration registration) {
//        // Check if email already exists
//        if (userRepository.findByEmail(registration.email) != null) {
//            return Response.status(Response.Status.BAD_REQUEST)
//                    .entity(new ErrorResponse("Email already exists"))
//                    .build();
//        }
//
//        // Create User
//        Users user = new Users();
//        user.setUserId(UUID.randomUUID().toString());
//        user.setEmail(registration.email);
//        user.setPassword(BCrypt.hashpw(registration.password, BCrypt.gensalt()));
//        user.setRole("LECTURER");
////        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//        userRepository.persist(user);
//
//        // Create Lecturer
//        Lecturer lecturer = new Lecturer();
//        lecturer.setLecturerId(UUID.randomUUID().toString());
//        lecturer.setFirstName(registration.firstName);
//        lecturer.setLastName(registration.lastName);
//        lecturer.setEmail(registration.email);
//        lecturer.setAge(registration.age);
////        lecturer.setDob(registration.dob);
//        lecturer.setDegree(registration.degree);
//        lecturer.setUserId(user.getUserId());
//        lecturerRepository.persist(lecturer);
//
//        return Response.status(Response.Status.CREATED)
//                .entity(new SuccessResponse("Lecturer registered successfully"))
//                .build();
//    }
//
//    public static class UserCredentials {
//        public String email;
//        public String password;
//    }
//
//    public static class LecturerRegistration {
//        public String email;
//        public String password;
//        public String firstName;
//        public String lastName;
//        public Integer age;
//        public java.util.Date dob;
//        public String degree;
//    }
//
//    public static class LoginResponse {
//        public String token;
//
//        public LoginResponse(String token) {
//            this.token = token;
//        }
//    }
//
//    public static class ErrorResponse {
//        public String message;
//
//        public ErrorResponse(String message) {
//            this.message = message;
//        }
//    }
//
//    public static class SuccessResponse {
//        public String message;
//
//        public SuccessResponse(String message) {
//            this.message = message;
//        }
//    }
//}