package edu.ap.group10.leapwebapp.capabilityapplication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.itapplication.ITApplication;

@Entity
@Table(name = "CapItApp")
public class CapabilityApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    ITApplication itApplications){
        setImportance(importance);
        setBusinessEfficiencySupport(businessEfficiencySupport);
        setBusinessFunctionalCoverage(businessFunctionalCoverage);
        setBusinessCorrectness(businessCorrectness);
        setBusinessFuturePotential(businessFuturePotential);
        setInformationCompleteness(informationCompleteness);
        setInformationCorrectness(informationCorrectness);
        setInformationAvailability(informationAvailability);
        setCapability(capability);
        setItApplications(itApplications);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getImportance() {
        return this.importance;
    }

    public void setImportance(Double importance) {
        this.importance = importance;
    }

    public Integer getBusinessEfficiencySupport() {
        return this.businessEfficiencySupport;
    }

    public void setBusinessEfficiencySupport(Integer businessEfficiencySupport) {
        this.businessEfficiencySupport = businessEfficiencySupport;
    }

    public Integer getBusinessFunctionalCoverage() {
        return this.businessFunctionalCoverage;
    }

    public void setBusinessFunctionalCoverage(Integer businessFunctionalCoverage) {
        this.businessFunctionalCoverage = businessFunctionalCoverage;
    }

    public Integer getBusinessCorrectness() {
        return this.businessCorrectness;
    }

    public void setBusinessCorrectness(Integer businessCorrectness) {
        this.businessCorrectness = businessCorrectness;
    }

    public Integer getBusinessFuturePotential() {
        return this.businessFuturePotential;
    }

    public void setBusinessFuturePotential(Integer businessFuturePotential) {
        this.businessFuturePotential = businessFuturePotential;
    }

    public Integer getInformationCompleteness() {
        return this.informationCompleteness;
    }

    public void setInformationCompleteness(Integer informationCompleteness) {
        this.informationCompleteness = informationCompleteness;
    }

    public Integer getInformationCorrectness() {
        return this.informationCorrectness;
    }

    public void setInformationCorrectness(Integer informationCorrectness) {
        this.informationCorrectness = informationCorrectness;
    }

    public Integer getInformationAvailability() {
        return this.informationAvailability;
    }

    public void setInformationAvailability(Integer informationAvailability) {
        this.informationAvailability = informationAvailability;
    }

    public Capability getCapability() {
        return this.capability;
    }

    public void setCapability(Capability capability) {
        this.capability = capability;
    }
    
    public ITApplication getItApplication() {
        return this.itApplication;
    }

    public void setItApplications(ITApplication itApplication) {
        this.itApplication = itApplication;
    }

}
