package se.iths.sofia.springunittestinglab.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.sofia.springunittestinglab.component.AccountComponent;
import se.iths.sofia.springunittestinglab.exception.InsufficientFundsException;
import se.iths.sofia.springunittestinglab.exception.InvalidAmountException;
import se.iths.sofia.springunittestinglab.exception.MaxWithdrawalExceededException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ATMServiceTest {

    // klass som ska testas
    @InjectMocks
    ATMService atmService;
    // klass som ska mockas
    @Mock
    AccountComponent accountComponent;

    // --- Felaktiga flöden (exceptions) ---

    @Test
    void testDepositMoneyWithZeroAmount() {
        int amount = 0;

        assertThrows(InvalidAmountException.class, () -> {
            atmService.depositMoney(amount);
        });
    }

    @Test
    void testDepositMoneyWithNegativeAmount() {
        int amount = -100;

        assertThrows(InvalidAmountException.class, () -> {
            atmService.depositMoney(amount);
        });
    }


    @Test
    void testWithdrawMoneyWithZeroAmount() {
        int amount = 0;

        assertThrows(InvalidAmountException.class, () -> {
            atmService.withdrawMoney(amount);
        });
    }

    @Test
    void testWithdrawMoneyGreaterThanMaxAmount() {
        int amountOverMax = 10001;

        assertThrows(MaxWithdrawalExceededException.class, () -> {
            atmService.withdrawMoney(amountOverMax);
        });
    }

    @Test
    void testWithdrawMoneyIfAmountGreaterThanBalance() {
        int amount = 5000;
        int balance = 3000;

        when(accountComponent.getBalance()).thenReturn(balance);

        assertThrows(InsufficientFundsException.class, () -> {
            atmService.withdrawMoney(amount);
        });
    }

    // --- Normalt flöde (korrekt beteende) ---


    @Test
    void shouldDepositMoneySuccessfully() {
        int amount = 500;

        atmService.depositMoney(amount);

        verify(accountComponent).deposit(amount);
    }

    @Test
    void shouldWithdrawMoneySuccessfully() {
        int amount = 500;
        int balance = 1000;

        when(accountComponent.getBalance()).thenReturn(balance);

        atmService.withdrawMoney(amount);

        verify(accountComponent).withdraw(amount);
    }

    @Test
    void shouldGetAccountBalanceSuccessfully() {
        int balance = 2000;
        when(accountComponent.getBalance()).thenReturn(balance);

        int result = atmService.getAccountBalance();

        assertEquals(balance, result);

    }


}