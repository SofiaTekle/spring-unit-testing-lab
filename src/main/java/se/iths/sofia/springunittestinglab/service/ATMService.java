package se.iths.sofia.springunittestinglab.service;

import org.springframework.stereotype.Service;
import se.iths.sofia.springunittestinglab.component.AccountComponent;
import se.iths.sofia.springunittestinglab.exception.InsufficientFundsException;
import se.iths.sofia.springunittestinglab.exception.InvalidAmountException;
import se.iths.sofia.springunittestinglab.exception.MaxWithdrawalExceededException;

@Service
public class ATMService {


    private static final int MAX_WITHDRAWAL_AMOUNT = 10000;

    private final AccountComponent accountComponent;

    public ATMService(AccountComponent accountComponent) {
        this.accountComponent = accountComponent;
    }


    public void depositMoney(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero.");
        }
        accountComponent.deposit(amount);
    }


    public void withdrawMoney(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
        }

        if (amount > MAX_WITHDRAWAL_AMOUNT) {
            throw new MaxWithdrawalExceededException("Withdrawal amount exceeds the maximum limit of " + MAX_WITHDRAWAL_AMOUNT);
        }

        if (amount > accountComponent.getBalance()) {
            throw new InsufficientFundsException("Withdrawal amount exceeds available balance.");
        }
        accountComponent.withdraw(amount);
    }

    public int getAccountBalance() {
        return accountComponent.getBalance();
    }


}
