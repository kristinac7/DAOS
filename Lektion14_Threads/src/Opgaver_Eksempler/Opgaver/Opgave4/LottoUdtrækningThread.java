package Opgaver_Eksempler.Opgaver.Opgave4;

public class LottoUdtrækningThread extends Thread {

    private Lottoraek række;
    private Lottoraek udtækning;
    int[] korrekteTal = new int[8];

    public LottoUdtrækningThread(Lottoraek række, Lottoraek udtækning, int[] korrekteTal) {
        this.række = række;
        this.udtækning = udtækning;
        this.korrekteTal = korrekteTal;
    }

    public void run() {
        int korrekte = række.antalrigtige(udtækning);
        synchronized (korrekteTal) {
            korrekteTal[korrekte]++;
        }

    }
}
