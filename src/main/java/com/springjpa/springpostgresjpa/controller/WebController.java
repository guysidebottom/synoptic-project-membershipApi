package com.springjpa.springpostgresjpa.controller;

import com.springjpa.springpostgresjpa.model.StaffMember;
import com.springjpa.springpostgresjpa.model.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    StaffRepository repository;

    @RequestMapping("/save")
    public String process() {
        repository.save(new StaffMember("Guy", "Sidebottom"));

        return "Done";
    }

    @RequestMapping("/findall")
    public String findAll() {
        String result = "";

        for(StaffMember staff : repository.findAll()) {
            result += staff.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("findbyid")
    public String findById(@RequestParam("id") long id) {
        String result = "";
        result = repository.findById(id).toString();
        return result;
    }

    @RequestMapping("/findbylastname")
    public String findByLastName(@RequestParam("lastname") String lastName) {
        String result = "";

        for(StaffMember staff : repository.findByLastName(lastName)) {
            result += staff.toString() + "<br>";
        }

        return result;
    }
}
