package com.kkpa.tutorial.handle;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ExceptionResponse {

  private LocalDate date;
  private String message;

}
