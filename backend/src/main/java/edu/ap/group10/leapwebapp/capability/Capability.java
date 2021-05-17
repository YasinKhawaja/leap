
package edu.ap.group10.leapwebapp.capability;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import edu.ap.group10.leapwebapp.environment.Environment;

@Entity
public class Capability {

	// PROPERTIES
	// primary key
	@Id
	@GeneratedValue
	private Long id;

	// columns
	@Column(nullable = false)
	private Integer level;

	@Column(nullable = false)
	private String name;

	@Column(name = "pace_of_change")
	@Enumerated(EnumType.STRING)
	private PaceOfChange paceOfChange;

	@Enumerated(EnumType.STRING)
	private Tom tom;

	@Column(name = "resources_quality")
	private Integer resourcesQuality;

	@Column(name = "information_quality")
	private Double informationQuality;

	@Column(name = "application_fit")
	private Double applicationFit;

	// foreign keys
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "parent_reference")
	private List<Capability> subcapabilities;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	@JsonBackReference(value = "parent_reference")
	private Capability parent;

	@ManyToOne
	@JoinColumn(name = "environment_id")
	@JsonBackReference(value = "environment_reference")
	private Environment environment;

	// CONSTRUCTORS
	public Capability() {
	}

	public Capability(String name, PaceOfChange paceOfChange, Tom tom, Integer resourcesQuality) {
		this.setParent(null); // Foreign key
		this.setEnvironment(null); // Foreign key
		this.setLevel(1);
		this.name = name;
		this.paceOfChange = paceOfChange;
		this.tom = tom;
		this.resourcesQuality = resourcesQuality;
		this.setInformationQuality(0.0);
		this.setApplicationFit(0.0);
	}

	// GETTERS & SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PaceOfChange getPaceOfChange() {
		return paceOfChange;
	}

	public void setPaceOfChange(PaceOfChange paceOfChange) {
		this.paceOfChange = paceOfChange;
	}

	public Tom getTom() {
		return tom;
	}

	public void setTom(Tom tom) {
		this.tom = tom;
	}

	public Integer getResourcesQuality() {
		return resourcesQuality;
	}

	public void setResourcesQuality(Integer resourcesQuality) {
		this.resourcesQuality = resourcesQuality;
	}

	public Double getInformationQuality() {
		return informationQuality;
	}

	public void setInformationQuality(Double informationQuality) {
		this.informationQuality = informationQuality;
	}

	public Double getApplicationFit() {
		return applicationFit;
	}

	public void setApplicationFit(Double applicationFit) {
		this.applicationFit = applicationFit;
	}

	public List<Capability> getSubcapabilities() {
		return subcapabilities;
	}

	public void setSubcapabilities(List<Capability> subcapabilities) {
		this.subcapabilities = subcapabilities;
	}

	public Capability getParent() {
		return parent;
	}

	public void setParent(Capability parent) {
		this.parent = parent;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
