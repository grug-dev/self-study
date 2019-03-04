package com.kkpa.tutorialkotlin.config;

import com.kkpa.tutorialkotlin.timezone.InterfaceForObject
import com.kkpa.tutorialkotlin.timezone.RequestLogging
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
@EnableConfigurationProperties
class AppConfig {

    @Bean
    fun getClock(): Clock
    {
        return Clock.systemUTC()
    }

    @Bean
    fun getOffset(): InterfaceForObject {
        return RequestLogging
    }

}