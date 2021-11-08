package com.example.MonthlySpendings.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class Spending {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private double cost;
	
	@ManyToOne
	@JoinColumn(name = "typeId")
    private Type type;
	
	@ManyToOne
	@JoinColumn(name = "frequencyId")
	private Frequency frequency;
	
	public Spending() {}
	
	public Spending(String name, Type type, double cost, Frequency frequency) {
		super();
		this.name = name;
		this.type = type;
		this.cost = cost;
		this.frequency = frequency;
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
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public Frequency getFrequency() {
		return frequency;
	}
	
	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
	
	@Override
	public String toString() {
			return "Spending [id=" + id + ", name=" + name + ", type=" + this.type + ", cost=" + cost + ", frequency=" + this.frequency + "]";
	}
}
