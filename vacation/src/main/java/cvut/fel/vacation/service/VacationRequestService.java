package cvut.fel.vacation.service;

import cvut.fel.vacation.dao.VacationRequestDao;
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
public class VacationRequestService {
    @Autowired
    private final VacationRequestDao vacationRequestDao;

    @Transactional
    public VacationRequest createVacationRequest(Employee employee, LocalDate from, LocalDate to, Manager manager) {
        VacationRequest request = employee.createVacationRequest(from, to, manager);
        vacationRequestDao.persist(request);
        return request;
    }


}
