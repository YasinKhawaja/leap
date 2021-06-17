
package edu.ap.group10.leapwebapp.capability;

import java.io.Serializable;
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

/**
 * 
 * Represents the different building blocks of an environment.
 * 
 */
@Entity
@Data
public class Capability implements Serializable {

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

	/** 
	 *
	 * The property informationQuality of the capability gets calculated. This calculation takes place when an IT application
	 * gets linked to the capability. It takes the average of the 3 information quality properties (completeness, correctness, availability) of the linked IT application and than multiplies it with the importanceFactor of that IT application.
	 * 
	 *
	 * @param completeness Information quality property of CapabilityApplication
	 * @param correctness Information quality property of CapabilityApplication
	 * @param availability Information quality property of CapabilityApplication
	 * @param importanceFactor Importance property of CapabilityApplication
	 */
	public void setCalculatedInformationQuality(Integer completeness, Integer correctness, Integer availability,
			Double importanceFactor) {
		double calculatedInformationQuality;
		if (getInformationQuality() == null) {
			calculatedInformationQuality = (double) (completeness + correctness + availability) / 3 * (importanceFactor/100);
		} else {
			calculatedInformationQuality = getInformationQuality()
					+ (double) (completeness + correctness + availability) / 3 * (importanceFactor/100);
		}

		setInformationQuality(round(calculatedInformationQuality, 1));

	}
	
	/** 
	 * 
	 * 
	 * The property applicationFit of the capability gets calculated. This calculation takes place when an IT application
	 * gets linked to the capability. It takes the average of the 4 business fit properties (efficiencySupport, functionalCoverage
	 * correctness, futurePotential) of the linked IT application and than multiplies it with the importanceFactor of that IT application.
	 * 
	 *
	 * 
	 * @param efficiencySupport Business fit property of CapabilityApplication 
	 * @param functionalCoverage Business fit property of CapabilityApplication
	 * @param correctness Business fit property of CapabilityApplication
	 * @param futurePotential Business fit property of CapabilityApplication
	 * @param importanceFactor Importance property of CapabilityApplication
	 */
	public void setCalculatedApplicationFit(Integer efficiencySupport, Integer functionalCoverage, Integer correctness,
			Integer futurePotential, Double importanceFactor) {
		double calculatedApplicationFit;
		if (getApplicationFit() == null) {
			calculatedApplicationFit = (double) (efficiencySupport + functionalCoverage + correctness + futurePotential)
					/ 4 * (importanceFactor/100);
		} else {
			calculatedApplicationFit = getApplicationFit()
					+ (double) (efficiencySupport + functionalCoverage + correctness + futurePotential) / 4
							* (importanceFactor/100);
		}

		setApplicationFit(round(calculatedApplicationFit, 1));
	}

	
	/** 
	 * @param value The number that you want to round.
	 * @param precision The number of digits to the right of the decimal point of the value param.
	 * @return double The rounded number.
	 */
	private static double round(double value, int precision) {
		int scale = (int) Math.pow(10, precision);
		return (double) Math.round(value * scale) / scale;
	}

}
