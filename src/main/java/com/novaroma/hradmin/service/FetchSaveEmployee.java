package com.novaroma.hradmin.service;

import com.novaroma.hradmin.model.Department;
import com.novaroma.hradmin.model.Employee;
import com.novaroma.hradmin.repository.DepartmentRepository;
import com.novaroma.hradmin.repository.EmployeeRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Optional;

@Component
public class FetchSaveEmployee implements DataFetcher<Employee> {

    @Autowired
    private EmployeeRepository repo;
    @Autowired
    private DepartmentRepository depRepo;

    @Override
    public Employee get(DataFetchingEnvironment environment) throws Exception {
        LinkedHashMap input = (LinkedHashMap) environment.getArgument("employee");
        Integer idDep = Integer.parseInt(input.get("idDepartment").toString());
        Optional<Department> depOp = depRepo.findById(idDep);
        if(depOp.isPresent()){
            if(input.get("id")!= null){
                Integer id = Integer.parseInt(input.get("id").toString());
                Optional<Employee> empOp = repo.findById(id);
                if(empOp.isPresent()){
                    empOp.get().setAge(Integer.parseInt(input.get("age").toString()));
                    empOp.get().setName(input.get("name").toString());
                    empOp.get().setDepartment(depOp.get());
                    return repo.save(empOp.get());
                }
            }
            Employee emp = new Employee();
            emp.setAge(Integer.parseInt(input.get("age").toString()));
            emp.setName(input.get("name").toString());
            emp.setDepartment(depOp.get());
            return repo.save(emp);
        }else{
            throw new Exception("Department not found");
        }
    }
}
