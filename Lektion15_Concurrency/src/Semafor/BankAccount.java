package Semafor;

import java.util.concurrent.Semaphore;

public class BankAccount {

    private double balance;
    private Semaphore semaphore = new Semaphore(1); // initialiserer heltalsværdi - positiv = åben

    public void setBalance(double amount, String action) {
        try {
            semaphore.acquire();

            if (action.equals("c")) {
                balance = balance + amount;
            }
            if (action.equals("d")) {
                balance = balance - amount;
            }

            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public double getBalance() {
        return balance;
    }
}
