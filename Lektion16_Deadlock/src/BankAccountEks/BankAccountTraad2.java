package BankAccountEks;

public class BankAccountTraad2 extends Thread {

    private BankAccount ba;

    public BankAccountTraad2(BankAccount ba) {
        this.ba = ba;
    }

    public void run() {
        int i = 0;
        while (i < 4) {
            try {
                this.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ba.debit(100);
            i++;
        }
        System.out.println("Balancen er: " + ba.getBalance());
    }
}
