package edu.ap.group10.leapwebapp.itapplication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.ap.group10.leapwebapp.environment.Environment;

@Entity
@Table(name="ITApplication")
public class ITApplication{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false, name = "application_id")
    private Long id;

    @Column(nullable = false, unique = true, name = "application_name")
    private String name;
    @Column(nullable = false, name = "application_technology")
    private String technology;
    @Column(nullable = false, name = "application_version")
    private String version;
    @Column(nullable = false, name = "application_acquisition_date")
    private String acquisitionDate;
    @Column(nullable = true, name = "application_end_of_life")
    private String endOfLife;
    @Column(nullable = false, name = "application_current_scalability")
    private Integer currentScalability;
    @Column(nullable = false, name = "application_expected_scalability")
    private Integer expectedScalability;
    @Column(nullable = false, name = "application_current_performance")
    private Integer currentPerformance;
    @Column(nullable = false, name = "application_expected_performance")
    private Integer expectedPerformance;
    @Column(nullable = false, name = "application_current_security_level")
    private Integer currentSecurityLevel;
    @Column(nullable = false, name = "application_expected_security_level")
    private Integer expectedSecurityLevel;
    @Column(nullable = false, name = "application_current_stability")
    private Integer currentStability;
    @Column(nullable = false, name = "application_expected_stability")
    private Integer expectedStability;
    // what's this?
    @Column(nullable = false, name = "application_cost_currency")
    private String costCurrency;
    @Column(nullable = false, name = "application_current_value_for_money")
    private Integer currentValueForMoney;
    @Column(nullable = false, name = "application_current_total_cost_per_year")
    private Double currentTotalCostPerYear;
    @Column(nullable = false, name = "application_tolerated_total_cost_per_year")
    private Double toleratedTotalCostPerYear;
    @Column(nullable = false, name = "application_time_value")
    @Enumerated(EnumType.STRING)
    private TimeValue timeValue;

    @ManyToOne(targetEntity = Environment.class, fetch = FetchType.EAGER)
    @JoinColumn(name="environment_id", nullable = false)
    private Environment environment;

    public ITApplication(){}

    public ITApplication(String name, String technology, String version, String acquisitionDate, String endOfLife, Integer currentScalability, Integer expectedScalability,
    Integer currentPerformance, Integer expectedPerformance, Integer currentSecurityLevel, Integer expectedSecurityLevel, Integer currentStability,
    Integer expectedStability, String costCurrency, Integer currentValueForMoney, Double currentTotalCostPerYear, Double toleratedTotalCostPerYear, TimeValue timeValue, Environment environment){
        this.setName(name);
        this.setTechnology(technology);
        this.setVersion(version);
        this.setAcquisitionDate(acquisitionDate);
        this.setEndOfLife(endOfLife);
        this.setCurrentScalability(currentScalability);
        this.setExpectedScalability(expectedScalability);
        this.setCurrentPerformance(currentPerformance);
        this.setExpectedPerformance(expectedPerformance);
        this.setCurrentSecurityLevel(currentSecurityLevel);
        this.setExpectedSecurityLevel(expectedSecurityLevel);
        this.setCurrentStability(currentStability);
        this.setExpectedStability(expectedStability);
        this.setCostCurrency(costCurrency);
        this.setCurrentValueForMoney(currentValueForMoney);
        this.setCurrentTotalCostPerYear(currentTotalCostPerYear);
        this.setToleratedTotalCostPerYear(toleratedTotalCostPerYear);
        this.setTimeValue(timeValue);
        this.setEnvironment(environment);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnology() {
        return this.technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAcquisitionDate() {
        return this.acquisitionDate;
    }

    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getEndOfLife() {
        return this.endOfLife;
    }

    public void setEndOfLife(String endOfLife) {
        this.endOfLife = endOfLife;
    }

    public Integer getCurrentScalability() {
        return this.currentScalability;
    }

    public void setCurrentScalability(Integer currentScalability) {
        this.currentScalability = currentScalability;
    }

    public Integer getExpectedScalability() {
        return this.expectedScalability;
    }

    public void setExpectedScalability(Integer expectedScalability) {
        this.expectedScalability = expectedScalability;
    }

    public Integer getCurrentPerformance() {
        return this.currentPerformance;
    }

    public void setCurrentPerformance(Integer currentPerformance) {
        this.currentPerformance = currentPerformance;
    }

    public Integer getExpectedPerformance() {
        return this.expectedPerformance;
    }

    public void setExpectedPerformance(Integer expectedPerformance) {
        this.expectedPerformance = expectedPerformance;
    }

    public Integer getCurrentSecurityLevel() {
        return this.currentSecurityLevel;
    }

    public void setCurrentSecurityLevel(Integer currentSecurityLevel) {
        this.currentSecurityLevel = currentSecurityLevel;
    }

    public Integer getExpectedSecurityLevel() {
        return this.expectedSecurityLevel;
    }

    public void setExpectedSecurityLevel(Integer expectedSecurityLevel) {
        this.expectedSecurityLevel = expectedSecurityLevel;
    }

    public Integer getCurrentStability() {
        return this.currentStability;
    }

    public void setCurrentStability(Integer currentStability) {
        this.currentStability = currentStability;
    }

    public Integer getExpectedStability() {
        return this.expectedStability;
    }

    public void setExpectedStability(Integer expectedStability) {
        this.expectedStability = expectedStability;
    }

    public String getCostCurrency() {
        return this.costCurrency;
    }

    public void setCostCurrency(String costCurrency) {
        this.costCurrency = costCurrency;
    }

    public Integer getCurrentValueForMoney() {
        return this.currentValueForMoney;
    }

    public void setCurrentValueForMoney(Integer currentValueForMoney) {
        this.currentValueForMoney = currentValueForMoney;
    }

    public Double getCurrentTotalCostPerYear() {
        return this.currentTotalCostPerYear;
    }

    public void setCurrentTotalCostPerYear(Double currentTotalCostPerYear) {
        this.currentTotalCostPerYear = currentTotalCostPerYear;
    }

    public Double getToleratedTotalCostPerYear() {
        return this.toleratedTotalCostPerYear;
    }

    public void setToleratedTotalCostPerYear(Double toleratedTotalCostPerYear) {
        this.toleratedTotalCostPerYear = toleratedTotalCostPerYear;
    }

    public TimeValue getTimeValue() {
        return this.timeValue;
    }

    public void setTimeValue(TimeValue timeValue) {
        this.timeValue = timeValue;
    }
    
    public Environment getEnvironment() {
        return this.environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
