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
public class Type {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long typeId;
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
	private List<Spending> spendings;
	
	public Type() {}
	
	public Type(String name) {
		super();
		this.name = name;
	}
	
	public Long getTypeId() {
		return typeId;
	}
	
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
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
		return "Type [typeId=" + typeId + ", name=" + name + "]";
	}
}