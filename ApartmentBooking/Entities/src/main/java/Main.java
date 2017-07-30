
import cache.ApartmentCache;
import dao.ApartmentsDAO;
import dao.CountriesDAO;
import dao.UsersDAO;
import entities.Apartment;
import entities.ApartmentLocation;
import entities.Country;
import entities.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AuthorizationService;

import javax.jws.soap.SOAPBinding;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) throws ParseException {
        AnnotationConfigApplicationContext anConAppConn = new AnnotationConfigApplicationContext();
        anConAppConn.register(AppartmentConfiguration.class);
        anConAppConn.refresh();

      /*  AuthorizationService authorizationService = anConAppConn.getBean(AuthorizationService.class);


        */

        ApartmentCache cache = anConAppConn.getBean(ApartmentCache.class);

        ApartmentsDAO apartmentsDAO = anConAppConn.getBean(ApartmentsDAO.class);

       // List<Apartment> apartments = apartmentsDAO.getByName("Smith-Mayer");

        Apartment apartment12 = cache.getApartByName("Smith-Mayer");

        System.out.println();


        new Thread(()->{
            Apartment apartment1 = cache.getApartByName("Smith-Mayer");
            boolean res = cache.bookApart(apartment1,1);
            System.out.println(res);
        }).start();

        new Thread(()->{
            Apartment apartment1 = cache.getApartByName("Smith-Mayer");
            boolean res = cache.bookApart(apartment1,1);
            System.out.println(res);
        }).start();


        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Apartment apartment = apartmentsDAO.getByKey(366L);
        apartment.setIsBooked(0);
        apartmentsDAO.update(apartment);
        System.out.println();

      /*  Apartment apartment = anConAppConn.getBean(Apartment.class);
        ApartmentLocation apartmentLocation = anConAppConn.getBean(ApartmentLocation.class);
        Country country = anConAppConn.getBean(Country.class);
        apartmentLocation.setCountry(country);
        apartment.setLocation(apartmentLocation);

        country.setName("United States of America");
       // country.setId(0L);

        apartmentLocation.setAddress("765 Clem N");
        apartmentLocation.setCity("Singapore");
        apartmentLocation.setPostCode("53453");
        apartmentLocation.setCountry(country);
        apartmentLocation.setPhone("123-123-123");

        apartment.setName("Rio");
        apartment.setSleepingPlaces(2);
        apartment.setPricePerNight(34);*/

     //   apartmentsDAO.create(apartment);

      /*  CountriesDAO countriesDAO = anConAppConn.getBean(CountriesDAO.class);

        Country country1 = countriesDAO.getByKey(23L);

      //  apartment = apartmentsDAO.getByKey(3L);
        List<ApartmentLocation> locations = country1.getApartmentLocations();
        ApartmentLocation location = locations.get(1);*/
        System.out.println();


       // List<Apartment> apartments  = apartmentsDAO.getAll();
        //UsersDAO usersDAO = anConAppConn.getBean(UsersDAO.class);
        //List<User> users = usersDAO.getAll();


       /* User newUser = anConAppConn.getBean(User.class);
        newUser.setFirstName("Siarhei");
        newUser.setLastName("Slabadniak");
        newUser.setEmail("serege_2401@tut.by");
        newUser.setPassword("Siarhei");
       // SimpleDateFormat format = anConAppConn.getBean(SimpleDateFormat.class);
        Date date =  new Date(10000);
        newUser.setBirthday(date);

        usersDAO.create(newUser);

        System.out.println();

        usersDAO.deleteByKey(usersDAO.getByName("Siarhei","Slabadniak").getId());


        System.out.println();*/
     //   User user = authorizationService.authorize("","","");

       // System.out.println(user.getId());

        //ApartmentsDAO apartmentsDAO = anConAppConn.getBean(ApartmentsDAO.class);

       /* Apartment apartment = anConAppConn.getBean(Apartment.class);

        ApartmentLocation location = anConAppConn.getBean(ApartmentLocation.class);

        Country country = anConAppConn.getBean(Country.class);

        country.setName("United States of America");
        country.setId("US");

        location.setAddress("765 Clem N");
        location.setCity("Singapore");
        location.setPostCode("53453");
        location.setCountry(country);

        apartment.setName("Rio");
        apartment.setSleepingPlaces(2);
        apartment.setPricePerNight(34);
        apartment.setLocation(location);

        apartmentsDAO.create(apartment);*/

      //  apartmentsDAO.deleteByKey(24L);

        /*SearchApartmentService searchApartmentService = anConAppConn.getBean(SearchApartmentService.class);
        List<Apartment> apartments = searchApartmentService.searchByLocationAdress("1298 Vileparle (E)");*/

       /* FilterApartmentService filterApartmentService = anConAppConn.getBean(FilterApartmentService.class);
        List<Apartment> apartments = filterApartmentService.filterByPrice(200, SearchParametrs.LOWER);*/


      // User user = authorizationService.authorize("Siarhei", "Slabadniak", "Siarhei");
      //  usersDAO.deleteByKey(user.getId());
       // System.out.println(user.getId());


       /* UserChangingPasswordService userChangingPasswordService = anConAppConn.getBean(UserChangingPasswordService.class);
       boolean res =  userChangingPasswordService.changeUserPassword("", "", "","");
       System.out.println(res);
       res = userChangingPasswordService.changeUserPassword("Steven", "King", "New","New");
       System.out.println(res);*/
    }
}
