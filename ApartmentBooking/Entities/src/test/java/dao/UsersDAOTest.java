package dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DbUnitConfiguration(databaseConnection = {"databaseDataSourceConnectionFactoryBean"})
@ContextConfiguration(classes = TestAppartmentConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class UsersDAOTest {


    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private User user;

    @Autowired
    private SimpleDateFormat format;

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/userTestData/dataSample")
    public void getByAuthorizationInfo() throws Exception {
        User user = usersDAO.getByAutorizationInfo("David", "Austin", "DAUSTIN");
        assertEquals(user.getFirstName(), "David");
        assertEquals(user.getLastName(), "Austin");
        assertEquals(user.getPassword(), "DAUSTIN");
        assertEquals(user.getEmail(), "serega_2401@tut.by");
    }


    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/userTestData/dataSample")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/userTestData/dataExpectedUpdate")
    public void update() throws Exception {
        User user = usersDAO.getByAutorizationInfo("David", "Austin", "DAUSTIN");
        user.setFirstName("John");
        user.setLastName("Smith");
        boolean isUpdated = usersDAO.update(user);
        assertEquals(isUpdated, true);
    }


    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/userTestData/dataSample")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/userTestData/dataExpectedCreate")
    public void create() throws Exception {
        user.setFirstName("New");
        user.setLastName("User");
        user.setPassword("User");
        user.setEmail("serega_2401@tut.by");
        Date date =  format.parse("1818-11-11");
        user.setBirthday(date);
        boolean isCreated = usersDAO.create(user);
        assertEquals(isCreated, true);

    }
}