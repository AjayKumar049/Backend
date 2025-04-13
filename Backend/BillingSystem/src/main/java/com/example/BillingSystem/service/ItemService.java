package com.example.BillingSystem.service;


import java.util.List;



import com.example.BillingSystem.model.Item;



public interface ItemService {
 boolean addItem(Item item);
 List<Item> getAllItems();
 boolean UpdateItem(Item item);


}

