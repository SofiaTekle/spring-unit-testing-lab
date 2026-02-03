package se.iths.sofia.springunittestinglab.component;

public class AccountComponent {

    private int balance = 0;


    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }


}
