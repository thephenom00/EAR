package cvut.fel.vacation.dao;

import cvut.fel.vacation.model.Employee;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao extends BaseDao<Employee> {
    public EmployeeDao() {
        super(Employee.class);
    }

    public Employee findByUsername(String username) {
        try {
            return em.createNamedQuery("Employee.findByUsername", Employee.class).setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
