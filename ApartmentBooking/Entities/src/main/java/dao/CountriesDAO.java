package dao;

import entities.Country;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class CountriesDAO implements IDAO<Country, Long> {
    public static final String COUNTRT_NAME ="name";
    public static final String COUNTRT_ID ="id";

    private SessionFactory sessionFactory;

    @Autowired
    public CountriesDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional(readOnly = true)
    public Country getByKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);
        criteriaQuery.select(countryRoot).where(criteriaBuilder.equal(countryRoot.get(COUNTRT_ID),id));
        Country country = session.createQuery(criteriaQuery).getSingleResult();
        Hibernate.initialize(country.getApartmentLocations());
        return country;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);
        criteriaQuery.select(countryRoot);
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional
    public boolean create(Country entity) {
        sessionFactory.getCurrentSession().persist(entity);
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
    public boolean update(Country entity) {
        sessionFactory.getCurrentSession().merge(entity);
        return false;
    }

    @Transactional(readOnly = true)
    public Country getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);
        criteriaQuery.select(countryRoot).where(criteriaBuilder.equal(countryRoot.get(COUNTRT_NAME),name));
        return session.createQuery(criteriaQuery).getSingleResult();
    }
}
