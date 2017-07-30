package dao;

import entities.Apartment;
import entities.ApartmentLocation;
import entities.Country;
import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ApartmentsDAO implements IDAO<Apartment, Long> {
    public static final String COUNRTY = "country";
    public static final String APARTMENT_LOCATION = "location";
    public static final String APARTMENT_ID = "id";
    public static final String APARTMENT_NAME = "name";
    public static final String LOCATION_ADRESS = "address";
    public static final String PPN = "pricePerNight";


    private SessionFactory sessionFactory;


    @Autowired
    public ApartmentsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    @Override
    @Transactional(readOnly = true)
    public Apartment getByKey(Long key) {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);

        Root<Apartment> apartmentRoot = criteriaQuery.from(Apartment.class);
        Root<ApartmentLocation> locationRoot = criteriaQuery.from(ApartmentLocation.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);

        Predicate countryApartmLoc = criteriaBuilder.equal(countryRoot,locationRoot.get(COUNRTY));
        Predicate apartmLocApartm =  criteriaBuilder.equal(locationRoot,apartmentRoot.get(APARTMENT_LOCATION));
        Predicate pkEquals = criteriaBuilder.equal(apartmentRoot.get(APARTMENT_ID),key);

        criteriaQuery.select(apartmentRoot).distinct(true).where(pkEquals,apartmLocApartm,countryApartmLoc);
        return session.createQuery(criteriaQuery).getSingleResult();

    }


    @Override
    @Transactional(readOnly = true)
    public List<Apartment> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);

        Root<Apartment> apartmentRoot = criteriaQuery.from(Apartment.class);
        Root<ApartmentLocation> locationRoot = criteriaQuery.from(ApartmentLocation.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);

        Predicate countryApartmLoc = criteriaBuilder.equal(countryRoot,locationRoot.get(COUNRTY));
        Predicate apartmLocApartm =  criteriaBuilder.equal(locationRoot,apartmentRoot.get(APARTMENT_LOCATION));

        criteriaQuery.select(apartmentRoot).distinct(true).where(apartmLocApartm,countryApartmLoc);
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional
    public boolean create(Apartment apartment) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);
        criteriaQuery.select(countryRoot).where(criteriaBuilder.equal(countryRoot.get("name"),apartment.getLocation().getCountry().getName()));
        Country country = session.createQuery(criteriaQuery).getSingleResult();
        apartment.getLocation().setCountry(country);
        sessionFactory.getCurrentSession().persist(apartment);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteByKey(Long key) {
        sessionFactory.getCurrentSession().remove(getByKey(key));
        return true;
    }

    @Override
    @Transactional
    public boolean update(Apartment apartment) {
        sessionFactory.getCurrentSession().merge(apartment);
        return false;
    }


    @Transactional(readOnly = true)
    public List<Apartment> getByName(String apartmentName) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);

        Root<Apartment> apartmentRoot = criteriaQuery.from(Apartment.class);
        Root<ApartmentLocation> locationRoot = criteriaQuery.from(ApartmentLocation.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);

        Predicate countryApartmLoc = criteriaBuilder.equal(countryRoot,locationRoot.get(COUNRTY));
        Predicate apartmLocApartm =  criteriaBuilder.equal(locationRoot,apartmentRoot.get(APARTMENT_LOCATION));
        Predicate nameEqual = criteriaBuilder.equal(apartmentRoot.get(APARTMENT_NAME),apartmentName);

        criteriaQuery.select(apartmentRoot).distinct(true).where(nameEqual,apartmLocApartm,countryApartmLoc);
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Apartment> getByLocationAdress(String locationAdress) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);

        Root<Apartment> apartmentRoot = criteriaQuery.from(Apartment.class);
        Root<ApartmentLocation> locationRoot = criteriaQuery.from(ApartmentLocation.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);

        Predicate countryApartmLoc = criteriaBuilder.equal(countryRoot,locationRoot.get(COUNRTY));
        Predicate apartmLocApartm =  criteriaBuilder.equal(locationRoot,apartmentRoot.get(APARTMENT_LOCATION));
        Predicate locatAddressEqual = criteriaBuilder.equal(locationRoot.get(LOCATION_ADRESS),locationAdress);

        criteriaQuery.select(apartmentRoot).distinct(true).where(locatAddressEqual,apartmLocApartm,countryApartmLoc);
        return session.createQuery(criteriaQuery).getResultList();
    }


    @Transactional(readOnly = true)
    public List<Apartment> getAllWherePriceLowerThen(int highBoundary) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);

        Root<Apartment> apartmentRoot = criteriaQuery.from(Apartment.class);
        Root<ApartmentLocation> locationRoot = criteriaQuery.from(ApartmentLocation.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);

        Predicate countryApartmLoc = criteriaBuilder.equal(countryRoot,locationRoot.get(COUNRTY));
        Predicate apartmLocApartm =  criteriaBuilder.equal(locationRoot,apartmentRoot.get(APARTMENT_LOCATION));
        Predicate nameEqual = criteriaBuilder.lessThanOrEqualTo(apartmentRoot.get(PPN),highBoundary);

        criteriaQuery.select(apartmentRoot).distinct(true).where(nameEqual,apartmLocApartm,countryApartmLoc);
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Apartment> getAllWherePriceHigherThen(int lowBoundary) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);

        Root<Apartment> apartmentRoot = criteriaQuery.from(Apartment.class);
        Root<ApartmentLocation> locationRoot = criteriaQuery.from(ApartmentLocation.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);

        Predicate countryApartmLoc = criteriaBuilder.equal(countryRoot,locationRoot.get(COUNRTY));
        Predicate apartmLocApartm =  criteriaBuilder.equal(locationRoot,apartmentRoot.get(APARTMENT_LOCATION));
        Predicate nameEqual = criteriaBuilder.greaterThanOrEqualTo(apartmentRoot.get(PPN),lowBoundary);

        criteriaQuery.select(apartmentRoot).distinct(true).where(nameEqual,apartmLocApartm,countryApartmLoc);
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Apartment> getAllWherePriceEqual(int price) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);

        Root<Apartment> apartmentRoot = criteriaQuery.from(Apartment.class);
        Root<ApartmentLocation> locationRoot = criteriaQuery.from(ApartmentLocation.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);

        Predicate countryApartmLoc = criteriaBuilder.equal(countryRoot,locationRoot.get(COUNRTY));
        Predicate apartmLocApartm =  criteriaBuilder.equal(locationRoot,apartmentRoot.get(APARTMENT_LOCATION));
        Predicate nameEqual = criteriaBuilder.equal(apartmentRoot.get(PPN),price);

        criteriaQuery.select(apartmentRoot).distinct(true).where(nameEqual,apartmLocApartm,countryApartmLoc);
        return session.createQuery(criteriaQuery).getResultList();
    }
}
