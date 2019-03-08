package com.springjpa.springpostgresjpa;

import com.springjpa.springpostgresjpa.exception.EntityCreationException;
import com.springjpa.springpostgresjpa.model.Employee;
import com.springjpa.springpostgresjpa.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeTest {

    private String name = "someName";
    private String emailAddress = "some@email.address";
    private int phoneNumber = 123456;
    private int pinNumber = 1234;
    private int cardNumber = 123456;

    @Mock
    private EmployeeRepository mockEmployeeRepository;

    @Mock
    private Employee mockEmployee;

    @Before
    public void setUp() {
        mockEmployee = new Employee(name, emailAddress, phoneNumber, pinNumber, cardNumber);
    }

    @Test
    public void shouldSaveNewEmployee() {

        mockEmployeeRepository.save(mockEmployee);
        verify(mockEmployeeRepository, times(1)).save(any(Employee.class));
    }

    @Test(expected = EntityCreationException.class)
    public void shouldNotCreateEmployeeWhenNameIsNullException() {
        mockEmployeeRepository.save(new Employee(null, emailAddress, phoneNumber, pinNumber, cardNumber));
    }

    @Test
    public void employeeShouldHaveValidData() {
        mockEmployeeRepository.save(mockEmployee);
        assertThat(mockEmployee.getName()).isNotNull();
        assertThat(mockEmployee.getEmailAddress()).isEqualTo("some@email.address");
        assertThat(mockEmployee.getPhoneNumber()).isEqualTo(123456);

    }


}
