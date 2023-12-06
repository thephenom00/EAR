package cvut.fel.vacation.service;

import cvut.fel.vacation.dao.ManagerDao;
import cvut.fel.vacation.dao.VacationRequestDao;
import cvut.fel.vacation.model.Manager;
import cvut.fel.vacation.model.State;
import cvut.fel.vacation.model.VacationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManagerService {
    @Autowired
    private final ManagerDao managerDao;

    @Autowired
    private final VacationRequestDao vacationRequestDao;

    public void updateRequestState(Manager manager, VacationRequest request, State state){
        manager.updateRequestState(request, state);
        vacationRequestDao.update(request);
    }

}
