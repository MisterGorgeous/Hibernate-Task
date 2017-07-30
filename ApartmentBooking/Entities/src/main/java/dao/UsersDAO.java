package dao;

import com.sun.xml.internal.ws.api.server.SDDocument;
import entities.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class UsersDAO implements IDAO<User, Long> {
    public static final String USER_ID ="id";
    public static final String USER_FIS_NAME ="firstName";
    public static final String USER_LAST_NAME ="lastName";
    public static final String USER_PASSWORD ="password";


    private SessionFactory sessionFactory;

    @Autowired
    public UsersDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional(readOnly = true)
    public User getByKey(Long key) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get(USER_ID),key));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot);
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional
    public boolean create(User entity) {
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
    public boolean update(User entity) {
       sessionFactory.getCurrentSession().merge(entity);
       return false;
    }


    @Transactional(readOnly = true)
    public User getByAutorizationInfo(String firsName, String lastName, String password) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get(USER_FIS_NAME),firsName),criteriaBuilder
                .equal(userRoot.get(USER_LAST_NAME),lastName),criteriaBuilder.equal(userRoot.get(USER_PASSWORD),password));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Transactional(readOnly = true)
    public User getByName(String firsName, String lastName) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get(USER_FIS_NAME),firsName),criteriaBuilder
                .equal(userRoot.get(USER_LAST_NAME),lastName));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

}
