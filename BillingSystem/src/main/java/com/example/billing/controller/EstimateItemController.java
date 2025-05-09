package com.example.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.billing.model.EstimateItem;
import com.example.billing.model.Item;
import com.example.billing.service.EstimateItemService;

@RestController
@RequestMapping("/api/estimate-items")
public class EstimateItemController {

    @Autowired
    private EstimateItemService estimateItemService;

    @GetMapping("/item-details")
    public ResponseEntity<Item> getItemDetails(@RequestParam String name) {
        Item item = estimateItemService.getItemDetailsByName(name);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEstimateItem(@RequestBody EstimateItem estimateItem) {
        estimateItemService.addEstimateItem(estimateItem);
        return ResponseEntity.ok("Estimate item added successfully");
    }
}

