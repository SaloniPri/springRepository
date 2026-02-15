package com.employee.Controller;

import com.employee.Entity.Employee;
import com.employee.Service.EmpService;
import com.employee.dto.JWTtoken;
import com.employee.dto.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/employee")
public class EmpController {
    private final EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<Employee> createUser(@RequestBody Employee employee )
    {
        Employee employee1=empService.createUser1(employee);
        return new ResponseEntity<>(employee1, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginAttempt1(@RequestBody Login login){
//        String employee2=empService.loginAttempt(login);
//        return  employee2;
          String token=empService.loginAttempt(login);
        JWTtoken jwTtoken=new JWTtoken();
        if(token != null) {
            jwTtoken.setTokentype("JWT");
            jwTtoken.setToken(token);
            return new ResponseEntity<>(jwTtoken, HttpStatus.OK);
        }
        else{
            return  new ResponseEntity<>("Invalid Credentials",HttpStatus.UNAUTHORIZED);
        }


        }

    }



