package com.employee.Service;


import com.employee.Entity.Employee;
import com.employee.Repo.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpService {
    @Autowired
    private EmpRepository empRepository;
    public Employee createUser1(Employee employee){
        return empRepository.save(employee);
    }
}
