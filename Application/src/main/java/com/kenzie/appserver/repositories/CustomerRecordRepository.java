package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.CustomerRecord;
import com.kenzie.appserver.repositories.model.ExampleRecord;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface CustomerRecordRepository extends CrudRepository<CustomerRecord, String> {
    List<CustomerRecord> getUserId(String userId);
}
