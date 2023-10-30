package StackOpgaver;

public class Trådklasse1 extends Thread {

    private Stack stack;

    public Trådklasse1(Stack stack) {
        this.stack = stack;
    }

    public void run() {
        for (int i = 1; i <= 4; i++) {
            stack.push(i);
            System.out.println("Pushed: " + i);
            synchronized (stack) {
                stack.notify();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

