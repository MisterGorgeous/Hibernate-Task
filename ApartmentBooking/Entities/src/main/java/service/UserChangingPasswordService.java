package service;

import dao.UsersDAO;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class UserChangingPasswordService {
    private UsersDAO usersDAO;


    @Autowired
    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }


    public boolean changeUserPassword(String firsName, String lastName, String oldPassword, String newPassword) {
        User user;
        try {
            user = usersDAO.getByAutorizationInfo(firsName, lastName, oldPassword);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }

        user.setPassword(newPassword);
        return usersDAO.update(user);
    }
}
