package Opgaver_Eksempler.Opgaver.Opgave3;

import java.util.ArrayList;

public class LottoUdtrækning {

    public static void main(String[] args) {

        int antalLottoRækker = 10000000;

        ArrayList<Lottoraek> lottoRækker = new ArrayList<>();
        Lottoraek lottoUdtrækning = genererLottoUdtrækning();

        for (int i = 0; i < antalLottoRækker; i++) {
            Lottoraek lottoraek = new Lottoraek();
            lottoRækker.add(lottoraek);

        }

        long startTid = System.currentTimeMillis();
        int[] korrekteTal = new int[8]; // 1-7

        for (Lottoraek række : lottoRækker) {
            int korrekte = række.antalrigtige(lottoUdtrækning);
            korrekteTal[korrekte]++;
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
