package com.example.MonthlySpendings.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SpendingRepository extends CrudRepository<Spending, Long> {

	List<Spending> findByName(String name);	    
}