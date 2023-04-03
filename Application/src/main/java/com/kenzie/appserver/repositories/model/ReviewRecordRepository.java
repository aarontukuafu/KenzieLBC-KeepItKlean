package com.kenzie.appserver.repositories.model;

import com.kenzie.appserver.service.model.Customer;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface ReviewRecordRepository extends CrudRepository<ReviewRecord, Customer> {
    List<ReviewRecord> getReviewRecordByName (Customer name);
}
