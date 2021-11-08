package com.example.MonthlySpendings.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface FrequencyRepository extends CrudRepository<Frequency, Long> {

    List<Frequency> findByName(String name);
    
}