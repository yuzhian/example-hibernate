package factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;

/**
 * @author yuzhian
 */
public class HibernateFactory {
    private static final SessionFactory factory;

    static {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        factory = configuration.buildSessionFactory();
    }

    private HibernateFactory() {
        throw new RuntimeException();
    }

    public static Session openSession() {
        return factory.openSession();
    }
}
