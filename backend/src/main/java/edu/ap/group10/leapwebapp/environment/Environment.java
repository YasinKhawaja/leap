
package edu.ap.group10.leapwebapp.environment;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import edu.ap.group10.leapwebapp.capability.Capability;

@Entity
public class Environment {

	// PROPERTIES
	// primary key
	@Id
	@GeneratedValue
	private Long id;

	// columns
	@Column(name = "environment_name", nullable = false, unique = true)
	private String name;

	// bidirectional @OneToMany, foreign key in Capability
	@OneToMany(mappedBy = "environment", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "environment_reference")
	private List<Capability> capabilities;

	// CONSTRUCTORS
	public Environment() {
	}

	public Environment(String name) {
		this.setName(name);
	}

	// GETTERS & SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Capability> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(List<Capability> capabilities) {
		this.capabilities = capabilities;
	}

}
