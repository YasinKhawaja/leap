
package edu.ap.group10.leapwebapp.capability;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Capability {

	// PROPERTIES
	// primary key
	@Id
	@GeneratedValue
	private Integer id;

	// ===== TO-DO foreign key? =====
	@Column(name = "parent_id", nullable = false)
	private Integer parentId;

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

	// CONSTRUCTORS
	public Capability() {
	}

	public Capability(String name) {
		this.setParentId(0);
		this.setLevel(1);
		this.name = name;
		this.setPaceOfChange(PaceOfChange.NONE);
		this.setTom(Tom.NONE);
		this.setResourcesQuality(1);
		this.setInformationQuality(0.0);
		this.setApplicationFit(0.0);
	}

	// GETTERS & SETTERS
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

}
