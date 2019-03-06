package com.springjpa.springpostgresjpa.controller;

import com.springjpa.springpostgresjpa.model.Employee;
import com.springjpa.springpostgresjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    EmployeeRepository repository;

    @RequestMapping("/save")
    public String process() {
        repository.save(new Employee("Guy", "Sidebottom", 908098, 9809));

        return "Done";
    }

    @RequestMapping("/findall")
    public String findAll() {
        String result = "";

        for(Employee employee : repository.findAll()) {
            result += employee.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("findbyid")
    public String findById(@RequestParam("id") long id) {
        String result = "";
        result = repository.findById(id).toString();
        return result;
    }

    @RequestMapping("/findbyemail")
    public String findByEmailAddress(@RequestParam("email") String email) {
        String result = "";

        for(Employee employee : repository.findByEmailAddress(email)) {
            result += employee.toString() + "<br>";
        }

        return result;
    }
}
