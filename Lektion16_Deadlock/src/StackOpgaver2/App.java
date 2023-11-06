package StackOpgaver2;

public class App {
    public static void main(String[] args) {

        Stack stack = new Stack();
        StackTraad1 stackTraad1 = new StackTraad1(stack);
        StackTraad2 stackTraad2 = new StackTraad2(stack);

        stackTraad1.start();
        stackTraad2.start();

        try {
            stackTraad1.join();
            stackTraad2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Er stacken tom?: " + stack.is_empty());
    }
}



