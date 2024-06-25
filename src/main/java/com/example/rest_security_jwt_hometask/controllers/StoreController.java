package com.example.rest_security_jwt_hometask.controllers;

import com.example.rest_security_jwt_hometask.entities.Store;
import com.example.rest_security_jwt_hometask.services.StoreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/store/")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Store> create(@Valid @RequestBody Store entity) {
        Store createdStore = storeService.createStore(entity);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<Store> update(@Valid @RequestBody Store entity) {
        Store updatedStore = storeService.updateStore(entity);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        storeService.deleteStore(id);
        return new ResponseEntity<>("Successfully Deleted - Store", HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Store> get(@PathVariable Long id) {
        Optional<Store> optionalStore = storeService.getStore(id);
        return optionalStore.map(
                store -> new ResponseEntity<>(store, HttpStatus.OK)
        ).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

}