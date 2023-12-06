package cvut.fel.vacation.dao;

import cvut.fel.vacation.model.Emergency;
import org.springframework.stereotype.Repository;

@Repository
public class EmergencyDao extends BaseDao<Emergency>{
    public EmergencyDao(){
        super(Emergency.class);
    }
}
