package com.book.service;

import com.book.entity.User;
import com.book.entity.UserPermission;
import com.book.repository.UserPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPermissionService {

    @Autowired
    UserPermissionRepository userPermissionRepository;

    public List<UserPermission> getPermissionByUsername(String username){
        return userPermissionRepository.findByUsername(username);
    }

    public void save(UserPermission userPermissions) {
        userPermissionRepository.save(userPermissions);
    }
}
