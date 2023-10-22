package Opgaver_Eksempler.Opgaver.Opgave1;

/*
 * Beskrivelse: Tråd der optæller og udskriver et tal
 */
public class TaellerThread extends Thread {
    int tal;

    /**
     * TaellerThread() constructor
     */
    public TaellerThread(int n) {
        tal = n;
    }

    /**
     * run() er trådens primære metode.
     */
    public void run() {
        for (int k = 0; k < 10; k++) {
            System.out.print(tal);
        } //for
    } // run()
} // TaellerThread
