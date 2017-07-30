package dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import entities.Apartment;
import entities.ApartmentLocation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@DbUnitConfiguration(databaseConnection = {"databaseDataSourceConnectionFactoryBean"})
@ContextConfiguration(classes = TestAppartmentConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class ApartmentsDAOTest {

    @Autowired
    private ApartmentsDAO apartmentsDAO;

    @Autowired
    private Apartment apartment;

    @Autowired
    private ApartmentLocation apartmentLocation;


    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/apartmentTestData/dataSample")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/apartmentTestData/dataExpectedUpdate")
    public void update() throws Exception {
        List<Apartment> apartments = apartmentsDAO.getByName("Blanchard");
        apartment = apartments.get(0);
        apartmentLocation = apartment.getLocation();
        apartmentLocation.setAddress("2011 Interiors Blvd");
        apartmentLocation.setCity("South San Francisco");
        apartmentLocation.setPostCode("34234");
        apartmentLocation.getCountry().setName("United States of America");
        apartment.setName("Sugar Land");
        apartment.setPricePerNight(45);
        apartment.setSleepingPlaces(2);
        apartment.setLocation(apartmentLocation);
        boolean isUpdated = apartmentsDAO.update(apartment);
        assertEquals(isUpdated, true);
    }


    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/apartmentTestData/dataSample")
    public void getByName() throws Exception {
        List<Apartment> apartments = apartmentsDAO.getByName("Blanchard");
        apartment = apartments.get(0);
        assertEquals(apartment.getName(), "Blanchard");
        assertEquals(apartment.getPricePerNight(), 67);
        assertEquals(apartment.getSleepingPlaces(), 3);
        assertEquals(apartment.getLocation().getAddress(), "2014 Jabberwocky Rd");
        assertEquals(apartment.getLocation().getCity(), "Southlake");
        assertEquals(apartment.getLocation().getPostCode(), "34234");
        assertEquals(apartment.getLocation().getCountry(), "United States of America");
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/apartmentTestData/dataSample")
    public void getByLocationAdress() throws Exception{
        List<Apartment> apartments = apartmentsDAO.getByLocationAdress("2014 Jabberwocky Rd");
        apartment = apartments.get(0);
        assertEquals(apartment.getName(), "Blanchard");
        assertEquals(apartment.getPricePerNight(), 67);
        assertEquals(apartment.getSleepingPlaces(), 3);
        assertEquals(apartment.getLocation().getAddress(), "2014 Jabberwocky Rd");
        assertEquals(apartment.getLocation().getCity(), "Southlake");
        assertEquals(apartment.getLocation().getPostCode(), "34234");
        assertEquals(apartment.getLocation().getCountry(), "United States of America");
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/apartmentTestData/dataSample")
    public void getAllWherePriceEqual() throws Exception {
        List<Apartment> apartments = apartmentsDAO.getAllWherePriceEqual(67);
       for(Apartment apartment:apartments) {
           assertEquals(apartment.getPricePerNight(), 67);
       }
    }

}