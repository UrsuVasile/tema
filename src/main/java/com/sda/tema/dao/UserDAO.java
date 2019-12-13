package com.sda.tema.dao;

import com.sda.tema.config.HibernateUtil;
import com.sda.tema.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
@Repository
public class UserDAO {

    HibernateUtil hibernateUtil;

    public UserDAO() {
        hibernateUtil = new HibernateUtil();
    }

    public void insertUser(User user) {
        hibernateUtil.openSessionAndTransaction();
        hibernateUtil.session.persist(user);
        hibernateUtil.closeSessionAndTransaction();
    }

    public void deleteUser(String userName) {
        hibernateUtil.openSessionAndTransaction();
        Query deleteQuery = hibernateUtil.session.createNamedQuery("delete_user_by_username");
        deleteQuery.setParameter("username", userName);
        deleteQuery.executeUpdate();
        hibernateUtil.closeSessionAndTransaction();
    }

    public boolean findUserAndPasswordFromDatabase(String userName, String password) {
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_user_and_pasword_from_database");
        query.setParameter("username", userName);
        query.setParameter("password", password);
        try {
            User user = (User) query.getSingleResult();
            hibernateUtil.closeSessionAndTransaction();
            return true;
        } catch (NoResultException e) {
            hibernateUtil.closeSessionAndTransaction();
            return false;
        }
    }

    public void updatePassword(User user) {
        if (user.getId() != 0) {
            hibernateUtil.openSessionAndTransaction();
            hibernateUtil.session.update(user);
            hibernateUtil.closeSessionAndTransaction();
        } else {
            throw new RuntimeException();
        }
    }


    public User findUserByUserName(String userName) {
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_user_from_database");
        query.setParameter("username", userName);
        User user = (User) query.getSingleResult();
        hibernateUtil.closeSessionAndTransaction();
        return user;
    }
}
