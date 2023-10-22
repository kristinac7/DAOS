package Opgaver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*Opgave 2
        Skriv et Java program, der opretter en opgave i databasen. Opgaven skal oprettes med en
        prepared statement. Oplysninger om opgaven skal indtastes via konsollen.*/

public class Opg2_PreparedStatement {
    private static final Logger logger = Logger.getLogger(Opg2_PreparedStatement.class.getName());

    public static void main(String[] args) {
        try {
            System.out.println("Opret opgave");
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Indtast opgaveNr: ");
            String opgaveNr = inLine.readLine();
            System.out.println("Indtast beskrivelse: ");
            String beskrivelse = inLine.readLine();
            System.out.println("Indtast prioritet: ");
            String prioritet = inLine.readLine();
            System.out.println("Indtast forventet start: ");
            String forventetStart = inLine.readLine();
            System.out.println("Indtast deadline: ");
            String deadline = inLine.readLine();
            System.out.println("Indtast status på opgave: ");
            String statusPaaOpgave = inLine.readLine();
            System.out.println("Indtast projektNr: ");
            String projektNr = inLine.readLine();
            System.out.println("Indtast opgavetypeId: ");
            String opgavetypeId = inLine.readLine();

            Connection minConnection;
            minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=master;user=sa;password=reallyStrongPwd123;");

            String sql = "INSERT INTO Opgave (opgaveNr, beskrivelse, prioritet, forventetStart, deadline, statusPaaOpgave, projektNr, opgavetypeId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement prestmt = minConnection.prepareStatement(sql);

            prestmt.setInt(1, Integer.parseInt(opgaveNr));
            prestmt.setString(2, beskrivelse);
            prestmt.setInt(3, Integer.parseInt(prioritet));
            prestmt.setDate(4, java.sql.Date.valueOf(forventetStart.trim()));
            prestmt.setDate(5, java.sql.Date.valueOf(deadline.trim()));
            prestmt.setString(6, statusPaaOpgave);
            prestmt.setInt(7, Integer.parseInt(projektNr.trim()));
            prestmt.setInt(8, Integer.parseInt(opgavetypeId.trim()));


            // Execute the SQL statement to insert the task
            int rowsAffected = prestmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Opgave oprettet");
            } else {
                System.out.println("Fejl under oprettelse");
            }

            if (prestmt != null)
                prestmt.close();
            if (minConnection != null)
                minConnection.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred:", e); // Logs the error
        }
    }
}
