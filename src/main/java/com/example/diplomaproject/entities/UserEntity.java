package com.example.diplomaproject.entities;


import com.example.diplomaproject.entities.enums.PrescriptionStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "userSecondname")
    private String userSecondname;

    @Column(name = "userThirdname")
    private String userThirdname;

    @Column(name = "password")
    private String password;
    @Column(name = "iin")
    private String iin;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "imageUrl")
    private String imageUrl;

    @ManyToMany
    @JoinTable(
            name="users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<RoleEntity> roles;
}
