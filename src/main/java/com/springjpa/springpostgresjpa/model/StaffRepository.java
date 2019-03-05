package com.springjpa.springpostgresjpa.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StaffRepository extends CrudRepository<StaffMember, Long> {
    List<StaffMember> findByLastName(String lastName);
}
