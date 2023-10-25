package Opgaver_Eksempler.Opgaver.Opgave4;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LottoUdtrækning {
    public static void main(String[] args) {
        int antalLottoRækker = 10000000;
        int numThreads = 2;

        ArrayList<Lottoraek> lottoRækker = new ArrayList<>();
        int[] korrekteTal = new int[8];

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        long startTid = System.currentTimeMillis();

        for (int i = 0; i < antalLottoRækker; i++) {
            Lottoraek lottoraek = new Lottoraek();
            LottoUdtrækningThread thread = new LottoUdtrækningThread(lottoraek, genererLottoUdtrækning(), korrekteTal);
            executor.submit(thread);
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
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

