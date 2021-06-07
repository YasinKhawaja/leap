package edu.ap.group10.leapwebapp.strategy;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.strategy_item.StrategyItem;
import lombok.Data;


@Entity
@Data
public class Strategy {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "strategy", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "strategyItems_reference")
	private List<StrategyItem> strategyItems;

	@ManyToOne
	@JoinColumn(name = "environment_id")
	@JsonBackReference(value = "strategy_reference")
	private Environment environment;


    
	@Column(name = "strategy_name", nullable = false, unique = true)
	private String name;


	@Column(name = "timeframe_from") 
	private String timeframeFrom;

	@Column(name = "timeframe_to") 
	private String timeframeTo;

    
	public Strategy() {
	}

    public Strategy(String name,String timeframeFrom,String timeframeTo , Environment environment) {
		this.setName(name);
		this.setTimeframeFrom(timeframeFrom);
		this.setTimeframeTo(timeframeTo);
		this.setEnvironment(environment);
	}
}
