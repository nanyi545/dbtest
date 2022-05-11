package com.example.spring1.mappers;

import com.example.spring1.data.Office;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OfficeMapper {

    @Select("SELECT * FROM offices")
    @Results({
            @Result(property = "officeCode", column = "officeCode"),
            @Result(property = "city", column = "city"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "addressLine1", column = "addressLine1"),
            @Result(property = "addressLine2", column = "addressLine2"),
            @Result(property = "state", column = "state"),
            @Result(property = "country", column = "country"),
            @Result(property = "postalCode", column = "postalCode"),
            @Result(property = "territory", column = "territory")
    })
    List<Office> getAll();

    @Select("SELECT * FROM offices WHERE officeCode=#{id}")
    Office getById(String id);


    // todo how to auto-increase string PK  ??
    @Insert("insert into offices(city,phone,addressLine1,addressLine2,state,country,postalCode,territory) VALUES(#{city},#{phone},#{addressLine1},#{addressLine2},#{state},#{country},#{postalCode},#{territory})")
    @Options(useGeneratedKeys = true, keyProperty = "officeCode", keyColumn = "officeCode")
    String insert(Office user);



}
