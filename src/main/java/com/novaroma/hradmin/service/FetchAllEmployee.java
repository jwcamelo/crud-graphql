package com.novaroma.hradmin.service;

import com.novaroma.hradmin.model.Employee;
import com.novaroma.hradmin.repository.EmployeeRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FetchAllEmployee implements DataFetcher<List<Employee>> {

    @Autowired
    private EmployeeRepository repo;

    @Override
    public List<Employee> get(DataFetchingEnvironment environment) throws Exception {
        return repo.findAll();
    }
}
