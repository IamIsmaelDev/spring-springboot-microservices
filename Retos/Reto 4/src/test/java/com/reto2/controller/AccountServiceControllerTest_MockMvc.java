package com.reto2.controller;

import com.reto2.persistence.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// TODO: uncomment and implement methods
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
// @TestPropertySource( locations = "classpath:application-integrationtest.properties")
@Sql(value = "classpath:data_testing.sql")
@ActiveProfiles("test")
class AccountServiceControllerTest_MockMvc {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AccountRepository repository;

    //    Sobreescribimos configuración de seguridad para perfil test
    @TestConfiguration
    static class SecurotyAltConf {
        @Bean
        @Profile("test")
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .anyRequest().permitAll() // Permite todas las solicitudes
                    .and()
                    .csrf().disable(); // Desactiva CSRF para simplificar las pruebas

            return http.build();
        }
    }

    @Test
    public void givenCustomerIdGetAccountsThenStatus200() throws Exception {
        mvc.perform(get("/account/customer/1")
                .contentType(MediaType.APPLICATION_JSON) // Incluye el encabezado Content-Type
                .accept(MediaType.APPLICATION_JSON)) // Indica que esperas JSON en la respuesta
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].balance", is(1000)));
    }

        @Test
    public void givenCustomerIdOwnerIdGetAccountsThenStatus400() throws Exception {
        mvc.perform(get("/account/1/customer/100")
                .contentType(MediaType.APPLICATION_JSON) // Incluye el encabezado Content-Type
                .accept(MediaType.APPLICATION_JSON)) // Indica que esperas JSON en la respuesta
                .andExpect(status().isBadRequest());
    }

}