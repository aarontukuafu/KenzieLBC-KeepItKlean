package com.kenzie.appserver.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.repositories.CustomerRecordRepository;
import com.kenzie.appserver.repositories.model.CustomerRecord;
import com.kenzie.appserver.service.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {

    private CustomerRecordRepository customerRecordRepository;
    private CacheStore cache;

    private final DynamoDBMapper dynamoDBMapper;


    @Autowired
    public SubscriptionService(CustomerRecordRepository customerRecordRepository, CacheStore cache, DynamoDBMapper dynamoDBMapper){
        this.customerRecordRepository = customerRecordRepository;
        this.cache = cache;
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public List<Customer> findAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        Iterable<CustomerRecord> iterator = customerRecordRepository.findAll();
        for (CustomerRecord record : iterator){
            customers.add(new Customer(record.getUserId(),
                    record.getDaysOfWeek(),
                        record.getPickupTime(),
                    record.getNumOfBins()));
        }
        return customers;
    }

    public Customer findCustomerById(String userId) {
        Customer cachedCustomer = cache.get(userId);
        if (cachedCustomer != null) {
            return cachedCustomer;
        }
        Customer customer = customerRecordRepository
                .findById(userId)
                .map(customer1 -> new Customer(customer1.getUserId(), customer1.getDaysOfWeek(), customer1.getPickupTime(), customer1.getNumOfBins()))
                .orElse(null);
        if (customer != null) {
            cache.add(customer.getUserId(), customer);
        }
        return customer;
    }


    public void addBin (Customer customer){
        if (customerRecordRepository.existsById(customer.getUserId())) {
            customer.setUserId(customer.getUserId());
            customer.setDaysOfWeek(customer.getDaysOfWeek());
            customer.setPickupTime(customer.getPickupTime());
            customer.setNumOfBins(customer.getNumOfBins());
            dynamoDBMapper.save(customer);
        }
    }

    public void deleteBin (String binNumber){
        customerRecordRepository.deleteById(binNumber);
        cache.evict(binNumber);
    }
}
