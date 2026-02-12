package com.employee.Repo;

import com.employee.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByUsername(String username);

}
