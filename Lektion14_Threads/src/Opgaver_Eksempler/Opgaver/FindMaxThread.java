package Opgaver_Eksempler.Opgaver;

public class FindMaxThread extends Thread {
    private int max;
    private int[] row;

    public FindMaxThread(int[] row) {
        this.row = row;
    }


    public void run() {
        int maxInRow = 0;

        for (int col : row) {
            if (col > maxInRow) {
                maxInRow = col;
            }
        }
        max = maxInRow;
    }

    public int getMax() {
        return max;
    }
}
