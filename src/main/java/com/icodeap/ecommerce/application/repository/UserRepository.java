package com.icodeap.ecommerce.application.repository;

import com.icodeap.ecommerce.domain.User;

public interface UserRepository {

    User createUser(User user);

    User findByEmail(String email);

    User findById(Integer id);
}
