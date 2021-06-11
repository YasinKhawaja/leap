package edu.ap.group10.leapwebapp.capilitybusinessprocess;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.ap.group10.leapwebapp.businessprocesses.BusinessProcess;
import edu.ap.group10.leapwebapp.capability.Capability;
import lombok.Data;

@Data
@Entity
@Table(name = "capbusprocess")
public class CapabilityBusinessProcess implements Serializable {

    @Id
    @Column(nullable = false, unique = true, updatable = false, name = "capbusprocess_id")
    private Long id;

    @OneToOne(targetEntity = Capability.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "capability_id", nullable = false)
    private Capability capability;

    @OneToOne(targetEntity = BusinessProcess.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "businessprocess_id", nullable = false)
    private BusinessProcess businessProcess;

    public CapabilityBusinessProcess() {
    }

    public CapabilityBusinessProcess(Capability capability, BusinessProcess businessProcess) {
        setId(Long.parseLong(capability.getId().toString() + businessProcess.getId().toString()));
        setCapability(capability);
        setBusinessProcess(businessProcess);
    }
}