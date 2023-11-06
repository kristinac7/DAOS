package StackOpgaver2;

public class StackTraad2 extends Thread {

    Stack stack;

    public StackTraad2(Stack stack) {
        this.stack = stack;
    }

    public void run() {
        int i = 0;
        while (true) {
            try {
                this.sleep(200); //Bestemmer hastigheden på tråden.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stack.pop();
            i++;
        }
    }
}

