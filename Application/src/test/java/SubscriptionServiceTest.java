import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.repositories.CustomerRecordRepository;
import com.kenzie.appserver.repositories.model.CustomerRecord;
import com.kenzie.appserver.service.SubscriptionService;
import com.kenzie.appserver.service.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SubscriptionServiceTest {
    private CustomerRecordRepository customerRecordRepository;
    private SubscriptionService subscriptionService;
    private CacheStore cache;
    private DynamoDBMapper dynamoDBMapper;

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

    @Test
    public void testCancelService(){
        //GIVEN
        SubscriptionService subscriptionService1 = new SubscriptionService(customerRecordRepository);
        Customer customer = new Customer("1234", "John", "Monday", "NOON", 2);

        //WHEN
        subscriptionService1.cancelService(customer);

        //THEN
        verify(customerRecordRepository, times(1)).deleteById("1234");

    }

    @Test
    public void canDeleteBin() {
        // GIVEN
        Customer customer = new Customer("1234", "John", "Monday", "NOON", 2);

        when(customerRecordRepository.existsById(customer.getUserId())).thenReturn(true);

        // WHEN
        subscriptionService.deleteBin(customer);

        // THEN
        verify(customerRecordRepository, times(1)).existsById(customer.getUserId());
        verify(dynamoDBMapper, times(1)).delete(customer.getNumOfBins());
    }

    @Test
    public void canAddReview() {
        // GIVEN
        Customer customer = new Customer("1234", "John", "Monday", "NOON", 2);

        when(customerRecordRepository.existsById(customer.getUserId())).thenReturn(true);

        // WHEN
        Map<String, String> result = subscriptionService.addReview(customer);

        // THEN
        assertEquals(1, result.size());
        assertTrue(result.containsKey(customer.getName()));
    }

    @Test
    public void canUpdateCustomer() {
        // GIVEN
        Customer existingCustomer = new Customer("1234", "John Doe", "Monday", "NOON", 1);
        when(customerRecordRepository.existsById(existingCustomer.getUserId())).thenReturn(true);

        Customer updatedCustomer = new Customer("1234", "Jane Doe", "Tuesday", "MORNING", 2);

        // WHEN
        subscriptionService.updateCustomer(updatedCustomer);

        // THEN
        ArgumentCaptor<CustomerRecord> captor = ArgumentCaptor.forClass(CustomerRecord.class);
        verify(customerRecordRepository, times(1)).save(captor.capture());
        CustomerRecord savedRecord = captor.getValue();

        assertEquals(updatedCustomer.getUserId(), savedRecord.getUserId());
        assertEquals(updatedCustomer.getName(), savedRecord.getName());
        assertEquals(updatedCustomer.getDaysOfWeek(), savedRecord.getDaysOfWeek());
        assertEquals(updatedCustomer.getPickupTime(), savedRecord.getPickupTime());
        assertEquals(updatedCustomer.getNumOfBins(), savedRecord.getNumOfBins());

        verify(cache, times(1)).evict(existingCustomer.getUserId());
    }


}
