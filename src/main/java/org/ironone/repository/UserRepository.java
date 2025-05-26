package org.ironone.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.ironone.entity.Users;

@ApplicationScoped
public class UserRepository implements PanacheRepository<Users> {
    public Users findByEmail(String email) {
        return find("email", email).firstResult();
    }
}