package com.develtrex.repositories;

import com.develtrex.config.MongoConfig;
import com.develtrex.entities.Client;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = {MongoConfig.class})
@RunWith(SpringRunner.class)
@DataMongoTest
@Log4j2
public class ClientRepositoryTest {
    // Fields
    @Autowired
    @Qualifier(MongoConfig.MONGODBDEVOLPAY)
    private MongoTemplate mongoTemplate;

    @Autowired
    private ClientRepository clientRepository;

    // Methods
    @Test
    public void insertClient() {
        // Create a new client
        Client oClient = new Client();
        //oClient.setId();
        oClient.setNames("Cris");
        oClient.setLastnames("Sandoval");
        oClient.setDni("12345678");
        oClient.setPhone("987654098");
        oClient.setAddress("Trujillo - La Libertad");

        clientRepository.insert(oClient);

        var count = clientRepository.getAll().size();
        log.info("Collection clients size: " + count);
        assertTrue(count > 0);

    }
}
