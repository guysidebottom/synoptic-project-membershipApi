package com.springjpa.springpostgresjpa.repository;

import com.springjpa.springpostgresjpa.model.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findByEmailAddress(String email);
    String findByName(String name);
    Optional<EmployeeEntity> findByCardId(String cardId);
}
