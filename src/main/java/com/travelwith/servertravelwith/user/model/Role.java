package com.travelwith.servertravelwith.user.model;

/**
 * Created by Marcin on 04.11.2018.
 */
public enum Role {
    USER("User"),
    ADMIN("Admin");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
