package com.kenzie.appserver.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.repositories.CustomerRecordRepository;
import com.kenzie.appserver.repositories.model.CustomerRecord;
import com.kenzie.appserver.service.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubscriptionService {

    private CustomerRecordRepository customerRecordRepository;
    private CacheStore cache;
    private DynamoDBMapper dynamoDBMapper;


    @Autowired
    public SubscriptionService(CustomerRecordRepository customerRecordRepository){
        this.customerRecordRepository = customerRecordRepository;
//        this.cache = cache;
//        this.dynamoDBMapper = dynamoDBMapper;
    }

    public List<Customer> findAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        Iterable<CustomerRecord> iterator = customerRecordRepository.findAll();
        for (CustomerRecord record : iterator){
            customers.add(new Customer(record.getUserId(),
                    record.getName(),
                    record.getDaysOfWeek(),
                    record.getSecondDayOfWeek(),
                    record.getPickupTime(),
                    record.getNumOfBins()));
        }
        return customers;
    }

    public Customer findCustomerById(String userId) {
//        Customer cachedCustomer = cache.get(userId);
//        if (cachedCustomer != null) {
//            return cachedCustomer;
//        }
        Customer customer = customerRecordRepository
                .findById(userId)
                .map(customer1 -> new Customer(customer1.getUserId(),
                        customer1.getName(),
                        customer1.getDaysOfWeek(),
                        customer1.getSecondDayOfWeek(),
                        customer1.getPickupTime(),
                        customer1.getNumOfBins()))
                .orElse(null);
//        if (customer != null) {
//            cache.add(customer.getUserId(), customer);
//        }
        return customer;
    }


    public void updateCustomer(Customer customer){
        if (customerRecordRepository.existsById(customer.getUserId())) {
            //if (customer.getNumOfBins() < 5) {
                CustomerRecord customerRecord = new CustomerRecord();
                customerRecord.setUserId(customer.getUserId());
                customerRecord.setName(customer.getName());
                customerRecord.setDaysOfWeek(customer.getDaysOfWeek());
                customerRecord.setSecondDayOfWeek(customer.getSecondDayOfWeek());
                customerRecord.setPickupTime(customer.getPickupTime());
                customerRecord.setNumOfBins(customer.getNumOfBins());
                //customerRecord.setCancelled(customer.isCancelled());
                customerRecordRepository.save(customerRecord);
//                cache.evict(customer.getUserId());
           // } else throw new InvalidCustomerInputException("Please review information entered and submit again.");
        }
    }

    public void deleteBin (Customer customer){
        if (customerRecordRepository.existsById(customer.getUserId())){
            if (customer.getNumOfBins() > 0 && customer.getNumOfBins() <= 5 ) {
                CustomerRecord customerRecord = new CustomerRecord();
                customerRecord.setUserId(customer.getUserId());
                customerRecord.setName(customer.getName());
                customerRecord.setDaysOfWeek(customer.getDaysOfWeek());
                customerRecord.setPickupTime(customer.getPickupTime());
                customerRecord.setNumOfBins(customer.getNumOfBins());
                customerRecord.setSecondDayOfWeek(customer.getSecondDayOfWeek());
                customerRecordRepository.save(customerRecord);
                dynamoDBMapper.delete(customer.getNumOfBins());
                cache.evict(customer.getUserId());
            } else throw new IllegalArgumentException("Unable to delete trash bin. Number of bins must be between 1 and 5, to remove all bins please" +
                    "visit the cancel subscription option ");
//            System.out.println("Unable to delete trash bin. Customer cannot have zero bins, to remove all bins please" +
//                    "visit the cancel subscription option");
        }
    }

    public Customer addNewCustomer(Customer customer) {
        CustomerRecord record = new CustomerRecord();
        record.setUserId(customer.getUserId());
        record.setName(customer.getName());
        record.setDaysOfWeek(customer.getDaysOfWeek());
        record.setSecondDayOfWeek(customer.getSecondDayOfWeek());
        record.setPickupTime(customer.getPickupTime());
        record.setNumOfBins(customer.getNumOfBins());
        customerRecordRepository.save(record);
        return customer;
    }

    public void cancelService(Customer customer){
        if(customerRecordRepository.existsById(customer.getUserId())){
            customerRecordRepository.deleteById(customer.getUserId());
            cache.evict(customer.getUserId());
            dynamoDBMapper.delete(customer);
//            customer.setCancelled(true);
        }else{
            throw new IllegalArgumentException("Customer does not exist.");
        }
    }

    public Map<String, String> addReview(Customer name){
        Map<String, String> customerReview = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String review = scanner.nextLine();
        customerReview.put(name.getName(), review);

        return customerReview;
    }
}
