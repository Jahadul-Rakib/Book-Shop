package com.book.repository;

import com.book.entity.UserPayment;


public interface UserPaymentService {
    UserPayment findById(Long id);
    void removeById(Long id);
}
