package com.kkpa.hibernate_tutorial.services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kkpa.hibernate_tutorial.EmployeeMaper;
import com.kkpa.hibernate_tutorial.dto.EmployeeDTO;
import com.kkpa.hibernate_tutorial.model.Employee;
import com.kkpa.hibernate_tutorial.repositories.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements CrudDomain<EmployeeDTO> {


  private EmployeeRepository employeeRepository;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<EmployeeDTO> findById(Long id) {
    Optional<Employee> employee = employeeRepository.findById(id);

    return Optional.of(EmployeeMaper.toDTO(employee.get()));
  }

  @Override
  public EmployeeDTO save(EmployeeDTO employeeDTO) {

    Employee newEmployee = EmployeeMaper.toEntity(employeeDTO);
    /*
     * Employee newEmployee = employeeRepository.findByEmployeeId(employee.getEmployeeId()); if
     * (newEmployee == null) { newEmployee = EmployeeMaper.toEntity(employeeDTO); }
     * 
     * 
     * Set<Employee> colleagues = new HashSet<>(); employee.getColleagues().stream().forEach(e -> {
     * 
     * Employee employeeFound = employeeRepository.findByEmployeeId(e.getEmployeeId()); if
     * (employeeFound == null) { employeeFound = employeeRepository.save(e); }
     * colleagues.add(employeeFound); });
     * 
     * newEmployee.setColleagues(colleagues);
     */
    newEmployee = employeeRepository.save(newEmployee);

    return EmployeeMaper.toDTO(newEmployee);
  }



}
