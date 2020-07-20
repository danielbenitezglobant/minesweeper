package com.danielbenitez.service;

import com.danielbenitez.model.User;

import java.util.List;

public interface UserService {

    User save(User user);
    List<User> findAll();
    void delete(long id);
    String getCurrentUser();
}
