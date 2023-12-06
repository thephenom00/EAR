package cvut.fel.vacation.service;

import cvut.fel.vacation.dao.EmergencyDao;
import cvut.fel.vacation.dao.EmployeeDao;
import cvut.fel.vacation.dao.ManagerDao;
import cvut.fel.vacation.dao.VacationRequestDao;
import cvut.fel.vacation.model.Emergency;
import cvut.fel.vacation.model.Employee;
import cvut.fel.vacation.model.Manager;
import cvut.fel.vacation.model.VacationRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    @Autowired
    private final VacationRequestDao vacationRequestDao;

    @Autowired
    private final EmergencyDao emergencyDao;

    @Transactional
    public VacationRequest createVacationRequest(Employee employee, LocalDate from, LocalDate to, Manager manager) {
        VacationRequest request = employee.createVacationRequest(from, to, manager);
        vacationRequestDao.persist(request);
        return request;
    }

    @Transactional
    public Emergency createEmergencyRequest(Employee employee, LocalDate date) {
        Emergency emergency = employee.createEmergency(date);
        emergencyDao.persist(emergency);
        return emergency;
    }
}
