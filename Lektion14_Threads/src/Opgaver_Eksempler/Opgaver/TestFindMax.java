package Opgaver_Eksempler.Opgaver;

import java.util.Random;

public class TestFindMax {
    private static final int rowindex = 2;
    private static final int colindex = 20000000;
    private static int[][] board = new int[rowindex][colindex];


    public static void main(String[] args) {
        fillBoard();
// printBoard();
        long l1 = System.nanoTime();
        System.out.println("Max: " + findMax());
        try {
            System.out.println("Max: " +
                    findMaxThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long l2 = System.nanoTime();
        System.out.println("Køretiden var " + (l2 - l1) / 1000000
                + " millisekunder");
    }


    public static int findMax() {
        int max = board[0][0];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] > max) {
                    max = board[i][j];
                }
            }
        }
        return max;
    }

    public static int findMaxThread() throws InterruptedException {
        int numThreads = board.length;
        FindMaxThread[] threads = new FindMaxThread[numThreads];

        // Start en tråd for hver række
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new FindMaxThread(board[i]);
            threads[i].start();
        }

        // Vent på, at alle tråde er færdige
        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
        }

        // Find det maksimale resultat fra trådene
        int max = 0;
        for (int i = 0; i < numThreads; i++) {
            int threadMax = threads[i].getMax();
            if (threadMax > max) {
                max = threadMax;
            }
        }
        return max;
    }


    public static void fillBoard() {
        Random rand = new Random();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length;
                 col++) {
                board[row][col] = rand.nextInt(1000);
            }
        }
    }

    public static void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
