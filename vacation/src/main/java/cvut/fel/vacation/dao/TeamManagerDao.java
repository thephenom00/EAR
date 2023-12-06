package cvut.fel.vacation.dao;

import cvut.fel.vacation.model.TeamManager;
import org.springframework.stereotype.Repository;

@Repository
public class TeamManagerDao extends BaseDao<TeamManager>{
    public TeamManagerDao() {
        super(TeamManager.class);
    }
}
