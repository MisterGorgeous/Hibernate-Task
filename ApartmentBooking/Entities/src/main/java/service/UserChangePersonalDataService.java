package service;

import dao.UsersDAO;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserChangePersonalDataService {
    private UsersDAO usersDAO;

    @Autowired
    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }



    public boolean changeFirstName(String firsName, String lastName, String password,String newFirstName) {
        User user = usersDAO.getByAutorizationInfo(firsName, lastName,password);
        if (user == null){
            return false;
        }

        user.setFirstName(newFirstName);
        return usersDAO.update(user);
    }

    public boolean changeLastName(String firsName, String lastName, String password,String newLastName) {
        User user = usersDAO.getByAutorizationInfo(firsName, lastName,password);
        if (user == null){
            return false;
        }

        user.setLastName(newLastName);
        return usersDAO.update(user);
    }

    public boolean changeEmail(String firsName, String lastName, String password,String newEmail) {
        User user = usersDAO.getByAutorizationInfo(firsName, lastName,password);
        if (user == null){
            return false;
        }

        user.setEmail(newEmail);
        return usersDAO.update(user);
    }

    public boolean changeBirthady(String firsName, String lastName, String password,Date correctBirthday) {
        User user = usersDAO.getByAutorizationInfo(firsName, lastName,password);
        if (user == null){
            return false;
        }

        user.setBirthday(correctBirthday);
        return usersDAO.update(user);
    }
}
