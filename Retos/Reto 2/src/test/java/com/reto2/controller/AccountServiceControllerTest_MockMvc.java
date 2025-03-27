package com.reto2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
@Sql(value = "classpath:testing.sql")
class AccountServiceControllerTest_MockMvc {

    @Autowired
    private MockMvc mvc;

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