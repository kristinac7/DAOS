package Opgave2_PetersonsAlgoritme;

public class MyThread extends Thread {

    private Faelles faelles;
    private String navn;

    public MyThread(String navn, Faelles faelles) {
        this.navn = navn;
        this.faelles = faelles;
    }

    public void run() {
        for (int j = 0; j < 100; j++) {
            if (HovedKlassen.getTurn() == 1) {
                HovedKlassen.setFlag(1, true);
                HovedKlassen.setTurn(0);

                while (HovedKlassen.getFlag(0) && HovedKlassen.getTurn() == 0) {
                }
                faelles.kritiskSection();
                faelles.TagerRandomTid(10);
                HovedKlassen.setFlag(1, false);
            } else {
                HovedKlassen.setFlag(0, true);
                HovedKlassen.setTurn(1);

                while (HovedKlassen.getFlag(1) && HovedKlassen.getTurn() == 1) {
                }
                faelles.kritiskSection();
                faelles.TagerRandomTid(10);
                HovedKlassen.setFlag(0, false);
            }
        }
        System.out.println(navn + ": " + faelles.getGlobal());
    }
}
