package cvut.fel.vacation.dao;

import cvut.fel.vacation.model.Employee;
import cvut.fel.vacation.model.Role;
import cvut.fel.vacation.model.Team;
import cvut.fel.vacation.utils.HelpMethod;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static cvut.fel.vacation.utils.HelpMethod.createEmployee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ComponentScan(basePackageClasses = cvut.fel.vacation.VacationApplication.class)
public class BaseDaoTest {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private EmployeeDao sut;

    @Test
    void persistSavesSpecifiedInstance() {
        Employee employee = createEmployee();

        sut.persist(employee);
        assertNotNull(employee.getId());

        final Employee result = em.find(Employee.class, employee.getId());
        assertNotNull(result);
        assertEquals(employee.getId(), result.getId());
        assertEquals(employee.getFirstName(), result.getFirstName());
        assertEquals(employee.getLastName(), result.getLastName());
    }

    @Test
    public void findRetrievesInstanceByIdentifier() {
        final Employee employee = createEmployee();
        em.persist(employee);
        em.flush();
        assertNotNull(employee.getId());

        final Employee result = sut.find(employee.getId());
        assertNotNull(result);
        assertEquals(employee.getId(), result.getId());
        assertEquals(employee.getFirstName(), result.getFirstName());
    }

    @Test
    public void findAllRetrievesAllInstancesOfType() {
        final Employee employee1 = createEmployee();
        final Employee employee2 = createEmployee();
        em.persistAndFlush(employee1);
        em.persistAndFlush(employee2);

        final List<Employee> result = sut.findAll();
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(c -> c.getId().equals(employee1.getId())));
        assertTrue(result.stream().anyMatch(c -> c.getId().equals(employee2.getId())));
    }

    @Test
    public void updateUpdatesExistingInstance() {
        final String firstName = "Donald";

        final Employee employee = createEmployee();
        em.persistAndFlush(employee);

        final Employee updatedEmployee = new Employee();
        updatedEmployee.setId(employee.getId());
        updatedEmployee.setFirstName(firstName);
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setUsername(employee.getUsername());
        updatedEmployee.setPassword(employee.getPassword());
        updatedEmployee.setTeam(employee.getTeam());
        updatedEmployee.setRole(employee.getRole());
        sut.update(updatedEmployee);

        final Employee result = sut.find(employee.getId());
        assertNotNull(result);
        assertEquals(firstName, result.getFirstName());
        assertEquals(employee.getId(), result.getId());

        assertThat(sut.findAll().size()).isEqualTo(1);
    }

    @Test
    public void removeRemovesSpecifiedInstance() {
        final Employee employee = createEmployee();
        em.persistAndFlush(employee);
        assertNotNull(em.find(Employee.class, employee.getId()));

        sut.remove(employee);
        assertNull(em.find(Employee.class, employee.getId()));
        assertThat(sut.findAll()).isEmpty();
    }

    @Test
    public void removeDoesNothingWhenInstanceDoesNotExist() {
        final Employee employee = createEmployee();
        employee.setId(123);
        assertNull(em.find(Employee.class, employee.getId()));

        sut.remove(employee);
        assertNull(em.find(Employee.class, employee.getId()));
    }

    @Test
    public void exceptionOnPersistInWrappedInPersistenceException() {
        final Employee employee = createEmployee();
        em.persistAndFlush(employee);
        em.remove(employee);
        assertThrows(PersistenceException.class, () -> sut.update(employee));
    }

    @Test
    public void existsReturnsTrueForExistingIdentifier() {
        final Employee employee = createEmployee();
        em.persistAndFlush(employee);
        assertTrue(sut.exists(employee.getId()));
        assertFalse(sut.exists(-1));
    }

}
