package StackOpgaver;

public class Stack {
    private class Element {
        int info;
        Element next;

        Element(int n, Element e) {
            info = n;
            next = e;
        }
    }

    private Element first;

    public Stack() {
        first = null;
    }

    public void push(int n) {
        first = new Element(n, first);
    }

    public int pop() {
        if (first == null) {
            System.out.println("Stacken er tom");
        }
        int n = first.info;
        first = first.next;
        return n;
    }

    public boolean is_empty() {
        return first == null;
    }
}

