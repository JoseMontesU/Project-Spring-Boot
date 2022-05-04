package com.example.tiendabackend.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.tiendabackend.entity.User;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public User addUser(User user) {
        entityManager.createNativeQuery("insert into users (id,user,password,email,active) values (?,?,?,?,?)")
                .setParameter(1, user.getId())
                .setParameter(2, user.getUser())
                .setParameter(3, user.getPassword())
                .setParameter(4, user.getEmail())
                .setParameter(5, user.getActive())
                .executeUpdate();
        return user;
    }

    public List<User> getUsers() {
        @SuppressWarnings("unchecked")
        List<User> userList = entityManager.createNativeQuery("select * from users",
                User.class)
                .getResultList();
        return userList;
    }

    public boolean session(String userName, String password) {
        try {
            Object a = entityManager.createNativeQuery("select * from users where user=? and password=?", User.class)
                    .setParameter(1, userName)
                    .setParameter(2, password)
                    .getSingleResult();
            System.out.println(a);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
