
package edu.ap.group10.leapwebapp.capability;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import edu.ap.group10.leapwebapp.environment.Environment;

@Entity
public class Capability {

	// PROPERTIES
	// primary key
	@Id
	@GeneratedValue
	private Long id;

	// foreign keys
	// ===== TO-DO foreign key? =====
	/*
	 * @Column(name = "parent_id", nullable = false) private Integer parentId;
	 */

	@ManyToOne
	@JoinColumn(name = "environment_id")
	@JsonBackReference
	private Environment environment;

	// columns
	/*
	 * @Column(nullable = false) private Integer level;
	 */

	@Column(nullable = false)
	private String name;

	/*
	 * @Column(name = "pace_of_change")
	 * 
	 * @Enumerated(EnumType.STRING) private PaceOfChange paceOfChange;
	 * 
	 * @Enumerated(EnumType.STRING) private Tom tom;
	 * 
	 * @Column(name = "resources_quality") private Integer resourcesQuality;
	 * 
	 * @Column(name = "information_quality") private Double informationQuality;
	 * 
	 * @Column(name = "application_fit") private Double applicationFit;
	 */

	// CONSTRUCTORS
	public Capability() {
	}

	/*
	 * public Capability(String name) { this.setParentId(0); this.setLevel(1);
	 * this.name = name; this.setPaceOfChange(PaceOfChange.NONE);
	 * this.setTom(Tom.NONE); this.setResourcesQuality(1);
	 * this.setInformationQuality(0.0); this.setApplicationFit(0.0); // Set
	 * environment happens in POST controller }
	 * 
	 * public Capability(String name, PaceOfChange paceOfChange, Tom tom, Integer
	 * resourcesQuality) { this.name = name; this.paceOfChange = paceOfChange;
	 * this.tom = tom; this.resourcesQuality = resourcesQuality;
	 * this.setParentId(1); this.setInformationQuality(0.0);
	 * this.setApplicationFit(0.0); this.setLevel(1); }
	 */

	// GETTERS & SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * public Integer getParentId() { return parentId; }
	 * 
	 * public void setParentId(Integer parentId) { this.parentId = parentId; }
	 */

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	/*
	 * public Integer getLevel() { return level; }
	 * 
	 * public void setLevel(Integer level) { this.level = level; }
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * public PaceOfChange getPaceOfChange() { return paceOfChange; }
	 * 
	 * public void setPaceOfChange(PaceOfChange paceOfChange) { this.paceOfChange =
	 * paceOfChange; }
	 * 
	 * public Tom getTom() { return tom; }
	 * 
	 * public void setTom(Tom tom) { this.tom = tom; }
	 * 
	 * public Integer getResourcesQuality() { return resourcesQuality; }
	 * 
	 * public void setResourcesQuality(Integer resourcesQuality) {
	 * this.resourcesQuality = resourcesQuality; }
	 * 
	 * public Double getInformationQuality() { return informationQuality; }
	 * 
	 * public void setInformationQuality(Double informationQuality) {
	 * this.informationQuality = informationQuality; }
	 * 
	 * public Double getApplicationFit() { return applicationFit; }
	 * 
	 * public void setApplicationFit(Double applicationFit) { this.applicationFit =
	 * applicationFit; }
	 */

}
