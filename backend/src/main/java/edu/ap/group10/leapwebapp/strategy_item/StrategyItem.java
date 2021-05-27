package edu.ap.group10.leapwebapp.strategy_item;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import edu.ap.group10.leapwebapp.strategy.Strategy;

@Entity
public class StrategyItem {

	@Id
	@GeneratedValue
	private Long id;


    @ManyToOne
	@JoinColumn(name = "strategy_id")
	@JsonBackReference(value = "strategyItems_reference")
	private Strategy strategy;


	@Column(nullable = false)
	private String name;

    @Column(nullable = false)
	private String description;

	public StrategyItem() {
	}

    public StrategyItem(String name, String description) {
		this.name = name;
        this.description=description;
	}

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

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
}