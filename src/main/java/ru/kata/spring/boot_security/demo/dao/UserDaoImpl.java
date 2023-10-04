package ru.kata.spring.boot_security.demo.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void editUser(Long id, User user) {
        entityManager.merge(entityManager.find(User.class, id));
//        User edit = entityManager.find(User.class, id);
//        edit.setUserName(user.getUserName());
//        edit.setFirstName(user.getFirstName());
//        edit.setSecondName(user.getSecondName());
//        edit.setAge(user.getAge());
//        edit.setEmail(user.getEmail());
//        edit.setPassword(user.getPassword());

    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public Optional <User> findByUserName(String userName) {
        return entityManager.createQuery("select u from User u join fetch u.roles where  u.userName =:username")
                .setParameter("username", userName).getResultStream().findFirst();
    }
}
