
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
import lombok.Data;

@Entity
@Data
public class Capability {

	@Id
	@GeneratedValue
	@Column(name = "capability_id")
	private Long id;

	@Column(nullable = false)
	private Integer level;

	@Column(nullable = false)
	private String name;

	@Column(name = "pace_of_change", nullable = false)
	@Enumerated(EnumType.STRING)
	private PaceOfChange paceOfChange;

	@Column(name = "target_operation_model", nullable = false)
	@Enumerated(EnumType.STRING)
	private TargetOperationModel targetOperationModel;

	@Column(name = "resources_quality")
	private Integer resourcesQuality;

	@Column(name = "information_quality")
	private Double informationQuality;

	@Column(name = "application_fit")
	private Double applicationFit;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "parent_reference")
	private List<Capability> subcapabilities;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	@JsonBackReference(value = "parent_reference")
	private Capability parent;

	@ManyToOne
	@JoinColumn(name = "environment_id", nullable = false)
	@JsonBackReference(value = "environment_reference")
	private Environment environment;

	public Capability() {
	}

	public Capability(String name, Environment environment) {
		this.setEnvironment(environment); // Foreign key
		this.setParent(null); // Foreign key
		this.setLevel(1);
		this.name = name;
		this.setPaceOfChange(PaceOfChange.NONE);
		this.setTargetOperationModel(TargetOperationModel.NONE);
		this.setResourcesQuality(0);
		this.setInformationQuality(0.0);
		this.setApplicationFit(0.0);
	}

	public void setCalculatedInformationQuality(Integer completeness, Integer correctness, Integer availability,
			Double importanceFactor) {
		double calculatedInformationQuality;
		if (getInformationQuality() == null) {
			calculatedInformationQuality = (double) (completeness + correctness + availability) / 3 * importanceFactor;
		} else {
			calculatedInformationQuality = getInformationQuality()
					+ (double) (completeness + correctness + availability) / 3 * importanceFactor;
		}

		setInformationQuality(round(calculatedInformationQuality, 1));

	}

	public void setCalculatedApplicationFit(Integer efficiencySupport, Integer functionalCoverage, Integer correctness,
			Integer futurePotential, Double importanceFactor) {
		double calculatedApplicationFit;
		if (getApplicationFit() == null) {
			calculatedApplicationFit = (double) (efficiencySupport + functionalCoverage + correctness + futurePotential)
					/ 4 * importanceFactor;
		} else {
			calculatedApplicationFit = getApplicationFit()
					+ (double) (efficiencySupport + functionalCoverage + correctness + futurePotential) / 4
							* importanceFactor;
		}

		setApplicationFit(round(calculatedApplicationFit, 1));
	}

	private static double round(double value, int precision) {
		int scale = (int) Math.pow(10, precision);
		return (double) Math.round(value * scale) / scale;
	}

}
