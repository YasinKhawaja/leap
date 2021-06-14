package edu.ap.group10.leapwebapp.company;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import edu.ap.group10.leapwebapp.environment.Environment;
import lombok.Data;

@Data
@Entity
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false, name = "company_id")
    private Long id;

    @Column(nullable = false, name = "company_vat_number")
    private String vatNumber;
    @Column(nullable = false, name = "company_name")
    private String companyName;
    @Column(nullable = false, name = "company_email")
    private String email;
    @Column(nullable = false, name = "company_street_name")
    private String streetName;
    @Column(nullable = false, name = "company_house_number")
    private Integer houseNumber;
    @Column(nullable = false, name = "company_postcode")
    private Integer postcode;
    @Column(nullable = false, name = "company_city")
    private String city;
    @Column(nullable = false, name = "company_country")
    private String country;

    @Column(nullable = true, name = "company_business_activity")
    private String businessActivity;
    @Column(nullable = true, name = "company_tax_office")
    private String taxOffice;

    @Column(nullable = false, name = "company_approved")
    private Boolean approved;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "company_reference")
    private List<Environment> environments;

    public Company() {
    }

    public Company(String vatNumber, String companyName, String email, String streetName, Integer houseNumber,
            Integer postcode, String city, String country, String businessActivity, String taxOffice) {
        this.setVatNumber(vatNumber);
        this.setCompanyName(companyName);
        this.setEmail(email);
        this.setStreetName(streetName);
        this.setHouseNumber(houseNumber);
        this.setPostcode(postcode);
        this.setCity(city);
        this.setCountry(country);
        this.setTaxOffice(taxOffice);
        this.setBusinessActivity(businessActivity);
        this.setApproved(false);
    }

    public void setTaxOffice(String taxOffice) {
        if (taxOffice.equals("")) {
            taxOffice = null;
        }
        this.taxOffice = taxOffice;
    }

    public void setBusinessActivity(String businessActivity) {
        if (businessActivity.equals("")) {
            businessActivity = null;
        }
        this.businessActivity = businessActivity;
    }
}
