package com.springjpa.springpostgresjpa;

import com.springjpa.springpostgresjpa.exception.EntityCreationException;
import com.springjpa.springpostgresjpa.model.EmployeeEntity;
import com.springjpa.springpostgresjpa.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeEntityTest {

    private int id = 1;
    private String cardId = "r7jTG7dqBy5wGO4L";
    private String name = "someName";
    private String emailAddress = "some@email.address";
    private String phoneNumber = "01139009007";
    private int pinNumber = 1234;

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeEntity employee;

    @Before
    public void setUp() {
        employee = new EmployeeEntity(id, cardId, name, emailAddress, phoneNumber, pinNumber);
    }

    @Test
    public void shouldSaveNewEmployee() {

        employeeRepository.save(employee);
        verify(employeeRepository, times(1)).save(any(EmployeeEntity.class));
    }

    @Test(expected = EntityCreationException.class)
    public void shouldNotCreateEmployeeWhenCardIdIsInvalid() {
        employee.setCardId("Invalid");
        employeeRepository.save(employee);
    }

    @Test
    public void employeeShouldHaveValidData() {
        employeeRepository.save(employee);
        assertThat(employee.getName()).isNotNull();
        assertThat(employee.getEmailAddress()).isEqualTo(emailAddress);
        assertThat(employee.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @Test(expected = EntityCreationException.class)
    public void shouldThrowExceptionForNonValidEmail() {
        employee.setEmailAddress("xxxATxxx.com");
    }

    @Test(expected = EntityCreationException.class)
    public void shouldThrowExceptionForNonValidCardId() {
        employee.setCardId("AAbbCC123");
    }


}
