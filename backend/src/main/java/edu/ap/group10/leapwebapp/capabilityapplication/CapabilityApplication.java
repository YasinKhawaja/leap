package edu.ap.group10.leapwebapp.capabilityapplication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.itapplication.ITApplication;
import lombok.Data;

@Data
@Entity
@Table(name = "CapItApp")
public class CapabilityApplication {
    
    @Id
    @Column(nullable = false, unique = true, updatable =false, name="capitapp_id")
    private Long id;

    @Column(nullable = false, name = "capitapp_importance")
    private Double importance;
    @Column(nullable = false, name = "capitapp_business_fit_efficiency_support")
    private Integer businessEfficiencySupport;
    @Column(nullable = false, name = "capitapp_business_fit_functional_coverage")
    private Integer businessFunctionalCoverage;
    @Column(nullable = false, name = "capitapp_business_fit_correctness")
    private Integer businessCorrectness;
    @Column(nullable = false, name = "capitapp_business_fit_future_potential")
    private Integer businessFuturePotential;
    @Column(nullable = false, name = "capitapp_information_quality_completeness")
    private Integer informationCompleteness;
    @Column(nullable = false, name = "capitapp_information_quality_correctness")
    private Integer informationCorrectness;
    @Column(nullable = false, name = "capitapp_information_quality_availability")
    private Integer informationAvailability;
    
    @OneToOne(targetEntity = Capability.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "capability_id", nullable = false)
    private Capability capability;

    @OneToOne(targetEntity = ITApplication.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id", nullable = false)
    private ITApplication itApplication;

    public CapabilityApplication(){}

    public CapabilityApplication(Double importance, Integer businessEfficiencySupport, Integer businessFunctionalCoverage, Integer businessCorrectness,
    Integer businessFuturePotential, Integer informationCompleteness, Integer informationCorrectness, Integer informationAvailability, Capability capability,
    ITApplication itApplication){
        setId(Long.parseLong(itApplication.getId().toString() + capability.getId().toString()));
        setImportance(importance);
        setBusinessEfficiencySupport(businessEfficiencySupport);
        setBusinessFunctionalCoverage(businessFunctionalCoverage);
        setBusinessCorrectness(businessCorrectness);
        setBusinessFuturePotential(businessFuturePotential);
        setInformationCompleteness(informationCompleteness);
        setInformationCorrectness(informationCorrectness);
        setInformationAvailability(informationAvailability);
        setCapability(capability);
        setItApplication(itApplication);
    }
}
