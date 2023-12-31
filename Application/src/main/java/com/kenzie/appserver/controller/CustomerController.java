package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.CustomerCreateRequest;
import com.kenzie.appserver.controller.model.CustomerResponse;
import com.kenzie.appserver.controller.model.CustomerUpdateRequest;
import com.kenzie.appserver.service.ExampleService;

import com.kenzie.appserver.service.SubscriptionService;
import com.kenzie.appserver.service.model.Customer;
import com.kenzie.appserver.service.model.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/example")
public class CustomerController {

    private SubscriptionService subscriptionService;

    CustomerController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CustomerResponse> get(@PathVariable("userId") String id){
        Customer customer = subscriptionService.findCustomerById(id);

        if (customer == null){
            return ResponseEntity.notFound().build();
        }


        CustomerResponse response = new CustomerResponse();
        response.setUserId(customer.getUserId());
        response.setDaysOfWeek(customer.getDaysOfWeek());
        response.setSecondDayOfWeek(customer.getSecondDayOfWeek());
        response.setPickupTime(customer.getPickupTime());
        response.setNumOfBins(customer.getNumOfBins());
        response.setName(customer.getName());

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> addNewCustomer(@RequestBody CustomerCreateRequest customerCreateRequest) {

        Customer customer = new Customer(customerCreateRequest.getUserId(),
                customerCreateRequest.getName(),
                customerCreateRequest.getDaysOfWeek(),
                customerCreateRequest.getSecondDayOfWeek(),
                customerCreateRequest.getPickupTime(),
                customerCreateRequest.getNumOfBins());

        subscriptionService.addNewCustomer(customer);

        CustomerResponse exampleResponse = new CustomerResponse();
        exampleResponse.setUserId(customer.getUserId());
        exampleResponse.setDaysOfWeek(customer.getDaysOfWeek());
        exampleResponse.setSecondDayOfWeek(customer.getSecondDayOfWeek());
        exampleResponse.setPickupTime(customer.getPickupTime());
        exampleResponse.setNumOfBins(customer.getNumOfBins());
        exampleResponse.setName(customer.getName());

        return ResponseEntity.created(URI.create("/example/" + exampleResponse.getUserId())).body(exampleResponse);
    }


    @PutMapping
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody CustomerUpdateRequest customerUpdateRequest) {

//        Customer customer = new Customer(customerId,
//                customerUpdateRequest.getName(),
//                customerUpdateRequest.getDaysOfWeek(),
//                customerUpdateRequest.getPickupTime(),
//                customerUpdateRequest.getSecondDayOfWeek(),
//                customerUpdateRequest.getNumOfBins());
        //customerUpdateRequest.getCancelled());

        Customer customer = new Customer();
        customer.setUserId(customerUpdateRequest.getUserId());
        customer.setDaysOfWeek(customerUpdateRequest.getDaysOfWeek());
        customer.setSecondDayOfWeek(customerUpdateRequest.getSecondDayOfWeek());
        customer.setPickupTime(customerUpdateRequest.getPickupTime());
        customer.setNumOfBins(customerUpdateRequest.getNumOfBins());
        customer.setName(customerUpdateRequest.getName());

        subscriptionService.updateCustomer(customer);

        CustomerResponse response = new CustomerResponse();
        response.setUserId(customer.getUserId());
        response.setDaysOfWeek(customer.getDaysOfWeek());
        response.setSecondDayOfWeek(customer.getSecondDayOfWeek());
        response.setPickupTime(customer.getPickupTime());
        response.setNumOfBins(customer.getNumOfBins());
        response.setName(customer.getName());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}/cancel")
    public ResponseEntity<CustomerResponse> cancelSubscription(@PathVariable("userId") String id){
        Customer customer = subscriptionService.findCustomerById(id);
        if (customer == null){
            return ResponseEntity.notFound().build();
        }
        subscriptionService.cancelService(customer);
        return (ResponseEntity<CustomerResponse>) ResponseEntity.ok();
    }
}