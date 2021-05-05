
package edu.ap.group10.leapwebapp.environment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Environment {

	// PROPERTIES
	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "environment_name", nullable = false)
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
