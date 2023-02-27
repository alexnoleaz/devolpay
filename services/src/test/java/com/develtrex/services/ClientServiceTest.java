package com.develtrex.services;

import com.develtrex.config.MongoConfig;
import com.develtrex.config.ServicesConfig;
import com.develtrex.entities.Client;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ContextConfiguration(classes = {ServicesConfig.class, MongoConfig.class})
@RunWith(SpringRunner.class)
@DataMongoTest
@Log4j2
@TestMethodOrder(OrderAnnotation.class)
public class ClientServiceTest {
    // Fields
    @Autowired
    private ClientService clientService;

    // Methods
    @Test
    @Order(1)
    public void create() {
        log.info("Running create test");
        // Client a new client
        Client newClient = new Client();
        newClient.setNames("Alexander");
        newClient.setLastnames("Nole");
        newClient.setDni("61513129");
        newClient.setPhone("937579505");
        newClient.setAddress("Trujillo - La Liberdad");

        var createdClient = clientService.create(newClient);
        assertTrue(createdClient != null);
        assertEquals("Alexander", createdClient.getNames());
        log.info("createdClient: " + createdClient.toString());
    }

    @Test
    @Order(2)
    public void getAll() {
        log.info("Running getAll test");
        var result = clientService.getAll();
        var firstClient = result.get(0);

        assertTrue(result.size() >= 2);
        assertEquals("Cris", firstClient.getNames());

        for (var item : result) {
            log.info("item: " + item.toString());
        }
    }

    @Test
    @Order(3)
    public void getById() {
        log.info("Running getById test");
        var clientTest = clientService.getAll().get(0);
        var clientFound = clientService.getById(clientTest.getId());

        log.info("clientTest: " + clientTest.toString());
        log.info("clientFound: " + clientFound.toString());

        assertTrue(clientTest != null && clientFound != null);
        assertEquals(clientTest.getNames(), clientFound.getNames());
    }

    @Test
    @Order(4)
    public void update() {
        log.info("Running update test");

        var clientTest = clientService.getAll().get(0);
        log.info("clientTest: " + clientTest.toString());

        clientTest.setAddress("Tumbes - Tumbes");
        clientTest.setDni("00000000");

        var updatedClient = clientService.update(clientTest);
        log.info("updatedClient: " + updatedClient.toString());

        assertTrue(clientTest != null && updatedClient != null);
        assertTrue(clientTest.getId().equals(updatedClient.getId()));
        assertEquals(clientTest.getNames(), updatedClient.getNames());
        assertEquals(clientTest.getLastnames(), updatedClient.getLastnames());
    }

    @Test
    @Order(5)
    public void deleteById() {
        log.info("Running deleteById test");
        var clientTest = clientService.getAll().get(1);

        boolean result = clientService.deleteById(clientTest.getId());
        assertEquals(true, result);
        log.info("deleted client successfully");

        result = clientService.deleteById(clientTest.getId());
        assertEquals(false, result);
        log.info("client not found");

    }

    @Test
    @Order(6)
    public void deleteAll() {
        log.info("Running deleteAll test");

        var count = clientService.getAll().size();
        log.info("Clients collection size: " + count);
        assertTrue(count >= 1);

        clientService.deleteAll();
        count = clientService.getAll().size();
        assertTrue(count == 0);
        log.info("Deleted clients successfully");
        log.info("Clients collection size: " + count);

    }
}













