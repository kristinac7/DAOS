package Opgave3_Monitor;

public class HovedKlassen {

    public static void main(String[] args) {

        Faelles faelles = new Faelles();

        MyThread tråd1 = new MyThread("Tråd1", faelles);
        MyThread tråd2 = new MyThread("Tråd2", faelles);

        tråd1.start();
        tråd2.start();

        try {
            tråd1.join();
            tråd2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Tæller: " + faelles.getGlobal());
        System.out.println("Antal lost updates: " + (200 - faelles.getGlobal()));
    }
}
