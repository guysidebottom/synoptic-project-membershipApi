package com.springjpa.springpostgresjpa;

import com.springjpa.springpostgresjpa.exception.EntityCreationException;
import com.springjpa.springpostgresjpa.exception.RecordNotFoundException;
import com.springjpa.springpostgresjpa.model.EmployeeEntity;
import com.springjpa.springpostgresjpa.model.EmployeeService;
import com.springjpa.springpostgresjpa.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    private int id = 1;
    private String cardId = "r7jTG7dqBy5wGO4L";
    private String name = "Test Guy";
    private String emailAddress = "tester@test.com";
    private String phoneNumber = "01132981111";
    private int pinNumber = 1234;

    @Mock
    private EmployeeRepository mockEmployeeRepository;

    private EmployeeEntity employeeEntity;

    private EmployeeService employeeService;

    @Before
    public void setup() {
        employeeEntity = new EmployeeEntity(id, cardId, name, emailAddress, phoneNumber, pinNumber);
        employeeService = new EmployeeService();
        employeeService.setRepository(mockEmployeeRepository);
    }

    @Test
    public void shouldFindEmployeeById() throws RecordNotFoundException {
        when(mockEmployeeRepository.findById(id)).thenReturn(Optional.of(employeeEntity));

        EmployeeEntity employee = employeeService.getEmployeeById(1);

        assertThat(employee).isNotNull();
        assertThat(employee.getName()).isEqualTo("Test Guy");
        assertThat(employee.getEmailAddress()).isEqualTo("tester@test.com");
        assertThat(employee.getPhoneNumber()).isEqualTo("01132981111");
        assertThat(employee.getPinNumber()).isEqualTo(1234);
    }

    // Mockito lenient employed to bypass strict ruling of unnecessary stub call
    @Test(expected = RecordNotFoundException.class)
    public void shouldThrowRecordNotFoundExceptionWhenEmployeeNotFound() throws RecordNotFoundException {
        lenient().when(mockEmployeeRepository.findById(id)).thenReturn(Optional.of(employeeEntity));
        employeeService.getEmployeeById(2);
    }

    @Test
    public void shouldFindEmployeeByCardId() throws RecordNotFoundException {
        when(mockEmployeeRepository.findByCardId(cardId)).thenReturn(Optional.of(employeeEntity));

        EmployeeEntity employee = employeeService.getEmployeeByCardId("r7jTG7dqBy5wGO4L");

        assertThat(employee).isNotNull();
        assertThat(employee.getName()).isEqualTo("Test Guy");
        assertThat(employee.getEmailAddress()).isEqualTo("tester@test.com");
        assertThat(employee.getPhoneNumber()).isEqualTo("01132981111");
        assertThat(employee.getPinNumber()).isEqualTo(1234);
    }

    @Test(expected = RecordNotFoundException.class)
    public void shouldThrowRecordNotFoundExceptionWhenEmployeeNotFoundByCardId() throws RecordNotFoundException {
        lenient().when(mockEmployeeRepository.findByCardId(cardId)).thenReturn(Optional.of(employeeEntity));
        employeeService.getEmployeeByCardId("XXX");
    }

    @Test
    public void shouldTopUpBalance() throws RecordNotFoundException {
        when(mockEmployeeRepository.findByCardId(cardId)).thenReturn(Optional.of(employeeEntity));
        employeeEntity.setLoggedIn(true);
        employeeService.topUpBalance(employeeEntity.getCardId(), 10.0);
        assertThat(employeeEntity.getBalance()).isEqualTo(10.0);
    }

    @Test(expected = EntityCreationException.class)
    public void shouldThrowEntityCreationExceptionWhenNotAuthenticated() throws RecordNotFoundException {
        when(mockEmployeeRepository.findByCardId(cardId)).thenReturn(Optional.of(employeeEntity));
        employeeEntity.setLoggedIn(false);
        employeeService.topUpBalance(employeeEntity.getCardId(), 10.0);
    }

    @Test(expected = EntityCreationException.class)
    public void shouldThrowEntityCreationExceptionWhenInvalidTopUpAmount() throws RecordNotFoundException {
        when(mockEmployeeRepository.findByCardId(cardId)).thenReturn(Optional.of(employeeEntity));
        employeeEntity.setLoggedIn(true);
        employeeService.topUpBalance(employeeEntity.getCardId(), 100.01);
    }


}
