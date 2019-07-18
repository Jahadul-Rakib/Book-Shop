package com.book.repository;

import com.book.entity.UserShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserShippingRepo extends JpaRepository<UserShipping, Long> {
	
	UserShipping getOne(Long id);
    UserShipping removeById(Long id);
}
