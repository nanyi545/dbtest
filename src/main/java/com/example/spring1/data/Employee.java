package com.example.spring1.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Getter
@Setter
@Entity                                                   //@Entity  实体类
@Table(name = "employees")                                //说明这是一个表
public class Employee {

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber=" + employeeNumber +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", reportsTo=" + reportsTo +
                "}\n";
    }

    // https://www.baeldung.com/jpa-strategies-when-set-primary-key
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "employeeNumber")
    private int employeeNumber;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "jobTitle")
    private String jobTitle;

    @Column(name = "extension")
    private String extension="";

    @Column(name = "email")
    private String email="";

    @Column(name = "officeCode")
    private String officeCode="6";

    @Column(name = "reportsTo")
    private Integer reportsTo=-1;


}
