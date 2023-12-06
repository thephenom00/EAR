package cvut.fel.vacation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="projectmanager")
public class ProjectManager extends Manager{
    @Column(name = "permission")
    private String permission;

}
