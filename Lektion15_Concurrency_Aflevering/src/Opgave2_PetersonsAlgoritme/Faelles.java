package Opgave2_PetersonsAlgoritme;

import java.util.Random;

public class Faelles {

    private int global = 0;

    public void TagerRandomTid(int max) {
        Random r = new Random();
        int nymax = Math.abs(r.nextInt()) % max + 1;
        for (int i = 0; i < nymax; i++) {
            for (int j = 0; j < nymax; j++) {
                int result = i + j * i;
            }
        }
    }

    public void kritiskSection() {
        int temp;
        temp = global;
        TagerRandomTid(10);
        global = temp + 1;
    }

    public int getGlobal() {
        return global;
    }
}
