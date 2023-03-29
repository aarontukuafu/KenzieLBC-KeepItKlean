package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.service.SubscriptionService;
import com.kenzie.appserver.service.model.Customer;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.org.awaitility.Awaitility.given;
import static sun.nio.cs.Surrogate.is;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
@IntegrationTest
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SubscriptionService subscriptionService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void newUser_withValidInputs_createsSubscription(){
        String id = UUID.randomUUID().toString();
        String name = mockNeat.strings().valStr();
        String daysOfWeek = "Monday-Sunday";
        String pickupTime = "NOON";
        int numOfBins = 2;

        Customer customer = new Customer(id,name, daysOfWeek,pickupTime,numOfBins);

//        mvc.perform(get("/example/{id}", id)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.userId")
//                        .value(is(id)))
//                .andExpect(jsonPath("$.name")
//                        .value(is(name)))
//                .andExpect(jsonPath("$.daysOfWeek")
//                        .value(is(daysOfWeek)))
//                .andExpect(jsonPath("$.pickupTime")
//                        .value(is(pickupTime)))
//                .andExpect(jsonPath("$.numOfBins")
//                        .value(is(numOfBins)))
//                .andExpect(status().isOk());

    }

}
