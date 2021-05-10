package com.manning.sbip.ch06.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "CT_USERS")
@NoArgsConstructor
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
