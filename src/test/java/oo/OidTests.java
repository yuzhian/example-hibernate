package oo;

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

    // 主控方
    @Test
    public void saveBook() {
        Book book = new Book();
        book.setId(1);
        book.setName("book");
        Detail detail = new Detail();
        detail.setId(10);
        detail.setName("detail");

        book.setDetail(detail);

        session.saveOrUpdate(book);
    }

    // 被控方, 无法保存外键
    @Test
    public void saveDetail() {
        Book book = new Book();
        book.setId(1);
        book.setName("book");
        Detail detail = new Detail();
        detail.setId(10);
        detail.setName("detail");

        detail.setBook(book);

        session.saveOrUpdate(detail);
    }

    @Test
    public void getBook() {
        Book book = session.get(Book.class, 1);
        ObjectMapperHolder.printValueAsString(book);
    }

    @Test
    public void getDetail() {
        Detail detail = session.get(Detail.class, 10);
        // debug: book
        ObjectMapperHolder.printValueAsString(detail);
    }
}
