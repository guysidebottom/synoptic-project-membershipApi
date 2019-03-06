package com.springjpa.springpostgresjpa.repository;

import com.springjpa.springpostgresjpa.model.Consumable;
import org.springframework.data.repository.CrudRepository;

public interface ConsumableRepository extends CrudRepository<Consumable, String> {

}
