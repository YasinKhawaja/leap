
package edu.ap.group10.leapwebapp.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Resource {

    // PROPERTIES
    // primary key
    @Id
    @GeneratedValue
    @Column(name = "resource_id")
    private Long id;

    // columns
    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @Column
    private Integer fullTimeEquivalentYearlyValue;

    // CONSTRUCTORS
    public Resource() {
    }

    public Resource(String name, String desc, Integer fte) {
        this.name = name;
        this.description = desc;
        this.fullTimeEquivalentYearlyValue = fte;
    }

}
