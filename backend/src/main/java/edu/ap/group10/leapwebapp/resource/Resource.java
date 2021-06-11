
package edu.ap.group10.leapwebapp.resource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import edu.ap.group10.leapwebapp.environment.Environment;
import lombok.Data;

@Entity
@Data
public class Resource implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "resource_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private Integer fullTimeEquivalentYearlyValue;

    @ManyToOne(targetEntity = Environment.class)
    @JoinColumn(name = "environment_id", nullable = false)
    @JsonBackReference(value = "environment_reference")
    private Environment environment;

    public Resource() {
    }

    public Resource(String name, String description, Integer fullTimeEquivalentYearlyValue, Environment environment) {
        this.name = name;
        this.description = description;
        this.fullTimeEquivalentYearlyValue = fullTimeEquivalentYearlyValue;
        this.environment = environment;
    }

}
