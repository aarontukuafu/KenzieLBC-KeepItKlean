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
                    record.getName(),
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
                .map(customer1 -> new Customer(customer1.getUserId(), customer1.getName(),customer1.getDaysOfWeek(), customer1.getPickupTime(), customer1.getNumOfBins()))
                .orElse(null);
        if (customer != null) {
            cache.add(customer.getUserId(), customer);
        }
        return customer;
    }


    public void addBin (Customer customer){
        if (customerRecordRepository.existsById(customer.getUserId())) {
            if (customer.getNumOfBins() < 5) {
                CustomerRecord customerRecord = new CustomerRecord();
                customerRecord.setUserId(customer.getUserId());
                customerRecord.setName(customer.getName());
                customerRecord.setDaysOfWeek(customer.getDaysOfWeek());
                customerRecord.setPickupTime(customer.getPickupTime());
                customerRecord.setNumOfBins(customer.getNumOfBins());
                dynamoDBMapper.save(customer);
            } else throw new IllegalArgumentException();
            System.out.println("Exceeds maximum number of trash bins allowed. Up to 5 trash bins allowed per customer.");
        }
    }

    public void deleteBin (Customer customer){
        if (customerRecordRepository.existsById(customer.getUserId())){
            if (customer.getNumOfBins() > 0 || customer.getNumOfBins() <= 5 ) {
                CustomerRecord customerRecord = new CustomerRecord();
                customerRecord.setUserId(customer.getUserId());
                customerRecord.setUserId(customer.getUserId());
                customerRecord.setDaysOfWeek(customer.getDaysOfWeek());
                customerRecord.setPickupTime(customer.getPickupTime());
                customerRecord.setNumOfBins(customer.getNumOfBins());
                dynamoDBMapper.delete(customer.getNumOfBins());
            } else throw new IllegalArgumentException();
            System.out.println("Unable to delete trash bin. Customer cannot have zero bins, to remove all bins please" +
                    "visit the cancel subscription option");
        }
    }
}
