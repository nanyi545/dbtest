package com.example.spring1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbService {

    @Autowired
    private EmployRepo employRepo;

    public EmployRepo getEmployRepo() {
        return employRepo;
    }

}
