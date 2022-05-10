package com.example.spring1.dao;

import com.example.spring1.data.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployRepo extends JpaRepository<Employee,Integer> {
}
