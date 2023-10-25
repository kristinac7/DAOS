package Opgave4_Semafor;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Faelles {

    private int global = 0;
    private Semaphore semaphore = new Semaphore(1);

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
        try {
            semaphore.acquire();

            int temp;
            temp = global;
            TagerRandomTid(10);
            global = temp + 1;

            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getGlobal() {
        return global;
    }
}
