package o;

import factory.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ObjectMapperHolder;

import java.util.List;

/**
 * @author yuzhian
 */
public class HqlTests {
    private Session session;
    private Transaction tx;

    @BeforeEach
    public void beforeEach() {
        session = HibernateFactory.openSession();
        tx = session.beginTransaction();
    }

    @AfterEach
    public void afterEach() {
        if (tx.isActive()) {
            tx.commit();
        }
        session.clear();
        session.close();
    }

    @Test
    public void get() {
        String hql = "select p from Person p where id = ?1";
        Person person = session.createQuery(hql, Person.class).setParameter(1, 1).uniqueResult();
        ObjectMapperHolder.printValueAsString(person);
    }

    @Test
    public void list() {
        String hql = "select p from Person p where name like ?1";
        List<Person> list = session.createQuery(hql, Person.class).setParameter(1, "张%").list();
        ObjectMapperHolder.printValueAsString(list);
    }

    @Test
    public void page() {
        String hql = "select p from Person p";
        List<Person> page = session.createQuery(hql, Person.class).setFirstResult(0).setMaxResults(3).getResultList();
        ObjectMapperHolder.printValueAsString(page);
    }
}
