package com.springjpa.springpostgresjpa.repository;

import com.springjpa.springpostgresjpa.model.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByCardId(String cardId);
}
