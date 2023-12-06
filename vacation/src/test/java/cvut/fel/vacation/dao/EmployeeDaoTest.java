package cvut.fel.vacation.dao;

import cvut.fel.vacation.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

import static cvut.fel.vacation.utils.HelpMethod.createEmployee;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@ComponentScan(basePackageClasses = cvut.fel.vacation.VacationApplication.class)
public class EmployeeDaoTest {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private EmployeeDao sut;

    @Test
    public void findByUsernameReturnsPersonWithMatchingUsername() {
        final Employee employee = createEmployee();
        em.persist(employee);

        final Employee result = sut.findByUsername(employee.getUsername());
        assertNotNull(result);
        assertEquals(employee.getId(), result.getId());
    }


}
