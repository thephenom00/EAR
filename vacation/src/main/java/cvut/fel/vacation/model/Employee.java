package cvut.fel.vacation.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "employee")
@NamedQueries({
        @NamedQuery(name = "Employee.findByUsername", query = "SELECT e FROM Employee e WHERE e.username = :username"),
})
public class Employee extends AbstractEntity {

    @Basic(optional = false)
    @Column(name="firstname", nullable = false)
    private String firstName;

    @Basic(optional = false)
    @Column(name="lastname", nullable = false)
    private String lastName;

    @Basic(optional = false)
    @Column(name="username", nullable = false)
    private String username;

    @Basic(optional = false)
    @Column(name="password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name="role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VacationRequest> requestList = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emergency> emergencies = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    public VacationRequest createVacationRequest(LocalDate from, LocalDate to, Manager manager) {
        // Checks if the request is valid
        for (VacationRequest request : requestList) {
            if (request.getTo() == to && request.getFrom() == from) {
                throw new IllegalArgumentException("There is already a vacation request with the same dates.");
            } else if (request.getFrom().isBefore(to) && request.getTo().isAfter(from)) {
                throw new IllegalArgumentException("There is already a vacation request that overlaps with the requested dates.");
            }
        }

        VacationRequest request = new VacationRequest();
        request.setFrom(from);
        request.setTo(to);
        request.setEmployee(this);
        request.setManager(manager);
        request.setState(State.PENDING);
        this.requestList.add(request);
        return request;
    }

    public Emergency createEmergency(LocalDate date) {
        // For one day only one emergency
        emergencyValidation(date);

        Emergency emergency = new Emergency();
        emergency.setDate(date);
        this.emergencies.add(emergency);
        return emergency;
    }

    public void removeVacationRequest(VacationRequest request) {
        if (requestList.contains(request)) {
            requestList.remove(request);
            request.setEmployee(null);
        }
    }

    public void removeEmergency(Emergency emergency) {
        if (emergencies.contains(emergency)) {
            emergencies.remove(emergency);
            emergency.setEmployee(null);
        }
    }

    public void emergencyValidation(LocalDate date) {
        for (Emergency emergency : emergencies) {
            if (emergency.getDate() == date) {
                throw new IllegalArgumentException("There is already an emergency for this day.");
            }
        }
    }
}
