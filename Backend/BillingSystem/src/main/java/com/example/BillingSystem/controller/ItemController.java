package com.example.BillingSystem.controller;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.BillingSystem.Utility.RequestValidationUtil;
import com.example.BillingSystem.model.Item;
import com.example.BillingSystem.reponse.BillingSystemResponseBuilder;
import com.example.BillingSystem.service.ItemService;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/items")
public class ItemController {
	
	private final ItemService itemService;
    private final RequestValidationUtil validationUtil;
	
	public ItemController(ItemService itemService, RequestValidationUtil validationUtil) {
        this.itemService = itemService;
        this.validationUtil = validationUtil;
    }

    // POST Method
    @PostMapping("/add")
    public ResponseEntity<Object> addItem(@Valid @RequestBody Item item, BindingResult result) {
        ResponseEntity<Object> validationResponse = validationUtil.validateRequest(result);
        if (validationResponse != null) {
            return validationResponse;
        }

        Item addedItem = itemService.addItem(item);
        return BillingSystemResponseBuilder.responseBuilder(
                "Item added successfully",
                HttpStatus.CREATED,
                addedItem
        );
    }
  
    @GetMapping("/all")
    public ResponseEntity<Object> getAllItems() {
        return BillingSystemResponseBuilder.responseBuilder(
            "Items fetched successfully",
            HttpStatus.OK,
            itemService.getAllItems()
        );
    }

  

 // PUT Method
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> UpdateItem(@PathVariable int id, @Valid @RequestBody Item item, BindingResult result) {
        ResponseEntity<Object> validationResponse = validationUtil.validateRequest(result);
        if (validationResponse != null) {
            return validationResponse;
        }

        // Service layer will handle checking if the item exists and throw exception if not
        Item updatedItem = itemService.UpdateItem(item);
        return BillingSystemResponseBuilder.responseBuilder(
                "Item updated successfully",
                HttpStatus.OK,
                updatedItem
        );
    }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Object> DeleteItem(@Valid @PathVariable int id, @RequestBody @Valid Item item) {
      item.setItemId(id);
      itemService.DeleteItem(item);

      return BillingSystemResponseBuilder.responseBuilder(
          "Item Deleted successfully",
          HttpStatus.OK,
          item // returning the updated item
      );
  }



}

