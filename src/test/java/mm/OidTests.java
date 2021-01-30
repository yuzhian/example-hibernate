package mm;

import factory.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ObjectMapperHolder;

import java.util.Collections;

/**
 * @author yuzhian
 */
public class OidTests {
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
    public void saveUser() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        Role role = new Role();
        role.setId(10);
        role.setName("用户");

        user.setRoles(Collections.singleton(role));
        session.save(user);
    }

    @Test
    public void saveRole() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        Role role = new Role();
        role.setId(10);
        role.setName("用户");

        role.setUsers(Collections.singleton(user));
        session.save(role);
    }

    @Test
    public void getUser() {
        ObjectMapperHolder.printValueAsString(session.get(User.class, 1));
    }

    @Test
    public void getRole() {
        ObjectMapperHolder.printValueAsString(session.get(Role.class, 10));
    }
}
