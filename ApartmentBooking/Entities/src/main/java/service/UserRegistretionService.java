package service;

import dao.UsersDAO;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserRegistretionService {
    private UsersDAO usersDAO;
    private User user;

    @Autowired
    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }

    public boolean register(String firsName, String lastName, String password, String email, Date birthday) {
        User userWithSameName = usersDAO.getByName(firsName, lastName);
        if (userWithSameName != null){
            return false;
        }

        user.setFirstName(firsName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setEmail(email);
        user.setBirthday(birthday);

        return usersDAO.create(user);
    }
}
