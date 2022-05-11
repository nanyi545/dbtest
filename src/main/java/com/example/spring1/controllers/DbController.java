package com.example.spring1.controllers;

import com.example.spring1.dao.DbService;
import com.example.spring1.data.Employee;
import com.example.spring1.data.Item;
import com.example.spring1.data.Office;
import com.example.spring1.mappers.EmployeeMapper;
import com.example.spring1.mappers.ItemMapper;
import com.example.spring1.mappers.OfficeMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * https://restfulapi.cn/page/restful-api-request
 */
@RestController
// https://spring.io/guides/gs/spring-boot/
// . That is because @RestController combines @Controller and @ResponseBody, two annotations that
// results in web requests returning data rather than a view.

public class DbController {

    @Autowired
    private DbService dbService;

    /**
     * https://blog.csdn.net/weixin_43823808/article/details/116894611
     *
     * 1.2 @RequestMapping（常用）
     *      支持 Get 请求，也支持 Post 请求
     *
     * 1.3 @GetMapping
     *     @RequestMapping 和 Get 请求方法的组合，只支持 Get 请求，Get 请求主要用于查询操作。
     *
     * 1.4 @PostMapping
     *     @RequestMapping 和 Post 请求方法的组合，只支持 Post 请求，Post 请求主要用户新增数据。
     *
     * 1.5 @PutMapping
     *     @RequestMapping 和 Put 请求方法的组合，只支持 Put 请求，Put 通常用于修改数据。
     *
     * 1.6 @DeleteMapping
     *     @RequestMapping 和 Delete 请求方法的组合，只支持 Delete 请求，通常用于删除数据。
     *
     */

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> list = dbService.getEmployRepo().findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Object> getEmployee(@PathVariable("id") Integer id){
        Optional<Employee> o =  dbService.getEmployRepo().findById(id);
        if(o.isEmpty()){
            return new ResponseEntity("{}",  HttpStatus.OK);
        }
        return new ResponseEntity(o.get(), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Object> addEmployee(
            @RequestParam (required = false) String firstName,
            @RequestParam (required = false) String lastName,
            @RequestParam (required = false) int reportsTo,
            @RequestParam (required = false) String jobTitle
    ){
        Employee e = new Employee();
        e.setFirstName(firstName);
        e.setLastName(lastName);
        e.setReportsTo(reportsTo);
        e.setJobTitle(jobTitle);

        Employee saved = dbService.getEmployRepo().save(e);
        return new ResponseEntity(saved,  HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Object> removeEmployee(@PathVariable("id") Integer id){
        List<Integer> list = new ArrayList<>();
        list.add(id);
        dbService.getEmployRepo().deleteAllById(list);
        return new ResponseEntity("{\"result\":\"success\"}",  HttpStatus.OK);
    }


    /**
     *  using mybatis
     */

    @Autowired
    OfficeMapper officeMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    ItemMapper itemMapper;


    @GetMapping("/offices2")
    public ResponseEntity<List<Office>> getO(){
        List<Office> list = officeMapper.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/offices2/{id}")
    public ResponseEntity<Object> getO(@PathVariable("id") String id){
        Office o = officeMapper.getById(id);
        if(o==null){
            return new ResponseEntity("{}", HttpStatus.OK);
        } else {
            return new ResponseEntity(o, HttpStatus.OK);
        }
    }


    @PostMapping("/offices2")
    public ResponseEntity<Object> addO(
            @RequestParam (required = false) String city,
            @RequestParam (required = false) String phone,
            @RequestParam (required = false) String state,
            @RequestParam (required = false) String country
    ){
        Office office = new Office();
        office.setCity(city);
        office.setPhone(phone);
        office.setState(state);
        office.setCountry(country);
        office.setAddressLine1("road 123");
        office.setAddressLine1("building 123");
        office.setPostalCode("310000");
        office.setTerritory("terri");

        String newId = officeMapper.insert(office);
        return new ResponseEntity("{\"newId\":\""+newId+"\"}", HttpStatus.OK);
    }


    @GetMapping("/items2")
    public ResponseEntity<List<Item>> getI(){
        List<Item> list = itemMapper.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/items2/{id}")
    public ResponseEntity<Item> getI2(@PathVariable("id") int id){
        Item item = itemMapper.getById(id);
        if(item==null){
            return new ResponseEntity("{}", HttpStatus.OK);
        }
        return new ResponseEntity(item, HttpStatus.OK);
    }

    @PostMapping("/items2/{itemNo}")
    public ResponseEntity<Object> addI(@PathVariable("itemNo") String itemNo){
        Item i = new Item();
        i.setItem_no(itemNo);
        itemMapper.insert(i);
        return new ResponseEntity(i.getId(), HttpStatus.OK);
    }

    @DeleteMapping("/items2/{id}")
    public ResponseEntity<Object> removeI(@PathVariable("id") int id){
        int i = itemMapper.delete(id);
        System.out.println(i);
        return new ResponseEntity("{}", HttpStatus.OK);
    }


    @GetMapping("/employees2")
    public ResponseEntity<List<Employee>> getE(){
        List<Employee> list = employeeMapper.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }




}
