package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void addUser(User user);
    List<User> listUsers();
    User getUser(Long id);
    void editUser(Long id, User user);
    void deleteUser(Long id);
    Optional <User> findByUserName(String userName);
}
