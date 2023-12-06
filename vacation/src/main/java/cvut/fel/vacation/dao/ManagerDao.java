package cvut.fel.vacation.dao;

import cvut.fel.vacation.model.Manager;
import org.springframework.stereotype.Repository;

@Repository
public class ManagerDao extends BaseDao<Manager>{
    public ManagerDao() {
        super(Manager.class);
    }
}
