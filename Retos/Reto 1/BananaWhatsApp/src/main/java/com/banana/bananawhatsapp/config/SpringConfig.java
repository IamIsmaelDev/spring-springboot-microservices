package com.banana.bananawhatsapp.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"com.banana.bananawhatsapp.persistencia","com.banana.bananawhatsapp.servicios","com.bananawhatsapp,controladores" })
@EntityScan("com.banana.bananawhatsapp.modelos")
@EnableJpaRepositories(basePackages = {"com.banana.whatsapp.persistence"})
public class SpringConfig {
}
