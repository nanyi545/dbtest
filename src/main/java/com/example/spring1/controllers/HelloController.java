package com.example.spring1.controllers;

import com.example.spring1.dao.DbService;
import com.example.spring1.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }

}
