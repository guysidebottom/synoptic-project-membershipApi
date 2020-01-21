package com.springjpa.springpostgresjpa;

import com.springjpa.springpostgresjpa.exception.EntityCreationException;
import com.springjpa.springpostgresjpa.model.EmployeeCard;
import com.springjpa.springpostgresjpa.repository.EmployeeCardRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeCardTest {


    private int cardNumber = 123456;
    private double balance = 1.0;

    @Mock
    private EmployeeCardRepository mockEmployeeCardRepository;

    @Mock
    private EmployeeCard mockEmployeeCard;

    @Before
    public void setUp() {
        mockEmployeeCard = new EmployeeCard(cardNumber, balance);
    }

    @Test
    public void shouldSaveNewEmployeeCard() {

        mockEmployeeCardRepository.save(mockEmployeeCard);
        verify(mockEmployeeCardRepository, times(1)).save(any(EmployeeCard.class));
    }

    @Test(expected = EntityCreationException.class)
    public void shouldNotCreateEmployeeWhenCardNumberIsNullException() {
        mockEmployeeCardRepository.save(new EmployeeCard(0, balance));
    }

    @Test
    public void employeeCardShouldSetBalance() {
        mockEmployeeCard.setBalance(10);
        mockEmployeeCardRepository.save(mockEmployeeCard);
        assertThat(mockEmployeeCard.getBalance()).isEqualTo(10);

    }

    @Test
    public void employeeCardCreditCannotBeLessThan1() {
        mockEmployeeCard.setBalance(-1.0);
        mockEmployeeCardRepository.save(mockEmployeeCard);
        assertThat(mockEmployeeCard.getBalance()).isEqualTo(0);

    }

    @Test
    public void employeeCardCreditCannotBeMoreThan75() {
        mockEmployeeCard.setBalance(76);
        mockEmployeeCardRepository.save(mockEmployeeCard);
        assertThat(mockEmployeeCard.getBalance()).isEqualTo(0);

    }
}
