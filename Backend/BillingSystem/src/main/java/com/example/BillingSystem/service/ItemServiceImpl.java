package com.example.BillingSystem.service;


import org.springframework.dao.DataAccessException;



import org.springframework.stereotype.Service;

import com.example.BillingSystem.exception.BillingSystemAlreadyExist;
import com.example.BillingSystem.model.Item;
import com.example.BillingSystem.repository.ItemRepository;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public boolean addItem(Item item) {
        if (itemRepository.existsByName(item.getName())) {
            throw new BillingSystemAlreadyExist("Item name already exists");
        }
        itemRepository.save(item);
        return true; // Returning true on successful insertion
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    //Update logic 
    @Override
    public boolean UpdateItem(Item item) {
        if (!itemRepository.existsById(item.getItemId())) {
            throw new BillingSystemNotFoundException("Item does not exist");
        }
        itemRepository.update(item);
        return true;
    }
}

