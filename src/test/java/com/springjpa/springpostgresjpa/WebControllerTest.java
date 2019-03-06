package com.springjpa.springpostgresjpa;

import com.springjpa.springpostgresjpa.model.Employee;
import com.springjpa.springpostgresjpa.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class WebControllerTest {

    @Mock
    private EmployeeRepository mockEmployeeRepository;

    @Before
    public void setUp() {

        mockEmployeeRepository.save(new Employee("someName", "someEmail", 123456, 0000));
    }

    @Test
    public void shouldReturnName() {
        assertTrue(mockEmployeeRepository.findByName("jhkjh").equals("kjbkjbkj"));
    }


}
