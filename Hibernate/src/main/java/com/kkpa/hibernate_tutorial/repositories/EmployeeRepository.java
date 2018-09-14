package com.kkpa.hibernate_tutorial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kkpa.hibernate_tutorial.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  public Employee findByEmployeeId(Long employeeId);

}
