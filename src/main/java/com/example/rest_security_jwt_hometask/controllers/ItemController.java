package com.example.rest_security_jwt_hometask.controllers;

import com.example.rest_security_jwt_hometask.entities.Item;
import com.example.rest_security_jwt_hometask.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Item> create(@Valid @RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Item> update(@Valid @RequestBody Item item) {
        Item updatedItem = itemService.updateItem(item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>("Successfully Deleted - Item", HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<Item> get(@PathVariable Long id) {
        Optional<Item> optionalItem = itemService.getItem(id);
        return optionalItem.map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}