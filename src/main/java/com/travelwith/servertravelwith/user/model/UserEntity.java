/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    @Column(unique = true)
    private String email;

    private String phoneNumber;

    @NotNull
    @Enumerated
    private Role role = Role.USER;

}
