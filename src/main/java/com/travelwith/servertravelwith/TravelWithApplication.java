/*
 * Copyright (c) 2018. 
 * Travel With
 * Marcin Witek
 */

package com.travelwith.servertravelwith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class TravelWithApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelWithApplication.class, args);
    }
}
