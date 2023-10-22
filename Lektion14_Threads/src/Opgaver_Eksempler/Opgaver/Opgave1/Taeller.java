package Opgaver_Eksempler.Opgaver.Opgave1;


/*
 * Beskrivelse: Mainprogram der opretter 5 tråde og starter udførelsen af dem
 */
public class Taeller {
    public static void main(String args[]) {
        TaellerThread tal1, tal2, tal3, tal4, tal5; // 5 tråde
        tal1 = new TaellerThread(1); tal1.start();
        tal2 = new TaellerThread(2); tal2.start();
        tal3 = new TaellerThread(3); tal3.start();
        tal4 = new TaellerThread(4); tal4.start();
        tal5 = new TaellerThread(5); tal5.start(); } // main()
} // Taeller
