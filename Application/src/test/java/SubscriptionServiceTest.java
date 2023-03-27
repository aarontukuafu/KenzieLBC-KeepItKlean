import com.kenzie.appserver.repositories.CustomerRecordRepository;
import com.kenzie.appserver.repositories.model.CustomerRecord;
import com.kenzie.appserver.service.SubscriptionService;
import com.kenzie.appserver.service.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SubscriptionServiceTest {
    private CustomerRecordRepository customerRecordRepository;
    private SubscriptionService subscriptionService;

    @BeforeEach
    void setup(){
        customerRecordRepository = mock(CustomerRecordRepository.class);
        subscriptionService = new SubscriptionService(customerRecordRepository);
    }
    @Test
    public void addNewCustomer(){
        //GIVEN
        Customer customer = new Customer("1234", "John Doe", "Monday", "NOON", 1);

        CustomerRecord record = new CustomerRecord();
        record.setUserId("1234");
        record.setName("John Doe");
        record.setDaysOfWeek("Monday");
        record.setPickupTime("NOON");
        record.setNumOfBins(1);

        when(customerRecordRepository.save(record)).thenReturn(record);

        //WHEN
        Customer result = subscriptionService.addNewCustomer(customer);

        //THEN
        assertNotNull(result, "The result is not null");
        assertEquals(customer.getUserId(), result.getUserId(), "The user ID matches");
        assertEquals(customer.getName(), result.getName(), "The name matches");
        assertEquals(customer.getDaysOfWeek(), result.getDaysOfWeek(), "The days of week matches");
        assertEquals(customer.getPickupTime(), result.getPickupTime(), "The pickup time matches");
        assertEquals(customer.getNumOfBins(), result.getNumOfBins(), "The number of bins matches");
    }


}
