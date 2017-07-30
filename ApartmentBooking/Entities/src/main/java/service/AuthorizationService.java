package service;

import dao.UsersDAO;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationService {
    private UsersDAO usersDAO;

    @Autowired
    public void setUsersDAO(UsersDAO DAO) {
        usersDAO = DAO;
    }

    public User authorize(String firsName, String lastName, String password) {
        User user;
        try {
            user = usersDAO.getByAutorizationInfo(firsName, lastName, password);
        } catch (EmptyResultDataAccessException e) {
            return new User() {
                public long getId() {
                    return -1L;
                }
            };
        }
        return user;
    }
}
