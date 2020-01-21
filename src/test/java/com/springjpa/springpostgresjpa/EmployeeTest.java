package com.springjpa.springpostgresjpa;

import com.springjpa.springpostgresjpa.exception.EntityCreationException;
import com.springjpa.springpostgresjpa.model.EmployeeEntity;
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

    private String cardId = "r7jTG7dqBy5wGO4L";
    private String name = "someName";
    private String emailAddress = "some@email.address";
    private int phoneNumber = 123456;
    private int pinNumber = 1234;

    @Mock
    private EmployeeRepository mockEmployeeRepository;

    @Mock
    private EmployeeEntity mockEmployee;

    @Before
    public void setUp() {
        mockEmployee = new EmployeeEntity(cardId, name, emailAddress, phoneNumber, pinNumber);
    }

    @Test
    public void shouldSaveNewEmployee() {

        mockEmployeeRepository.save(mockEmployee);
        verify(mockEmployeeRepository, times(1)).save(any(EmployeeEntity.class));
    }

    @Test(expected = EntityCreationException.class)
    public void shouldNotCreateEmployeeWhenCardIdIsInvalid() {
        mockEmployee.setCardId("Invalid");
        mockEmployeeRepository.save(mockEmployee);
    }

    @Test
    public void employeeShouldHaveValidData() {
        mockEmployeeRepository.save(mockEmployee);
        assertThat(mockEmployee.getName()).isNotNull();
        assertThat(mockEmployee.getEmailAddress()).isEqualTo("some@email.address");
        assertThat(mockEmployee.getPhoneNumber()).isEqualTo(123456);
    }


}
