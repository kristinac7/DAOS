package Opgaver_Eksempler.Opgaver;

public class FindMaxThread extends Thread{
    private int max;
    private int[] row;

    public FindMaxThread(int[] row) {
        this.row = row;
    }


    public void run() {
        int maxInRow = Integer.MIN_VALUE; // Initialiser maksimum for denne række til en meget lav værdi

        for (int col : row) {
            if (col > maxInRow) {
                maxInRow = col; // Opdater maksimum for denne række, hvis det aktuelle element er større
            }
        }

        max = maxInRow; // Opdater den maksimale værdi for denne tråd
    }



    public int getMax() {
        return max;
    }
}
