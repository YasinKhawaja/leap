package edu.ap.group10.leapwebapp.capabilityinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.informationfqsfqs.Information;
import lombok.Data;

@Data
@Entity
@Table(name = "capinfo")
public class CapabilityInfo {

    @Id
    @Column(nullable = false, unique = true, updatable = false, name = "capinfo_id")
    private Long id;

    @Column(nullable = false, unique = false, updatable = true, name = "capinfo_criticality")
    private Criticality criticality;

    @OneToOne(targetEntity = Capability.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "capability_id", nullable = false)
    private Capability capability;

    @OneToOne(targetEntity = Information.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "businessprocess_id", nullable = false)
    private Information information;

    public CapabilityInfo() {
    }

    public CapabilityInfo(Capability capability, Information information, Criticality criticality) {
        setId(Long.parseLong(capability.getId().toString() + information.getId().toString()));
        setCapability(capability);
        setInformation(information);
        setCriticality(criticality);
    }
}
