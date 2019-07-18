package com.book.entity;

import com.book.config.security.permission.Permission;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "user_permissions")
public class UserPermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column
    String username;
    @Column
    Permission permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}