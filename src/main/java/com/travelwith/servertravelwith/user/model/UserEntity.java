/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith.user.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

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

}
