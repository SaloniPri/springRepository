package com.employee.Repo;

import com.employee.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Employee,Long> {
}
