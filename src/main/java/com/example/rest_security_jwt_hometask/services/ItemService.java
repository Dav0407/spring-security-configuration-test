package com.example.rest_security_jwt_hometask.services;

import com.example.rest_security_jwt_hometask.entities.Item;
import com.example.rest_security_jwt_hometask.repositories.ItemRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Optional<Item> getItem(Long id) {
        return itemRepository.findById(id);
    }



}
