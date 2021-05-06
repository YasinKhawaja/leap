
package edu.ap.group10.leapwebapp.environment;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import edu.ap.group10.leapwebapp.capability.Capability;

@Entity
public class Environment {

	// PROPERTIES
	// primary key
	@Id
	@GeneratedValue
	private Integer id;

	/*
	// one-to-many relationship, required for foreign key in Capability
	@OneToMany(mappedBy = "environment", cascade = CascadeType.ALL)
	private Set<Capability> capabilities;
	*/

	// columns
	@Column(name = "environment_name", nullable = false, unique = true)
	private String name;

	// CONSTRUCTORS
	public Environment() {
	}

	public Environment(String name) {
		this.setName(name);
	}

	// GETTERS & SETTERS
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*
	public Set<Capability> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(Set<Capability> capabilities) {
		this.capabilities = capabilities;
	}
	*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
