package StackOpgaver;

public class TestTrådklasse {

    public static void main(String[] args) {

        Stack stack = new Stack();

        Trådklasse1 tråd1 = new Trådklasse1(stack);
        Trådklasse2 tråd2 = new Trådklasse2(stack);

        tråd1.start();
        tråd2.start();

        try {
            tråd1.join();
            tråd2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
