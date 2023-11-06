package StackOpgaver2;

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

    public synchronized void push(int n) {
        first = new Element(n, first);
        System.out.println("Element: " + n + " Er nu blevet pushed");
        if (first.next == null) {
            notify();
        }
    }

    public synchronized int pop() {
        //Hvis stakken er tom ventes der på et notify() fra push metoden.
        if (is_empty()) {
            System.out.println("Venter på et push..");
            try {
                wait(); // Det efter wait sker kun efter et notify
                if (!is_empty()) {
                    int n = first.info;
                    first = first.next;
                    System.out.println("Har nu poppet: " + n + " fra stakken");
                    System.out.println("Stakken er nu tom: " + is_empty());
                    System.out.println();
                    return n;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else { // Hvis stakken ikke er tom bliver der poppet.
            int n = first.info;
            first = first.next;
            System.out.println("Har nu poppet: " + n);
            System.out.println("Er stakken tom?: " + is_empty());
            return n;
        }
        return 0;
    }

    public boolean is_empty() {
        return first == null;
    }
}
