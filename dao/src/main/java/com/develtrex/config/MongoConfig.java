package com.develtrex.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Slf4j
@Configuration
@EnableMongoRepositories(basePackages = {"com.develtrex.repositories"})
@ComponentScan(basePackages = {"com.develtrex.repositories"})
public class MongoConfig extends AbstractMongoClientConfiguration {
    public static final String MONGODBDEVOLPAY = "devolpay";

    @Value("${database.mongodb.uri}")
    private String connectionString;

    @Value("${database.mongodb.name}")
    private String databaseName;

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(this.connectionString);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    protected String getDatabaseName() {
        return this.databaseName;
    }

    @Override
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.develtrex");
    }

    @Bean(name=MONGODBDEVOLPAY)
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(),MONGODBDEVOLPAY);
    }
}
