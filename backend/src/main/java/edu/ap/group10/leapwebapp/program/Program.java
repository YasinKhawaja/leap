package edu.ap.group10.leapwebapp.program;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.project.Project;
import lombok.Data;

@Data
@Entity
@Table(name = "Program")
public class Program implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false, name = "program_id")
    private Long id;

    @Column(nullable = false, unique = false, name = "program_name")
    private String name;

    @Column(nullable = true, unique = false, name = "program_description")
    private String description;

    @ManyToOne(targetEntity = Environment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "environment_id", nullable = false)
    @JsonBackReference(value = "environment_reference")
    private Environment environment;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "program_reference")
	private List<Project> projects; 

    public Program() {
    }

    public Program(String name, String description, Environment environment) {
        this.setName(name);
        this.setDescription(description);
        this.setEnvironment(environment);
    }

}
