package com.kkpa.tutorial.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User implements Serializable {



  public User(long id2) {
    this.id = id2;
  }

  public User() {

  }

  @Id
  private Long id;
  private String name;
  private int age;
  private LocalDate birthDate = LocalDate.now();

}
