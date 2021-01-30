package o;

import factory.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ObjectMapperHolder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author yuzhian
 */
public class QbcTests {
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
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);
        query.select(root);
        query.where(builder.equal(root.get("id"), 1));
        Person person = session.unwrap(Session.class).createQuery(query).uniqueResult();
        ObjectMapperHolder.printValueAsString(person);
    }
}
