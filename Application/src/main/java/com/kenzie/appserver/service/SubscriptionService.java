package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheConfig;
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

    @Autowired
    public SubscriptionService(CustomerRecordRepository customerRecordRepository, CacheStore cache){
        this.customerRecordRepository = customerRecordRepository;
        this.cache = cache;
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

    public void addBin (Customer customer){
        if (customerRecordRepository.existsById(customer.getUserId())) {
            CustomerRecord customerRecord = new CustomerRecord();
            customerRecord.setUserId(customer.getUserId());
            customerRecord.setDaysOfWeek(customer.getDaysOfWeek());
            customerRecord.setPickupTime(customer.getPickupTime());
            customerRecord.setNumOfBins(customer.getNumOfBins());
            customerRecordRepository.save(customerRecord);
            cache.evict(customer.getUserId());
        }
    }

    public void deleteBin (String binNumber){
        customerRecordRepository.deleteById(binNumber);
        cache.evict(binNumber);
    }
}
