package com.springjpa.springpostgresjpa.repository;

import com.springjpa.springpostgresjpa.model.EmployeeCard;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeCardRepository extends CrudRepository<EmployeeCard, Long> {

}
