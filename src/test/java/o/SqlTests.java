package o;

import factory.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ObjectMapperHolder;

/**
 * @author yuzhian
 */
public class SqlTests {
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
        Person person = session.createNativeQuery("select * from person where id = ?1", Person.class).setParameter(1, 1).uniqueResult();
        ObjectMapperHolder.printValueAsString(person);
    }
}
