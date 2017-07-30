package dao;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import entities.Apartment;
import entities.ApartmentLocation;
import entities.User;
import org.springframework.beans.factory.annotation.Value;
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
public class TestAppartmentConfiguration {

    @Value("${schemaUrl}")
    private String url;

    @Value("${testUsername}")
    private String schema;

    @Value("${testUserPassword}")
    private String schemaPassword;

    @Value("${driverClass}")
    private String driverClass;

    @Bean
    public DatabaseDataSourceConnectionFactoryBean databaseDataSourceConnectionFactoryBean(){
        DatabaseDataSourceConnectionFactoryBean databaseData = new DatabaseDataSourceConnectionFactoryBean(managerDataSource());
        databaseData.setDatabaseConfig(databaseConfigBean());
        return databaseData;
    }



    @Bean
    public DatabaseConfigBean databaseConfigBean(){
        DatabaseConfigBean databaseConfigBean =  new DatabaseConfigBean();
        databaseConfigBean.setQualifiedTableNames(true);
        return databaseConfigBean;
    }




    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(managerDataSource());
    }



    @Bean
    public DriverManagerDataSource managerDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource(url, schema,schemaPassword);
        driverManagerDataSource.setDriverClassName(driverClass);
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
    public UsersDAO useresDAO() {
        return new UsersDAO(localSessionFactoryBean().getObject());
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

    @Bean FilterApartmentService filterApartmentService(){return new FilterApartmentService();}

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
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @Bean
    public SendEmail sendEmail(){
        return new SendEmail();
    }

}
