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

import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppartmentConfiguration.class)
public class AuthorizationServiceTest {

    @Autowired
    private User existingUser;
    
    @Autowired
    private User substitutedUser;
    
    @Mock
    private UsersDAO usersDAO;
    

    @InjectMocks
    @Autowired
    private AuthorizationService authorizationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        existingUser.setFirstName("CorrectFirstName");
        existingUser.setLastName("CorrectLastName");
        existingUser.setPassword("CorrectPassword");
        substitutedUser.setId(-1);
    }

    @Test
    public void authorize() throws Exception {
        when(usersDAO.getByAutorizationInfo("CorrectFirstName","CorrectLastName","CorrectPassword"))
                .thenReturn(existingUser);
        when(usersDAO.getByAutorizationInfo("WrongFirstName","WrongLastName","WrongPassword"))
                .thenThrow(new EmptyResultDataAccessException(0));

        Assert.assertEquals(authorizationService.authorize("CorrectFirstName","CorrectLastName",
                "CorrectPassword"),existingUser);
        Assert.assertEquals(authorizationService.authorize("WrongFirstName","WrongLastName",
                "WrongPassword").getId(),substitutedUser.getId());

    }

}