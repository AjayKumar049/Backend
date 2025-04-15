package com.example.BillingSystem.controller;
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
import com.example.BillingSystem.exception.BillingSystemNotFoundException;
import com.example.BillingSystem.exception.BillingSystemAlreadyExist;
import com.example.BillingSystem.exception.BillingSystemInternalException;
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
        try {
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
        } catch (BillingSystemAlreadyExist ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    null
            );
        } catch (BillingSystemInternalException ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    "Internal server error: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        } catch (Exception ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    "Unexpected error occurred: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllItems() {
        try {
            return BillingSystemResponseBuilder.responseBuilder(
                    "Items fetched successfully",
                    HttpStatus.OK,
                    itemService.getAllItems()
            );
        } catch (BillingSystemInternalException ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    "Internal server error: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        } catch (Exception ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    "Unexpected error occurred: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

    // PUT Method
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> UpdateItem(@PathVariable int id, @Valid @RequestBody Item item, BindingResult result) {
        try {
            ResponseEntity<Object> validationResponse = validationUtil.validateRequest(result);
            if (validationResponse != null) {
                return validationResponse;
            }

            item.setItemId(id);  // Ensuring that the ID from the path is set in the item
            Item updatedItem = itemService.UpdateItem(item);
            return BillingSystemResponseBuilder.responseBuilder(
                    "Item updated successfully",
                    HttpStatus.OK,
                    updatedItem
            );
        } catch (BillingSystemNotFoundException ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    ex.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null
            );
        } catch (BillingSystemInternalException ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    "Internal server error: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        } catch (Exception ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    "Unexpected error occurred: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> DeleteItem(@PathVariable int id, @RequestBody @Valid Item item) {
        try {
            item.setItemId(id);
            itemService.DeleteItem(item);
            return BillingSystemResponseBuilder.responseBuilder(
                    "Item Deleted successfully",
                    HttpStatus.OK,
                    item // returning the updated item
            );
        } catch (BillingSystemNotFoundException ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    ex.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null
            );
        } catch (BillingSystemInternalException ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    "Internal server error: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        } catch (Exception ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    "Unexpected error occurred: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }
}
