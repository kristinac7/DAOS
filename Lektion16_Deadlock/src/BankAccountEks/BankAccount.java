package BankAccountEks;

public class BankAccount {

    private double balance;

    public synchronized void credit(double amount) {
        balance = balance + amount;
        System.out.println("Kredit: " + balance);
        notify();     // Kalder på en tilfældig ventende tråd
    }

    public synchronized void debit(double amount) {
        if (balance - amount >= 0) {
            balance = balance - amount;
            System.out.println("Debit: " + balance);
        } else {
            try {
                wait();
                System.out.println("Venter");
                if (balance - amount >= 0) {
                    balance = balance - amount;
                    System.out.println("Debit: " + balance);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public double getBalance() {
        return balance;
    }
}
