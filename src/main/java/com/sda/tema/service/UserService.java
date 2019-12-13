package com.sda.tema.service;

import com.sda.tema.dao.UserDAO;
import com.sda.tema.dto.UserDTO;
import com.sda.tema.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public void insertUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        userDAO.insertUser(user);
    }

    public void deleteUser(String userName) {
        userDAO.deleteUser(userName);
    }

    public boolean findUserAndPasswordFromDatabase(String userName, String password) {
        return userDAO.findUserAndPasswordFromDatabase(userName, password);
    }

    public void updateUserPassword(UserDTO userDTO) {
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setId(userDAO.findUserByUserName(userDTO.getUserName()).getId());
        userDAO.updatePassword(user);
    }

    public User findUserByUserName(String userName) {
        User user = userDAO.findUserByUserName(userName);
        return user;
    }
}
