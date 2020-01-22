package com.springjpa.springpostgresjpa.model;

import com.springjpa.springpostgresjpa.exception.EntityCreationException;
import com.springjpa.springpostgresjpa.exception.RecordNotFoundException;
import com.springjpa.springpostgresjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public EmployeeEntity getEmployeeById(int id) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = repository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException(String.format("No employee record exist for given id %s", id));
        }
    }

    public EmployeeEntity getEmployeeByCardId(String cardId) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = repository.findByCardId(cardId);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException(String.format("No employee record exist for given card id %s. Please register to use this service", cardId));
        }
    }

    public EmployeeEntity topUpBalance(String cardId, double amount) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = repository.findByCardId(cardId);

        if (employee.isPresent()) {
            EmployeeEntity returnedEntity = employee.get();
            if (returnedEntity.isLoggedIn()) {
                if (amount > 0 && amount <= 100) {
                    returnedEntity.setBalance(amount);
                    return returnedEntity;
                } else throw new EntityCreationException("Invalid amount, please try again");
            }
            throw new EntityCreationException("User is not authenticated");
        } else {
            throw new RecordNotFoundException(String.format("No employee record exist for given card id %s", cardId));
        }
    }

    public void setRepository(EmployeeRepository repository) {
        this.repository = repository;
    }
}
