package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.CustomerCreateRequest;
import com.kenzie.appserver.controller.model.CustomerResponse;
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

//    TODO Service class need to be completed before to properly inplement this class----------------------------

//    @GetMapping("/{UserId}")
//    public ResponseEntity<CustomerResponse> get(@PathVariable("UserId") String id) {
//
//        Example example = exampleService.findById(id);
//        if (example == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        CustomerResponse exampleResponse = new CustomerResponse();
//        exampleResponse.setId(example.getId());
//        exampleResponse.setName(example.getName());
//        return ResponseEntity.ok(exampleResponse);
//    }

    @PostMapping
    public ResponseEntity<CustomerResponse> addNewConcert(@RequestBody CustomerCreateRequest customerCreateRequest) {
//        Example example = new Example(randomUUID().toString(),
//                customerCreateRequest.getName());
//        exampleService.addNewExample(example);

        Customer customer = new Customer(randomUUID().toString(),
                customerCreateRequest.getName(),customerCreateRequest.getDaysOfWeek(),
                customerCreateRequest.getPickupTime(),
                customerCreateRequest.getNumOfBins());

        subscriptionService.addNewCustomer(customer);

        CustomerResponse exampleResponse = new CustomerResponse();
        exampleResponse.setUserId(customer.getUserId());
        exampleResponse.setDaysOfWeek(customer.getDaysOfWeek());
        exampleResponse.setPickupTime(customer.getPickupTime());
        exampleResponse.setNumOfBins(customer.getNumOfBins());
        exampleResponse.setName(customer.getName());

        return ResponseEntity.created(URI.create("/example/" + exampleResponse.getUserId())).body(exampleResponse);
    }
}
