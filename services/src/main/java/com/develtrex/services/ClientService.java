package com.develtrex.services;


import com.develtrex.entities.Client;
import com.develtrex.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements BaseServices<Client> {
    // Fields
    @Autowired
    private ClientRepository clientRepository;

    // Methods
    @Override
    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    @Override
    public Client getById(String id) {
        return clientRepository.getById(id);
    }

    @Override
    public Client create(Client entity) {
        return clientRepository.insert(entity);
    }

    @Override
    public Client update(Client entity) {
        return clientRepository.update(entity);
    }

    @Override
    public boolean deleteById(String id) {
        boolean exists = clientRepository.existsById(id);

        if(exists) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        clientRepository.deleteAll();
    }
}
