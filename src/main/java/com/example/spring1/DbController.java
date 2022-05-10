package com.example.spring1;

import com.example.spring1.dao.DbService;
import com.example.spring1.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * https://restfulapi.cn/page/restful-api-request
 */
@RestController
//相当于是 @Controller 与 @ResponseBody 的组合注解，意味着当前控制层类中的所有方法返回的都是Json对象
// @RestController 那么该 Controller 类下的所有方法都相当于添加了@ResponseBody 注解，用于返回字符串或 json 数据。
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

    @PostMapping("/addemployee")
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
        System.out.println("presaved:"+e);
        Employee saved = dbService.getEmployRepo().save(e);
        System.out.println("saved:"+saved);
        return new ResponseEntity(saved,  HttpStatus.OK);
    }





}
