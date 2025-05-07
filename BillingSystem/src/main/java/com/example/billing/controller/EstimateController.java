package com.example.billing.controller;

import com.example.billing.model.Estimate;

import com.example.billing.reponse.BillingSystemResponseBuilder;
import com.example.billing.service.EstimateService;
import com.example.billing.utility.RequestValidationUtil;

import jakarta.validation.Valid;

import com.example.billing.constant.BillingSystemConstants;
import com.example.billing.exception.BillingSystemAlreadyExist;
import com.example.billing.exception.BillingSystemInternalException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/estimates")
public class EstimateController {

    @Autowired
    private EstimateService estimateService;

    private final RequestValidationUtil validationUtil = new RequestValidationUtil();

    @PostMapping("/add")
    public ResponseEntity<Object> createEstimate(@Valid @RequestBody Estimate estimate, BindingResult result) {
        try {
            ResponseEntity<Object> validationResponse = validationUtil.validateRequest(result);
            if (validationResponse != null) {
                return validationResponse;
            }

            estimateService.createEstimate(estimate);

            return BillingSystemResponseBuilder.responseBuilder(
                    "Estimate created successfully",
                    HttpStatus.CREATED,
                    estimate
            );
        } catch (BillingSystemAlreadyExist ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    null
            );
        } catch (BillingSystemInternalException ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    BillingSystemConstants.INTERNAL_ERROR + ": " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        } catch (Exception ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    BillingSystemConstants.UNEXPECTED_ERROR + ": " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }
}
