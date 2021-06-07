package edu.ap.group10.leapwebapp.strategy_item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import edu.ap.group10.leapwebapp.strategy.Strategy;
import lombok.Data;

@Data
@Entity
public class StrategyItem {

	@Id
	@GeneratedValue
	@Column(name = "strategyitem_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "strategy_id")
	@JsonBackReference(value = "strategyitem_reference")
	private Strategy strategy;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	public StrategyItem() {
	}

	public StrategyItem(String name, String description) {
		this.name = name;
		this.description = description;
	}
}