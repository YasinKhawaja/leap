package edu.ap.group10.leapwebapp.businessprocesses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.ap.group10.leapwebapp.environment.Environment;
import lombok.Data;

@Data
@Entity
@Table(name = "Business_Process")
public class BusinessProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false, name = "business_process_id")
    private Long id;

    @Column(nullable = false, unique = false, name = "business_process_name")
    private String name;

    @Column(nullable = true, unique = false, name = "business_process_description")
    private String description;

    @ManyToOne(targetEntity = Environment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "environment_id", nullable = false)
    private Environment environment;

    public BusinessProcess() {
    }

    public BusinessProcess(String name, String description, Environment environment) {
        this.setName(name);
        this.setDescription(description);
        this.setEnvironment(environment);
    }

}
