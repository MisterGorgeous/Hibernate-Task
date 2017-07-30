import cache.ApartmentCache;
import dao.ApartmentsDAO;
import dao.CountriesDAO;
import dao.UsersDAO;
import aop.EmailAspect;
import entities.Apartment;
import entities.ApartmentLocation;
import entities.Country;
import entities.User;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import service.*;
import utills.SendEmail;

import java.text.SimpleDateFormat;


@Configuration
@PropertySource("classpath:connection")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
public class AppartmentConfiguration {

   @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(managerDataSource());
    }

    @Bean
    public DriverManagerDataSource managerDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource("jdbc:oracle:thin:@localhost:1521:orcl", "C##SIARHEI", "password");
        driverManagerDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        return driverManagerDataSource;
    }

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean(){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(managerDataSource());
        localSessionFactoryBean.setPackagesToScan(new String[]{"entities"});
        return localSessionFactoryBean;
    }



    @Bean
    public HibernateTransactionManager hibernateTransactionManager(){
        HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(localSessionFactoryBean().getObject());
        return htm;
    }

    @Bean
    public ApartmentCache apartmentCache(){return new ApartmentCache(apartmentsDAO());}

    @Bean
    public UsersDAO useresDAO() {
        return new UsersDAO(localSessionFactoryBean().getObject());
    }

    @Bean
    public CountriesDAO countriesDAO() {
        return new CountriesDAO(localSessionFactoryBean().getObject());
    }

    @Bean
    public ApartmentsDAO apartmentsDAO(){return new ApartmentsDAO(localSessionFactoryBean().getObject());}

    @Bean
    public AuthorizationService authorizationService() {
        return new AuthorizationService();
    }

    @Bean
    public SearchApartmentService searchApartmentService(){ return new SearchApartmentService();}

    @Bean
    public UserRegistretionService userRegistretionService(){return new UserRegistretionService();}

    @Bean
    public UserChangePersonalDataService userChangePersonalDataService(){return new UserChangePersonalDataService();}

    @Bean
    public UserChangingPasswordService userChangingPasswordService(){ return new UserChangingPasswordService();}

    @Bean
    public ShowApartmentsService showApartmentsService(){return new ShowApartmentsService();}

    @Bean
    public FilterApartmentService filterApartmentService(){return new FilterApartmentService();}

    @Bean
    public BookingApartmentService bookingApartmentService(){return new BookingApartmentService();}

    @Bean
    @Scope("prototype")
    public User user() {
        return new User();
    }

    @Bean
    @Scope("prototype")
    public Apartment apartment() {return  new Apartment();}

    @Bean
    @Scope("prototype")
    public ApartmentLocation apartmentLocation() {return  new ApartmentLocation();}

    @Bean
    @Scope("prototype")
    public Country country() {return  new Country();}

    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @Bean
    public SendEmail sendEmail(){
        return new SendEmail();
    }

    @Bean
    public EmailAspect emailAspect(){return new EmailAspect();}

}
