package com.kkpa.hibernate_tutorial;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kkpa.hibernate_tutorial.dto.EmployeeDTO;
import com.kkpa.hibernate_tutorial.services.EmployeeServiceImpl;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  private EmployeeServiceImpl employeeService;


  public EmployeeController(EmployeeServiceImpl employeeService) {
    this.employeeService = employeeService;
  }


  @PostMapping("/")
  public String createEmployees(@RequestBody EmployeeDTO employeeDTO) {


    employeeService.save(employeeDTO);

    return "YEs!";
  }


}
