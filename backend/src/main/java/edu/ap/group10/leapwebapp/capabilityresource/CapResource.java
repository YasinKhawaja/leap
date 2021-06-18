
package edu.ap.group10.leapwebapp.capabilityresource;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.resource.Resource;
import lombok.Data;

@Entity
@Data
public class CapResource implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "capresource_id")
    Long id;

    @OneToOne(targetEntity = Capability.class)
    @JoinColumn(name = "capability_id")
    private Capability capability;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @Column
    private Integer numberOfResources;

    public CapResource() {
    }

    public CapResource(Capability cap, Resource res, Integer numberOfResources) {
        this.capability = cap;
        this.resource = res;
        this.numberOfResources = numberOfResources;
    }

}
