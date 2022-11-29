package com.novaroma.hradmin.controller;

import com.novaroma.hradmin.model.Department;
import com.novaroma.hradmin.model.Employee;
import com.novaroma.hradmin.repository.DepartmentRepository;
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
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeGraphQLService service;
    @Autowired
    private EmployeeRepository repo;
    @Autowired
    private DepartmentRepository depRepo;

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
        List<Department> depList = new ArrayList<>();
        Department itDep = new Department("IT", "It uses the latest tech to keep " +
                "communication lines running smoothly and protect critical data");
        Department hrDep = new Department("HR", "Responsible for managing the employee life cycle and administering employee benefits.");
        Department admDep = new Department("Administration", "provides administrative and technical support in the areas of human " +
                "resources (HR), budgetary, strategic planning, legal affairs, calls for tenders, " +
                "facilities and security.");

        depList.addAll(Arrays.asList(itDep,hrDep,admDep));
        depRepo.saveAll(depList);

        List<Employee> empList=new ArrayList<>();
        empList.add(new Employee(22, "Amit Reena", itDep));
        empList.add(new Employee(43, "Cejay McLamb", hrDep));
        empList.add(new Employee(18, "Sahari Patel", admDep));
        empList.add(new Employee(28, "Deville Sudhra", admDep));

        repo.saveAll(empList);
    }

}
