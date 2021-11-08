package com.example.MonthlySpendings.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, Long> {

    List<Type> findByName(String name);
    
}