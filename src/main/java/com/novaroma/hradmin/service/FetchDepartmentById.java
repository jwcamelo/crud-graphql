package com.novaroma.hradmin.service;

import com.novaroma.hradmin.model.Department;
import com.novaroma.hradmin.repository.DepartmentRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FetchDepartmentById implements DataFetcher<Department> {
    @Autowired
    private DepartmentRepository repo;

    @Override
    public Department get(DataFetchingEnvironment environment) throws Exception {
        return repo.findById(environment.getArgument("id")).get();
    }
}