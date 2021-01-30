package single;

import factory.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * @author yuzhian
 */
public class Tests {
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
    public void save() {
        Person person = new Person();
        person.setId(1);
        person.setName("张三");
        person.setBirthday(LocalDate.of(2000, 1, 1));
        session.saveOrUpdate(person);
    }

    @Test
    public void update() {
        Person person = new Person();
        person.setId(1);
        person.setName("李四");
        session.update(person);
    }

    @Test
    public void get() {
        Person person = session.get(Person.class, 1);
        // breakpoint
        System.out.println(person.getId() + " : " + person.getName() + " : " + person.getBirthday());
    }

    @Test
    public void load() {
        Person person = session.load(Person.class, 1);
        // breakpoint
        System.out.println(person.getId() + " : " + person.getName() + " : " + person.getBirthday());
    }

    @Test
    public void delete() {
        Person person = new Person();
        person.setId(1);
        session.delete(person);
    }
}
