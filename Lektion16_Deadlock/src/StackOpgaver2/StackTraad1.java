package StackOpgaver2;


public class StackTraad1 extends Thread {

    Stack stack;

    public StackTraad1(Stack stack) {
        this.stack = stack;
    }

    public void run() {
        int i = 0;
        while (true) {
            try {
                this.sleep(1000); //Bestemmer hastigheden på tråden.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stack.push(i);
            i++;
        }
    }
}
