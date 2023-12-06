package cvut.fel.vacation.utils;

import cvut.fel.vacation.model.Employee;
import cvut.fel.vacation.model.Role;
import cvut.fel.vacation.model.Team;

public class HelpMethod {
    public static Employee createEmployee() {
        Team team = new Team();
        team.setTeamName("Fnatic");

        Employee employee = new Employee();
        employee.setFirstName("Barack");
        employee.setLastName("Obama");
        employee.setUsername("Faker");
        employee.setPassword("Obamajeborec1");
        employee.setRole(Role.USER);
        employee.setTeam(team);

        return employee;
    }
}
