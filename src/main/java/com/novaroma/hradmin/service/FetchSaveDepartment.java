package com.novaroma.hradmin.service;

import com.novaroma.hradmin.model.Department;
import com.novaroma.hradmin.repository.DepartmentRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Optional;

@Component
public class FetchSaveDepartment implements DataFetcher<Department> {
    @Autowired
    private DepartmentRepository repo;

    @Override
    public Department get(DataFetchingEnvironment environment) throws Exception {
        LinkedHashMap input = environment.getArgument("department");
        if(input.get("id")!=null){
            Integer id = Integer.parseInt(input.get("id").toString());
            Optional<Department> depOp = repo.findById(id);
            if(depOp.isPresent()) {
                depOp.get().setName(input.get("name").toString());
                depOp.get().setDescription(input.get("description").toString());
                return repo.save(depOp.get());
            }
        }
        Department dep = new Department();
        dep.setName(input.get("name").toString());
        dep.setDescription(input.get("description").toString());
        return repo.save(dep);
    }
}
