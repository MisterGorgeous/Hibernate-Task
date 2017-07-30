package service;

import dao.TestAppartmentConfiguration;
import dao.UsersDAO;
import entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppartmentConfiguration.class)
public class UserChangePersonalDataServiceTest {

    @Autowired
    private User existingUser;

    @Mock
    private UsersDAO usersDAO;


    @InjectMocks
    @Autowired
    private UserChangePersonalDataService userChangePersonalDataService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        existingUser.setFirstName("CorrectFirstName");
        existingUser.setLastName("CorrectLastName");
        existingUser.setPassword("CorrectPassword");
    }

    @Test
    public void changeFirstName() throws Exception {
        when(usersDAO.getByAutorizationInfo("CorrectFirstName","CorrectLastName","CorrectPassword"))
                .thenReturn(existingUser);
        when(usersDAO.getByAutorizationInfo("WrongFirstName","WrongLastName","WrongPassword"))
                .thenReturn(null);
        when(usersDAO.update(existingUser)).thenReturn(true);

        Assert.assertEquals(userChangePersonalDataService.changeFirstName("CorrectFirstName","CorrectLastName",
                "CorrectPassword","NewName"),true);
        Assert.assertEquals(userChangePersonalDataService.changeFirstName("WrongFirstName","WrongLastName",
                "WrongPassword","NewName"),false);

    }

}