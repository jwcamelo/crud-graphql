package com.novaroma.hradmin.service;

import com.novaroma.hradmin.repository.DepartmentRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FetchDeleteDepartment implements DataFetcher<Boolean> {
    @Autowired
    private DepartmentRepository repo;

    @Override
    public Boolean get(DataFetchingEnvironment environment) throws Exception {
        if(!repo.findById(environment.getArgument("id")).isPresent()){
            return false;
        }
        repo.deleteById(environment.getArgument("id"));
        return true;
    }
}
