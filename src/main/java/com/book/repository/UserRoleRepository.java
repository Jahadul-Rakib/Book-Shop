package com.book.repository;

import com.book.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<Role, Long> {
}
