package com.develtrex.controllers;

import com.develtrex.entities.Client;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Log4j2
@RunWith(SpringRunner.class)
public class ClientControllerTest {
    // Fields
    @Autowired
    private TestRestTemplate testRestTemplate;
    private String uri;

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        uri = "http://localhost:" + port + "/api/";
    }

    @Test
    public void getClients() {
        log.info("Running getClients test");

        var response = testRestTemplate.getForEntity(uri + "clients", Client[].class);
        var clientTest = response.getBody()[0];

        for (var item : response.getBody()) {
            log.info("item: " + item.toString());
        }

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Client[].class, response.getBody().getClass());
        assertTrue(response.getBody().length >= 1);

        log.info("clientTest: " + clientTest.toString());
        assertEquals("Cris", clientTest.getNames());
        assertEquals("Sandoval", clientTest.getLastnames());
    }

    @Test
    public void getClient() {
        log.info("Running getClient test");

        var response = testRestTemplate.getForEntity(uri + "clients/63fd1b8b8d0ba763e5de1aec", Client.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cris", response.getBody().getNames());

        var responseError = testRestTemplate.getForEntity(uri + "clients/23483438734", Client.class);

        assertEquals(HttpStatus.NOT_FOUND, responseError.getStatusCode());
        assertEquals(null, responseError.getBody());
    }

    @Test
    public void createClient(){
        log.info("Running createClient test");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        var client = new Client();
        client.setNames("Alexander");
        client.setLastnames("Nole");
        client.setDni("61513129");
        client.setPhone("937579505");
        client.setAddress("Tumbes - Tumbes");

        HttpEntity<Client> request = new HttpEntity<>(client,headers);

        var response = testRestTemplate.postForEntity(uri + "clients",request,Client.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Alexander", response.getBody().getNames());
    }

}
