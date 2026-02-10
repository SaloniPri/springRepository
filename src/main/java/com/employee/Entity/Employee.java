package com.employee.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;


    @Column(name="name",nullable = false,length = 50)
    public String name;
    @Column(name="mailid",nullable = false,unique = true)
    public String email;
    @Column(name="username",nullable = false,length = 50)
    public String username;
    @Column(name="password",nullable = false,length = 100)
    public String password;


}
