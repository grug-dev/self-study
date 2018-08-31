package com.kkpa.tutorial;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
@ConfigurationProperties("server")
public class CustomProperties {

  private String port;

}
