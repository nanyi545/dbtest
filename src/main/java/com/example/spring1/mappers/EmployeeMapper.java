package com.example.spring1.mappers;


import com.example.spring1.data.Employee;
import com.example.spring1.data.Office;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM employees")
    @Results({
            @Result(property = "employeeNumber", column = "employeeNumber"),
            @Result(property = "firstName", column = "firstName"),
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "jobTitle", column = "jobTitle"),
            @Result(property = "extension", column = "extension"),
            @Result(property = "officeCode", column = "officeCode"),
            @Result(property = "email", column = "email"),
            @Result(property = "reportsTo", column = "reportsTo")
    })
    List<Employee> getAll();

}
