package com.kkpa.java8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

  private int id;

  @Override
  public int hashCode() {
    return -1;
  }


}
