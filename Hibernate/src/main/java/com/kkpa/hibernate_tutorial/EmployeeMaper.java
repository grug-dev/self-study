package com.kkpa.hibernate_tutorial;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import com.kkpa.hibernate_tutorial.dto.EmployeeDTO;
import com.kkpa.hibernate_tutorial.model.Employee;

public class EmployeeMaper {

  public static Employee toEntity(EmployeeDTO employeeDTO) {
    Employee employee = new Employee();
    employee.setEmployeeId(employeeDTO.getEmployeeId());


    Set<Employee> colleagues =
        employeeDTO.getColleagues().stream().map(Employee::new).collect(Collectors.toSet());
    employee.setColleagues(colleagues);

    return employee;
  }

  public static EmployeeDTO toDTO(Employee employee) {
    EmployeeDTO employeeDTO = new EmployeeDTO();

    employeeDTO.setEmployeeId(employee.getEmployeeId());
    Set<Long> colleagues = new HashSet<>();

    colleagues =
        employee.getColleagues().stream().map(e -> e.getEmployeeId()).collect(Collectors.toSet());

    employeeDTO.setColleagues(colleagues);

    return employeeDTO;
  }

}
