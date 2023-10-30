package StackOpgaver;

public class Trådklasse2 extends Thread {

    private Stack stack;

    public Trådklasse2(Stack stack) {
        this.stack = stack;
    }

    public void run() {
        for (int i = 1; i <= 4; i++) {
            synchronized (stack) {
                while (stack.is_empty()) {
                    try {
                        stack.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int popped = stack.pop();
                System.out.println("Popped: " + popped);
            }
        }
    }
}
