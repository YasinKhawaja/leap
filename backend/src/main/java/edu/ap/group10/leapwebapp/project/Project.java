package edu.ap.group10.leapwebapp.project;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.ap.group10.leapwebapp.program.Program;
import lombok.Data;

@Data
@Entity
@Table(name = "Project")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false, name = "project_id")
    private Long id;

    @Column(nullable = false, unique = false, name = "project_name")
    private String name;

    @Column(nullable = true, unique = false, name = "project_description")
    private String description;

    @OneToOne(targetEntity = Program.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    public Project() {
    }

    public Project(String name, String description, Program program) {
        this.setName(name);
        this.setDescription(description);
        this.setProgram(program);
    }

}
