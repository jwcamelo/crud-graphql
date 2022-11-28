package com.novaroma.hradmin.service;

import com.novaroma.hradmin.model.Employee;
import com.novaroma.hradmin.repository.EmployeeRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class FetchSaveEmployee implements DataFetcher<Employee> {

    @Autowired
    private EmployeeRepository repo;

    @Override
    public Employee get(DataFetchingEnvironment environment) throws Exception {
        Employee emp = new Employee();
        LinkedHashMap input = (LinkedHashMap) environment.getArgument("emp");

        emp.setAge(Integer.parseInt(input.get("age").toString()));
        emp.setName(input.get("name").toString());
        emp.setDepartment(input.get("department").toString());

        return repo.save(emp);
    }
}
