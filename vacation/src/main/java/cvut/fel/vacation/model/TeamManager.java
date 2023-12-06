package cvut.fel.vacation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="teammanager")
public class TeamManager extends Manager {
    @Column(name = "permission")
    private String permission;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

}
