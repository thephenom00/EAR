package cvut.fel.vacation.dao;

import cvut.fel.vacation.model.ProjectManager;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectManagerDao extends BaseDao<ProjectManager>{
    public ProjectManagerDao() {
        super(ProjectManager.class);
    }
}
