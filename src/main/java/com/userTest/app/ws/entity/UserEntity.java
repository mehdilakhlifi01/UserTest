package com.userTest.app.ws.entity;

import com.userTest.app.ws.constants.GenderEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { UserEntity.USERNAME_NAME, UserEntity.BIRTHDAY_NAME, UserEntity.COUNTRY_NAME}) })
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserEntity {
    public static final String USERNAME_NAME = "username";
    public static final String BIRTHDAY_NAME = "birthday";
    public static final String COUNTRY_NAME = "country";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = USERNAME_NAME)
    private String username;

    @Column(name = BIRTHDAY_NAME)
    private LocalDate birthday;

    @Column(name = COUNTRY_NAME)
    private String country;

    private String phone;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

}
