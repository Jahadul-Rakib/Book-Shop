package com.book.impl;

import com.book.entity.UserPayment;
import com.book.repository.UserPaymentRepo;
import com.book.repository.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserPaymentImpl implements UserPaymentService {

    @Autowired
    private UserPaymentRepo userPaymentRepo;

    @Override
    public UserPayment findById(Long id) {
        return userPaymentRepo.findById(id).get();
    }

    @Override
    public void removeById(Long id) {
        userPaymentRepo.deleteById(id);
    }
}
