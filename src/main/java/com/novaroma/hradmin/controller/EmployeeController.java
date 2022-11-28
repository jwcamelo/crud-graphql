package com.novaroma.hradmin.controller;

import com.novaroma.hradmin.model.Employee;
import com.novaroma.hradmin.repository.EmployeeRepository;
import com.novaroma.hradmin.service.EmployeeGraphQLService;
import graphql.ExecutionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeGraphQLService service;

    @Autowired
    private EmployeeRepository repo;

    @PostMapping
    public ResponseEntity<ExecutionResult> getAllEmployee(@RequestBody String query){
        try{
            ExecutionResult executionResult=service.getGraphQL().execute(query);
            return new ResponseEntity<>(executionResult, HttpStatus.OK);
        }catch (Exception e){
            LOGGER.info("error ocucured in method getAllEmployee : {}", e.getMessage());
            throw new RuntimeException(new Error((e.getMessage())));
        }
    }

    @PostConstruct
    private void saveEmployeeData(){
        List<Employee> empList=new ArrayList<>();
        empList.add(new Employee(22, "Amit Reena", "CS"));
        empList.add(new Employee(43, "Cejay McLamb", "CS"));
        empList.add(new Employee(18, "Sahari Patel", "IT"));
        empList.add(new Employee(28, "Deville Sudhra", "Mech"));

        repo.saveAll(empList);
    }

}
