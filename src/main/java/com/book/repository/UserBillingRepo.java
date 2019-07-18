package com.book.repository;

import com.book.entity.UserBilling;
import com.book.entity.UserShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBillingRepo extends JpaRepository<UserBilling, Long> {

}
