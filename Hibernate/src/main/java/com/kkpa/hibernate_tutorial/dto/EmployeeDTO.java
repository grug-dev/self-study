package com.kkpa.hibernate_tutorial.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class EmployeeDTO implements Serializable {

  public EmployeeDTO() {

  }

  public EmployeeDTO(Long id) {
    this.employeeId = id;
  }

  private Long employeeId;

  private Set<Long> colleagues = new HashSet<>();

}
