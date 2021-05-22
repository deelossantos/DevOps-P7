package revolut;

import java.util.Currency;

public class Account {
    private Currency accCurrency;
    private double balance;

    public Account(Currency currency, double balance){
        this.balance = balance;
        this.accCurrency = currency;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void addFunds(double topUpAmount) {
        this.balance += topUpAmount;
    }

    public void sendFunds(double sendAmount, Account receiver) {
        if (this.balance>=sendAmount) {
            receiver.addFunds(sendAmount);
            this.balance -= sendAmount;
        }
        else {System.out.println("Not enough balance to complate the operation");}
    }
}
