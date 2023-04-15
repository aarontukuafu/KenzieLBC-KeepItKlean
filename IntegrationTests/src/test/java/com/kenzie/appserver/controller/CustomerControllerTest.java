package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.QueryUtility;
import com.kenzie.appserver.controller.model.CustomerCreateRequest;
import com.kenzie.appserver.controller.model.CustomerUpdateRequest;
import com.kenzie.appserver.service.SubscriptionService;
import com.kenzie.appserver.service.model.Customer;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.testcontainers.shaded.org.awaitility.Awaitility.given;
import static sun.nio.cs.Surrogate.is;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@IntegrationTest
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SubscriptionService subscriptionService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private QueryUtility queryUtility;

    @BeforeAll
    public void setup() {
        queryUtility = new QueryUtility(mvc);
    }

    @Test
    public void can_create_newUser_and_Subscribe_Successful() throws Exception{
        //GIVEN
        CustomerCreateRequest createRequest = new CustomerCreateRequest();
        createRequest.setUserId(mockNeat.strings().get());
        createRequest.setName(mockNeat.strings().get());
        createRequest.setDaysOfWeek(mockNeat.strings().get());
        createRequest.setNumOfBins(mockNeat.ints().get());
        createRequest.setPickupTime(mockNeat.strings().get());
        createRequest.setSecondDayOfWeek(mockNeat.strings().get());

        //WHEN
        //THEN
        queryUtility.customerControllerClient.addNewCustomer(createRequest)
                .andExpect(status().isCreated());
    }

    @Test
    public void can_get_user_by_userId() throws Exception{
        //GIVEN
        CustomerCreateRequest createRequest = new CustomerCreateRequest();
        createRequest.setUserId(mockNeat.strings().get());
        createRequest.setName(mockNeat.strings().get());
        createRequest.setDaysOfWeek(mockNeat.strings().get());
        createRequest.setNumOfBins(mockNeat.ints().get());
        createRequest.setPickupTime(mockNeat.strings().get());
        createRequest.setSecondDayOfWeek(mockNeat.strings().get());

        queryUtility.customerControllerClient.getCustomer(String.valueOf(createRequest))
                        .andExpect(status().isCreated());
        //WHEN
        //THEN
        queryUtility.customerControllerClient.getCustomer(createRequest.getUserId())
                .andExpect(status().isOk());
    }

    @Test
    public void can_update_user_by_userId() throws Exception{
        //GIVEN
        CustomerCreateRequest createRequest = new CustomerCreateRequest();
        createRequest.setUserId("1234");
        createRequest.setName("John Doe");
        createRequest.setDaysOfWeek(mockNeat.strings().get());
        createRequest.setNumOfBins(mockNeat.ints().get());
        createRequest.setPickupTime(mockNeat.strings().get());
        createRequest.setSecondDayOfWeek(mockNeat.strings().get());

        String id = createRequest.getUserId();

        queryUtility.customerControllerClient.getCustomer(String.valueOf(createRequest))
                .andExpect(status().isCreated());
        //WHEN
        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest();
        updateRequest.setUserId("4321");
        updateRequest.setName(id);
        updateRequest.setDaysOfWeek("Friday");
        updateRequest.setNumOfBins(2);
        updateRequest.setPickupTime("NOON");
        updateRequest.setSecondDayOfWeek("Tuesday");

        queryUtility.customerControllerClient.updateCustomer(updateRequest)
                .andExpect(status().isCreated());
        //THEN
        queryUtility.customerControllerClient.getCustomer(id)
                .andExpect(status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("userId")
//                        .value(is(Integer.parseInt(updateRequest.getUserId()))));
//                .andExpect(MockMvcResultMatchers.jsonPath("name")
//                        .value(is(Integer.parseInt(updateRequest.getName())))
//                .andExpect(jsonPath("daysOfWeek")
//                        .value(is(Integer.parseInt(updateRequest.getDaysOfWeek())))
//                .andExpect(jsonPath("secondDayOfWeek")
//                        .value(is(Integer.parseInt(updateRequest.getSecondDayOfWeek())))
//                .andExpect(jsonPath("pickupTime")
//                        .value(is(Integer.parseInt(updateRequest.getPickupTime())))
//                .andExpect(jsonPath("numOfBins")
//                        .value(is(updateRequest.getNumOfBins()))))))));

        queryUtility.customerControllerClient.updateCustomer(updateRequest)
                .andExpect(status().isOk());
    }
    @Test
    public void can_cancel_Subscription() throws Exception{
        //GIVEN
        CustomerCreateRequest createRequest = new CustomerCreateRequest();
        createRequest.setUserId(mockNeat.strings().get());
        createRequest.setName(mockNeat.strings().get());
        createRequest.setDaysOfWeek(mockNeat.strings().get());
        createRequest.setNumOfBins(mockNeat.ints().get());
        createRequest.setPickupTime(mockNeat.strings().get());
        createRequest.setSecondDayOfWeek(mockNeat.strings().get());

        queryUtility.customerControllerClient.addNewCustomer(createRequest)
                .andExpect(status().isCreated());

        //WHEN

        queryUtility.customerControllerClient.cancelSubscription(createRequest.getUserId())
                .andExpect(status().isNoContent());
        //THEN

        queryUtility.customerControllerClient.getCustomer(createRequest.getUserId())
                .andExpect(status().isNotFound());
    }
}
