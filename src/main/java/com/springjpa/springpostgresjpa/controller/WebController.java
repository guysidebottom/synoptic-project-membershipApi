package com.springjpa.springpostgresjpa.controller;

import com.springjpa.springpostgresjpa.exception.EntityCreationException;
import com.springjpa.springpostgresjpa.exception.RecordNotFoundException;
import com.springjpa.springpostgresjpa.model.EmployeeEntity;
import com.springjpa.springpostgresjpa.model.EmployeeCard;
import com.springjpa.springpostgresjpa.model.EmployeeService;
import com.springjpa.springpostgresjpa.repository.EmployeeCardRepository;
import com.springjpa.springpostgresjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class WebController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeCardRepository employeeCardRepository;
    @Autowired
    EmployeeService service;

    // Welcome page
    @RequestMapping(value = "/welcome/{cardId}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public String welcomePage(@PathVariable String cardId) throws RecordNotFoundException {
        EmployeeEntity employee = service.getEmployeeByCardId(cardId);
        if(employee.getCardId().isEmpty()) {
            return new RecordNotFoundException("Unregistered card").toString();
        }
        return String.format("Welcome %s", employee.getName());
    }

    // GET employee by id
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeEntity> findEmployeeById(@PathVariable Long id) throws RecordNotFoundException {
        EmployeeEntity employee = service.getEmployeeById(id);
        return new ResponseEntity<>(employee, new HttpHeaders(), HttpStatus.OK);
    }

    // GET employee card by id
    @RequestMapping(value = "/employeecard/cardId", method = RequestMethod.GET)
    public ResponseEntity<EmployeeEntity> findCardById(@RequestParam ("card_id") String cardId) throws RecordNotFoundException {
        EmployeeEntity employee = service.getEmployeeByCardId(cardId);
        return new ResponseEntity<>(employee, new HttpHeaders(), HttpStatus.OK);
    }

    // create a new employee
    @RequestMapping(value = "/employee", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public EmployeeEntity newEmployee(@RequestBody EmployeeEntity newEmployee) throws EntityCreationException {
        if(newEmployee.getCardId() == null) {
            throw new EntityCreationException();
        }
        return employeeRepository.save(newEmployee);
    }

    // replace an existing employee
    @RequestMapping(value = "/employee/update/{id}", method = RequestMethod.PUT)
    public EmployeeEntity replaceEmployee(@RequestBody EmployeeEntity newEmployee, @PathVariable long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setCardId(newEmployee.getCardId());
                    employee.setEmailAddress(newEmployee.getEmailAddress());
                    employee.setPhoneNumber(newEmployee.getPhoneNumber());
                    employee.setPinNumber(newEmployee.getPinNumber());
                    return employeeRepository.save(newEmployee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }

}
