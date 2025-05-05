package com.example.billing.service;
import java.util.List;
import com.example.billing.model.Item;
public interface ItemService {
 Item addItem(Item item);
 Item updateItem(Item item);
 List<Item> getAllItems();
 Item deleteItem(Item item);
 Item searchItemByName(Item item);



}

