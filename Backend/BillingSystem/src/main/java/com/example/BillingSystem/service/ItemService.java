package com.example.BillingSystem.service;
import java.util.List;
import com.example.BillingSystem.model.Item;
import com.example.BillingSystem.model.Item;
public interface ItemService {
 Item addItem(Item item);
 Item UpdateItem(Item item);
 List<Item> getAllItems();
 Item DeleteItem(Item item);
 Item searchItemByName(Item item);
}

