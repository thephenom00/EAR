package cvut.fel.vacation.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "manager")
public class Manager extends AbstractEntity {
    @Basic(optional = false)
    @Column(name="firstname", nullable = false)
    private String firstName;

    @Basic(optional = false)
    @Column(name="lastname", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VacationRequest> requestList = new ArrayList<>();

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emergency> emergencies = new ArrayList<>();

    public void updateRequestState(VacationRequest request, State state) {
        for (VacationRequest oneRequest : requestList) {
            if (oneRequest == request) {
                oneRequest.setState(state);
            }
        }
    }

    public Emergency createEmergency(LocalDate date) {
        // For one day only one emergency
        emergencyValidation(date);

        Emergency emergency = new Emergency();
        emergency.setDate(date);
        this.emergencies.add(emergency);
        return emergency;
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
