package Opgave2_PetersonsAlgoritme;

public class HovedKlassen {

    private static int turn;
    private static boolean[] flag;

    public static void main(String[] args) {
        flag = new boolean[2];
        flag[0] = false;
        flag[1] = false;
        turn = 1;

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

    public static boolean getFlag(int index) {
        return flag[index];
    }

    public static void setFlag(int index, boolean b) {
        flag[index] = b;
    }

    public static int getTurn() {
        return turn;
    }

    public static void setTurn(int setValue) {
        turn = setValue;
    }
}
