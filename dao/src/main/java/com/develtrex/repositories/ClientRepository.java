package com.develtrex.repositories;

import com.develtrex.config.MongoConfig;
import com.develtrex.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository implements BaseRepository<Client> {
    // Fields
    @Autowired
    @Qualifier(MongoConfig.MONGODBDEVOLPAY)
    private MongoTemplate mongoTemplate;

    // Methods
    @Override
    public Client insert(Client entity) {
        return mongoTemplate.insert(entity, "clients");
    }

    @Override
    public Client update(Client entity) {
        return mongoTemplate.save(entity, "clients");
    }

    @Override
    public void deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Client.class);
    }

    @Override
    public List<Client> getAll() {
        return mongoTemplate.findAll(Client.class);
    }

    @Override
    public void deleteAll() {
        mongoTemplate.remove(new Query(), Client.class);
    }

    @Override
    public Client getById(String id) {
        return mongoTemplate.findById(id, Client.class);
    }

    @Override
    public boolean existsById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, Client.class);
    }
}
