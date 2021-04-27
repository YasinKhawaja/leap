package edu.ap.group10.leapwebapp.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Capability {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;


    public Capability() {
    }

    public Capability(String name) {
        this.name = name;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
