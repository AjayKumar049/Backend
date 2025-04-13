package com.example.BillingSystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BillingSystem.model.Item;
import com.example.BillingSystem.reponse.BillingSystemResponseBuilder;
import com.example.BillingSystem.service.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addItem(@RequestBody Item item) {
        return BillingSystemResponseBuilder.responseBuilder(
                "Item added successfully",
                HttpStatus.OK,
                itemService.addItem(item)
        );
    }

 
    
    
    @GetMapping("/all")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}

