package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Controller
public class testUser {
    private UserService userService;

    @Autowired
    public testUser(UserService userService) {
        this.userService = userService;
    }
    @PostConstruct
    public String home(){
        if(userService.listUsers().isEmpty()) {
            userService.addUser(new User("admin", "admin", "Nick", "Petrov", 44, "petrov@petr.ru", Arrays.asList("ROLE_ADMIN")));
            userService.addUser(new User("user", "user", "Andrey", "Ivanov", 42, "ivanov@andr.ru", Arrays.asList("ROLE_USER")));
        }
        return "index";
    }
}
