package edu.ap.group10.leapwebapp.capabilityproject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.project.Project;
import lombok.Data;

@Data
@Entity
@Table(name = "capability_project")
public class CapabilityProject implements Serializable {

    @Id
    @Column(nullable = false, unique = true, updatable = false, name = "capability_project_id")
    private Long id;

    @OneToOne(targetEntity = Capability.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "capability_id", nullable = false)
    private Capability capability;

    @OneToOne(targetEntity = Project.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public CapabilityProject() {
    }

    public CapabilityProject(Capability capability, Project project) {
        setId(Long.parseLong(capability.getId().toString() + project.getId().toString()));
        setCapability(capability);
        setProject(project);
    }
}
