package org.ironone.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class Users  {
    @Id
    @Column(name = "User_Id")
    public String userId;

    @Column(name = "Email", nullable = false, unique = true)
    public String email;

    @Column(name = "Password", nullable = false)
    public String password;

    @Column(name = "Role", nullable = false)
    public String role;

    @Column(name = "Created_At")
    public java.sql.Timestamp createdAt;

    @Column(name = "Last_Login")
    public java.sql.Timestamp lastLogin;
}


