package com.rest_jpa.rest_crud.repository;

import com.rest_jpa.rest_crud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long>{
}
