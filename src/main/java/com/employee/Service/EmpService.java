package com.employee.Service;


import com.employee.Entity.Employee;
import com.employee.Repo.EmpRepository;
import com.employee.dto.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpService {
    @Autowired
    private EmpRepository empRepository;
    public Employee createUser1(Employee employee){
        return empRepository.save(employee);
    }
    public String loginAttempt(Login login ){
        Optional<Employee> optionalEmployee=empRepository.findByUsername(login.getUsername());
        if(optionalEmployee.isPresent()){
            Employee employee=optionalEmployee.get();
            if(employee.getPassword().equals(login.getPassword()))
                return "login success";

        }
        return "invalid credentials";

    }



}
