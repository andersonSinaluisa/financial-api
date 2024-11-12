package com.andersonsinaluisa.financial_api.controllers;

import com.andersonsinaluisa.financial_api.FinancialApiApplication;
import com.andersonsinaluisa.financial_api.core.application.create.AccountCreateUseCase;
import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.model.TypeTransaction;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.account.AccountCreateDto;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.core.JsonParser;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectWriter;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = FinancialApiApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class AccountControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private AccountCreateUseCase createUseCase;

    @Test
    public void givenAccounts_whenGetAccount_thenStatus200() throws Exception {
        Faker faker = new Faker();

        Account a = createUseCase.create(Account.builder()
                .initial_balance(
                        faker.number()
                                .randomDouble(2,10,59633)
                )
                .status("A")
                .account_number(faker.business().creditCardNumber())
                .account_type(TypeTransaction.INGRESO.getValue())
                .account_name(faker.commerce().productName())
                .created_at(LocalDateTime.now())
                .build());
        mvc.perform(get("/account")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].account_number",is(a.account_number)));
    }


    @Test
    public void givenAccount_WhenCreateAccount_thenStatus200() throws Exception {
        Faker faker = new Faker();
        AccountCreateDto d = AccountCreateDto.builder()
                .account_name(faker.commerce().productName())
                .account_number(faker.commerce().promotionCode())
                .account_type(TypeTransaction.INGRESO.getValue())
                .initial_balance(faker.number().randomDouble(2,10,99999))
                .status(faker.bool().bool()?"A":"I")

                .build();
        ObjectMapper mapper = new ObjectMapper();

        String json =  mapper.writeValueAsString(d);
        mvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(json)

        ).andExpect(
                status().isOk()
        ).andExpect(
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
        ).andExpect(
                jsonPath("$.account_number",is(d.account_number))
        );
    }
}
