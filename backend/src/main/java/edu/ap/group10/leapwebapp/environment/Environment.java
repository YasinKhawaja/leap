
package edu.ap.group10.leapwebapp.environment;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.company.Company;
import lombok.Data;

@Data
@Entity
public class Environment {

	@Id
	@GeneratedValue
	@Column(name = "environment_id")
	private Long id;

	@Column(name = "environment_name", nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "environment", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "environment_reference")
	private List<Capability> capabilities;

	@ManyToOne(targetEntity = Company.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	public Environment() {
	}

	public Environment(String name, Company company) {
		this.setName(name);
		this.setCompany(company);
	}
}
