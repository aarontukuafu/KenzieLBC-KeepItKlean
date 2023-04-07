package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.CustomerCreateRequest;
import com.kenzie.appserver.controller.model.CustomerUpdateRequest;
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

import java.time.LocalTime;
import java.util.UUID;

import static javax.swing.UIManager.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    @Test
    void updateCustomer_Successful() throws Exception {
        // create a customer
        CustomerCreateRequest createRequest = new CustomerCreateRequest();
//        Customer customer = content(createRequest);
//        // create a request to update the customer
//        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest();
//        updateRequest.setUserId(customer.getUserId());
//        updateRequest.setName(mockNeat.strings().size(10).val());
//        updateRequest.setDaysOfWeek("Mon,Tue");
//        updateRequest.setPickupTime(String.valueOf(LocalTime.of(14, 0)));
//        updateRequest.setSecondDayOfWeek(null);
//        updateRequest.setNumOfBins(2);
//        // call API to update the customer
//        mvc.perform(put("/example")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(updateRequest))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//        // check if the customer has been updated
//        Customer updatedCustomer = subscriptionService.findCustomerById(customer.getUserId());
//        assertNotNull(updatedCustomer);
//        assertEquals(updateRequest.getName(), updatedCustomer.getName());
//        assertEquals(updateRequest.getDaysOfWeek(), updatedCustomer.getDaysOfWeek());
//        assertEquals(updateRequest.getPickupTime(), updatedCustomer.getPickupTime());
//        assertEquals(updateRequest.getSecondDayOfWeek(), updatedCustomer.getSecondDayOfWeek());
//        assertEquals(updateRequest.getNumOfBins(), updatedCustomer.getNumOfBins());
//    }
    }
}
