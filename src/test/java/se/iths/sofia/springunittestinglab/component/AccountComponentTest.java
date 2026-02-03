package se.iths.sofia.springunittestinglab.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountComponentTest {

    private AccountComponent accountComponent;

    @BeforeEach
    void setup() {
        accountComponent = new AccountComponent(); // Skapa en instans inför varje test

    }


    @Test
    void deposit_shouldIncreaseBalance() {

        int depositAmount = 100; // Arrange

        accountComponent.deposit(depositAmount); // Act

        assertEquals(100, accountComponent.getBalance()); // Assert
    }

    @Test
    void withdraw_shouldDecreaseBalance() {
        accountComponent.deposit(200);
        int withdrawAmount = 50;

        accountComponent.withdraw(withdrawAmount);

        assertEquals(150, accountComponent.getBalance());
    }

    @Test
    void getBalance_shouldReturnCurrentBalance() {

        accountComponent.deposit(300);

        int balance = accountComponent.getBalance();

        assertEquals(300, balance);
    }

    @Test
    void depositAndWithdraw_shouldResultInCorrectBalance() {
        accountComponent.deposit(500);
        accountComponent.withdraw(200);

        assertEquals(300, accountComponent.getBalance());
    }

    @Test
    void shouldCheckThatBalanceIsZeroInitially() {
        assertEquals(0, accountComponent.getBalance());
    }


}