package com.novaroma.hradmin.repository;

import com.novaroma.hradmin.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
