package com.develtrex.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.develtrex.controllers"})
public class RestConfig {
}
