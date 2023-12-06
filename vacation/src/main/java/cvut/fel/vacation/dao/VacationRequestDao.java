package cvut.fel.vacation.dao;

import cvut.fel.vacation.model.VacationRequest;
import org.springframework.stereotype.Repository;

@Repository
public class VacationRequestDao extends BaseDao<VacationRequest>{
    public VacationRequestDao() {
        super(VacationRequest.class);
    }
}
