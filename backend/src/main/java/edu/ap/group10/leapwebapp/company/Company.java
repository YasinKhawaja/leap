package edu.ap.group10.leapwebapp.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This makes a table out of this class
public class Company {
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
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVatNumber() {
        return this.vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxOffice() {
        return this.taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        if (taxOffice.equals("")) {
            taxOffice = null;
        }
        this.taxOffice = taxOffice;
    }

    public String getBusinessActivity() {
        return this.businessActivity;
    }

    public void setBusinessActivity(String businessActivity) {
        if (businessActivity.equals("")) {
            businessActivity = null;
        }
        this.businessActivity = businessActivity;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getHouseNumber() {
        return this.houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getPostcode() {
        return this.postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
