package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.CustomerCreateRequest;
import com.kenzie.appserver.controller.model.CustomerResponse;
import com.kenzie.appserver.service.ExampleService;

import com.kenzie.appserver.service.SubscriptionService;
import com.kenzie.appserver.service.model.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/example")
public class CustomerController {

    private ExampleService exampleService;

    CustomerController(ExampleService exampleService) {
        this.exampleService = exampleService;
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
    public ResponseEntity<CustomerResponse> addNewConcert(@RequestBody CustomerCreateRequest exampleCreateRequest) {
        Example example = new Example(randomUUID().toString(),
                exampleCreateRequest.getName());
        exampleService.addNewExample(example);

        SubscriptionService

        CustomerResponse exampleResponse = new CustomerResponse();
        exampleResponse.setId(example.getId());
        exampleResponse.setName(example.getName());

        return ResponseEntity.created(URI.create("/example/" + exampleResponse.getId())).body(exampleResponse);
    }
}
