package com.example.billing.controller;
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

import com.example.billing.exception.BillingSystemAlreadyExist;
import com.example.billing.exception.BillingSystemInternalException;
import com.example.billing.exception.BillingSystemNotFoundException;
import com.example.billing.model.Item;
import com.example.billing.reponse.BillingSystemResponseBuilder;
import com.example.billing.service.ItemService;
import com.example.billing.utility.RequestValidationUtil;

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

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable("id") Long id, @RequestBody Item item) {
        try {
            item.setItemId(id); // Ensure the ID from the URL is set in the item object
            Item updatedItem = itemService.updateItem(item);
            return BillingSystemResponseBuilder.responseBuilder(
                    "Item updated successfully",
                    HttpStatus.OK,
                    updatedItem
            );
        } catch (BillingSystemNotFoundException ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    ex.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null);
        } catch (BillingSystemInternalException ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    "Internal server error: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
        } catch (Exception ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    "We’re currently experiencing a technical issue while connecting to the system. Please try again later.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

    
    @PostMapping("/search")
    public ResponseEntity<Object> searchItemByName(@RequestBody Item item) {
        try {
            Item foundItem = itemService.searchItemByName(item);
            return BillingSystemResponseBuilder.responseBuilder(
                    "Item found successfully",
                    HttpStatus.OK,
                    foundItem
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
