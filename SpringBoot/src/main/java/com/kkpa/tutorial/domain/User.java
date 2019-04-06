package com.kkpa.tutorial.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFilter;
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
  @NotNull
  private Long id;

  @Size(min = 10, message = "Name should have atleast 10 characteres")
  private String name;
  private int age;
  private LocalDate birthDate = LocalDate.now();

}
