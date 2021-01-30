package om;

import factory.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ObjectMapperHolder;

import java.util.Arrays;
import java.util.HashSet;

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

    // 被控方, 无法保存子项
    @Test
    public void saveDept() {
        Department dept = new Department();
        dept.setId(1);
        dept.setName("dept");

        Employee e1 = new Employee();
        e1.setId(10);
        e1.setName("e1");
        Employee e2 = new Employee();
        e2.setId(20);
        e2.setName("e2");

        dept.setEmployees(new HashSet<>(Arrays.asList(e1, e2)));

        session.save(dept);
    }

    // 主控方
    @Test
    public void saveEmp() {
        Department dept = new Department();
        dept.setId(1);
        dept.setName("dept");

        Employee e1 = new Employee();
        e1.setId(10);
        e1.setName("e1");
        Employee e2 = new Employee();
        e2.setId(20);
        e2.setName("e2");

        e1.setDepartment(dept);
        e2.setDepartment(dept);

        session.save(e1);
        session.save(e2);
    }

    @Test
    public void getDept() {
        Department department = session.get(Department.class, 1);
        ObjectMapperHolder.printValueAsString(department);
    }

    @Test
    public void getEmp() {
        Employee employee = session.get(Employee.class, 10);
        ObjectMapperHolder.printValueAsString(employee);
    }
}
