package com.kenzie.appserver;

import com.kenzie.appserver.controller.model.CustomerCreateRequest;
import com.kenzie.appserver.controller.model.CustomerUpdateRequest;
import com.kenzie.appserver.controller.model.ReviewCreateRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;

import static io.micrometer.core.instrument.config.MeterFilter.accept;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class QueryUtility {

    public CustomerControllerClient customerControllerClient;

    private final MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    public QueryUtility(MockMvc mvc){
        this.mvc = mvc;
        this.customerControllerClient = new CustomerControllerClient();
    }

    public class CustomerControllerClient{
        public ResultActions getCustomer(String userId) throws Exception{
            return mvc.perform(get("/example/{userId}", userId)
                .accept(String.valueOf(MediaType.APPLICATION_JSON)));
        }

        public ResultActions addNewCustomer(CustomerCreateRequest customerCreateRequest) throws Exception{
            return mvc.perform(post("/example")
                    .accept(String.valueOf(MediaType.APPLICATION_JSON))
                    .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                    .content(mapper.writeValueAsString(customerCreateRequest)));
        }

        public ResultActions updateCustomer(CustomerUpdateRequest customerUpdateRequest) throws Exception{
            return mvc.perform(put("/example")
                    .accept(String.valueOf(MediaType.APPLICATION_JSON))
                    .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                    .content(mapper.writeValueAsString(customerUpdateRequest)));
        }

        public ResultActions cancelSubscription(String userId) throws Exception{
            return mvc.perform(delete("/example/{userId}/cancel", userId)
                    .accept(String.valueOf(MediaType.APPLICATION_JSON)));
        }

        public ResultActions postNewReview(ReviewCreateRequest reviewCreateRequest) throws Exception{
            return mvc.perform(post("/example/review")
                    .accept(String.valueOf(MediaType.APPLICATION_JSON))
                    .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                    .content(mapper.writeValueAsString(reviewCreateRequest)));
        }

        public ResultActions getAllReviews(ReviewCreateRequest reviewCreateRequest) throws Exception{
            return mvc.perform(get("/example")
                    .accept(String.valueOf(MediaType.APPLICATION_JSON))
                    .contentType(String.valueOf(MediaType.APPLICATION_JSON)));
        }
    }
}
