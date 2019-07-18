package com.book.repository;

import com.book.entity.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaymentRepo extends CrudRepository<UserPayment, Long> {
}
