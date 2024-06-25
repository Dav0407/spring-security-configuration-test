package com.example.rest_security_jwt_hometask.services;

import com.example.rest_security_jwt_hometask.entities.Item;
import com.example.rest_security_jwt_hometask.entities.Store;
import com.example.rest_security_jwt_hometask.repositories.ItemRepository;
import com.example.rest_security_jwt_hometask.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService {


    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    public Store updateStore(Store store) {
        return storeRepository.save(store);
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }

    public Optional<Store> getStore(Long id) {
        return storeRepository.findById(id);
    }

}
