package com.novaroma.hradmin.controller;

import com.novaroma.hradmin.repository.DepartmentRepository;
import com.novaroma.hradmin.service.DepartmentGraphQLService;
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

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private DepartmentGraphQLService service;
    @Autowired
    private DepartmentRepository repo;

    @PostMapping
    public ResponseEntity<ExecutionResult> getAllDepartment(@RequestBody String query){
        try{
            ExecutionResult executionResult=service.getGraphQL().execute(query);
            return new ResponseEntity<>(executionResult, HttpStatus.OK);
        }catch (Exception e){
            LOGGER.info("error ocucured in method getAllDepartment : {}", e.getMessage());
            throw new RuntimeException(new Error((e.getMessage())));
        }
    }
}
