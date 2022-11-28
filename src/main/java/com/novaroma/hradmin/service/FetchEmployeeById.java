package com.novaroma.hradmin.service;

import com.novaroma.hradmin.model.Employee;
import com.novaroma.hradmin.repository.EmployeeRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FetchEmployeeById implements DataFetcher<Employee> {

    @Autowired
    private EmployeeRepository repo;

    @Override
    public Employee get(DataFetchingEnvironment environment) throws Exception {
        return repo.findById(environment.getArgument("id")).get();
    }
}
