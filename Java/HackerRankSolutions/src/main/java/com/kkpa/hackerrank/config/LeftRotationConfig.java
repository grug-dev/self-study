package com.kkpa.hackerrank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.kkpa.hackerrank.interviewpreparationkit.arrays.LeftRotation;

@Configuration
public class LeftRotationConfig {

  @Bean
  public LeftRotation leftRotationBean() {
    return new LeftRotation();
  }

}
