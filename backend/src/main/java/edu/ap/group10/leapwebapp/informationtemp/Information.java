package edu.ap.group10.leapwebapp.informationtemp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import edu.ap.group10.leapwebapp.environment.Environment;

import lombok.Data;

@Data
@Entity
@Table(name = "Information")
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false, name = "information_id")
    private Long id;

    @Column(nullable = false, unique = false, name = "information_name")
    private String name;

    @Column(nullable = true, unique = false, name = "information_description")
    private String description;

    @ManyToOne(targetEntity = Environment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "environment_id", nullable = false)
    @JsonBackReference(value = "environment_reference")
    private Environment environment;

    public Information() {
    }

    public Information(String name, String description, Environment environment) {
        this.setName(name);
        this.setDescription(description);
        this.setEnvironment(environment);
    }
}
