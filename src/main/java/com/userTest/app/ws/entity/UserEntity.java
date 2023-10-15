package com.userTest.app.ws.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    @Column(name = "username",nullable = false)
    private String username;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date birthdate;
    @Column(name = "country",nullable = false)
    private String country;
    @Column(unique = true)
    private String phoneNumber;
    private String gender;
}
