package com.example.MonthlySpendings.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Frequency {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long frequencyId;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "frequency")
	private List<Spending> spendings;
	
	public Frequency() {}
	
	public Frequency(String name) {
		super();
		this.name = name;
	}
	
	public Long getFrequencyId() {
		return frequencyId;
	}
	
	public void setFrequencyId(Long frequencyId) {
		this.frequencyId = frequencyId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Spending> getSpendings() {
		return spendings;
	}
	
	public void setSpendings(List<Spending> spendings) {
		this.spendings = spendings;
	}

	@Override
	public String toString() {
		return "Interval [id=" + frequencyId + ", name=" + name + "]";
	}
}