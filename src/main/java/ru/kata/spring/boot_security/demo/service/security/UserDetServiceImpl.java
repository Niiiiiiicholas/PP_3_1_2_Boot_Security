package ru.kata.spring.boot_security.demo.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
@Service
public class UserDetServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public UserDetServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public AccountDet loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> user = userDao.findByUserName(username);
        if (user.size() != 1) {
            throw new UsernameNotFoundException("User ot found");
        }
        return new AccountDet(user.get(0));
    }
}
