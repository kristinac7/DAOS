package Opgaver_Eksempler.Opgaver.Opgave4;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LottoUdtrækning {

    public static void main(String[] args) {

        int antalLottoRækker = 1000000;
        int numThreads = 4; // Number of threads to use


        ArrayList<Lottoraek> lottoRækker = new ArrayList<>();
        Lottoraek lottoUdtrækning = genererLottoUdtrækning();

        for (int i = 0; i < antalLottoRækker; i++) {
            Lottoraek lottoraek = new Lottoraek();
            lottoRækker.add(lottoraek);

        }

        long startTid = System.currentTimeMillis();

        ArrayList<LottoUdtrækningThread> threads = new ArrayList<>();
        int[] korrekteTal = new int[8]; // 1-7


        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (Lottoraek række : lottoRækker) {
            LottoUdtrækningThread thread = new LottoUdtrækningThread(række, lottoUdtrækning, korrekteTal);
            threads.add(thread);
            executor.submit(thread);
        }
        executor.shutdown();


        for (LottoUdtrækningThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long slutTid = System.currentTimeMillis();
        long executionTime = slutTid - startTid;

        // Resultat
        int sum = 0;
        for (int i = 0; i <= 7; i++) {
            System.out.println("Antal rækker med " + i + " korrekte tal: " + korrekteTal[i]);
            sum += korrekteTal[i];
        }

        System.out.println("Summen: " + sum);
        System.out.println("Execution time: " + executionTime + " milliseconds");
    }


    public static Lottoraek genererLottoUdtrækning() {
        Lottoraek udtræk = new Lottoraek();
        return udtræk;
    }
}
