package com.vladnickgo.Project.dao.entity;

public enum Role {
    ADMIN,
    USER;

    public static Role getRole(int role_id) {
        if (role_id != 0) {
            switch (role_id) {
                case 1:
                    return ADMIN;
                case 2:
                    return USER;
                default:
                    throw new IllegalArgumentException("This role doesn't exist");
            }

        } else {
            throw new IllegalArgumentException("Role is null");
        }
    }

}
