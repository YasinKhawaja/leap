package edu.ap.group10.leapwebapp.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//add country as enum here?
import edu.ap.group10.leapwebapp.company.Country;

@Entity // This makes a table out of this class
public class Company {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(nullable = false)
    private String vatNumber;
    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String streetName;
    @Column(nullable = false)
    private Integer houseNumber;
    @Column(nullable = false)
    private Integer postcode;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;

    private String businessActivity;
    private String taxOffice;

    public Company(){
        
    }

    public Company(String vatNumber, String companyName, String email, String streetName, Integer houseNumber, Integer postcode, String city, String country, String businessActivity, String taxOffice)
    {
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
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
        if(taxOffice == "") {
        	taxOffice = null;
        }
        this.taxOffice = taxOffice;
    }

    public String getBusinessActivity() {
        return this.businessActivity;
    }

    public void setBusinessActivity(String businessActivity) {
    	if(businessActivity == "") {
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
