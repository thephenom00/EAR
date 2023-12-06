package cvut.fel.vacation.dao;

import cvut.fel.vacation.model.Team;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDao extends BaseDao<Team>{
    public TeamDao() {
        super(Team.class);
    }
}
