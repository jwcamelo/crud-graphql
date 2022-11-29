package com.novaroma.hradmin.service;

import com.novaroma.hradmin.model.Department;
import com.novaroma.hradmin.repository.DepartmentRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FetchAllDepartment implements DataFetcher<List<Department>> {
    @Autowired
    private DepartmentRepository repo;

    @Override
    public List<Department> get(DataFetchingEnvironment environment) throws Exception {
        return repo.findAll();
    }
}
