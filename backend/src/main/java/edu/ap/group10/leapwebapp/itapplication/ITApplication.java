package edu.ap.group10.leapwebapp.itapplication;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import edu.ap.group10.leapwebapp.environment.Environment;
import lombok.Data;

@Data
@Entity
@Table(name = "ITApplication")
public class ITApplication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false, name = "application_id")
    private Long id;

    @Column(nullable = false, unique = false, name = "application_name")
    private String name;
    @Column(nullable = false, name = "application_technology")
    private String technology;
    @Column(nullable = true, name = "application_version")
    private String version;
    @Column(nullable = true, name = "application_acquisition_date")
    private String acquisitionDate;
    @Column(nullable = true, name = "application_end_of_life")
    private String endOfLife;
    @Column(nullable = true, name = "application_current_scalability")
    private Integer currentScalability;
    @Column(nullable = true, name = "application_expected_scalability")
    private Integer expectedScalability;
    @Column(nullable = true, name = "application_current_performance")
    private Integer currentPerformance;
    @Column(nullable = true, name = "application_expected_performance")
    private Integer expectedPerformance;
    @Column(nullable = true, name = "application_current_security_level")
    private Integer currentSecurityLevel;
    @Column(nullable = true, name = "application_expected_security_level")
    private Integer expectedSecurityLevel;
    @Column(nullable = true, name = "application_current_stability")
    private Integer currentStability;
    @Column(nullable = true, name = "application_expected_stability")
    private Integer expectedStability;
    @Column(nullable = true, name = "application_cost_currency")
    private String costCurrency;
    @Column(nullable = true, name = "application_current_value_for_money")
    private Integer currentValueForMoney;
    @Column(nullable = true, name = "application_current_total_cost_per_year")
    private Double currentTotalCostPerYear;
    @Column(nullable = true, name = "application_tolerated_total_cost_per_year")
    private Double toleratedTotalCostPerYear;
    @Column(nullable = true, name = "application_time_value")
    private String timeValue;

    @ManyToOne(targetEntity = Environment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "environment_id", nullable = false)
    @JsonBackReference(value = "environment_reference")
    private Environment environment;

    public ITApplication() {
    }

    public ITApplication(String name, String technology, Environment environment) {
        this.setName(name);
        this.setTechnology(technology);
        this.setEnvironment(environment);
        this.setVersion("version");
        this.setAcquisitionDate("acquisitionDate");
        this.setEndOfLife("endOfLife");
        this.setCurrentScalability(0);
        this.setExpectedScalability(0);
        this.setCurrentPerformance(0);
        this.setExpectedPerformance(0);
        this.setCurrentSecurityLevel(0);
        this.setExpectedSecurityLevel(0);
        this.setCurrentStability(0);
        this.setExpectedStability(0);
        this.setCostCurrency("Eur");
        this.setCurrentValueForMoney(0);
        this.setCurrentTotalCostPerYear(0.0);
        this.setToleratedTotalCostPerYear(0.0);
        this.setTimeValue("timeValue");
    }

    public ITApplication(String name, String technology, String version, String acquisitionDate, String endOfLife,
            Integer currentScalability, Integer expectedScalability, Integer currentPerformance,
            Integer expectedPerformance, Integer currentSecurityLevel, Integer expectedSecurityLevel,
            Integer currentStability, Integer expectedStability, String costCurrency, Integer currentValueForMoney,
            Double currentTotalCostPerYear, Double toleratedTotalCostPerYear, String timeValue,
            Environment environment) {
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

    public void setTimeValue(String timeValue) {
        this.timeValue = timeValue.toUpperCase();
    }
}
