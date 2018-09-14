package com.kkpa.hibernate_tutorial.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Employee {

  public Employee() {

  }

  public Employee(long id) {
    this.employeeId = id;
  }


  // @Id
  // @GeneratedValue
  // private Long id;

  @Id
  @Column(name = "EMPLOYEE_ID")
  private Long employeeId;


  // fetch = FetchType.EAGER, targetEntity = Employee.class

  // @JsonIgnore
  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "EMPLOYEE_COLLEAGUE", joinColumns = {@JoinColumn(name = "EMPLOYEE_ID")},
      inverseJoinColumns = {@JoinColumn(name = "COLLEAGUE_ID")})
  private Set<Employee> colleagues = new HashSet<>();



}
