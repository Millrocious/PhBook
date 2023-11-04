package com.phonebook.service.impl;

import com.phonebook.entity.User;
import com.phonebook.service.UserService;
import com.phonebook.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public User saveUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            HibernateUtil.performTransaction(session, s -> s.persist(user));
        }

        return user;
    }

    @Override
    public User updateUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            HibernateUtil.performTransaction(session, s -> s.merge(user));
        }

        return user;
    }

    @Override
    public void deleteUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            HibernateUtil.performTransaction(session, s -> s.remove(user));
        }
    }

    @Override
    public User getUserById(Integer userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, userId);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

}
