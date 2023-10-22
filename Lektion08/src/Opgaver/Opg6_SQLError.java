package Opgaver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*Opgave 6:
        Tage udgangspunkt i opgave 5 fra sidst (JDBC 1) og ændre løsningen så der nu
        programmet nu fanger fejlen triggeren kaster – den du lavede i opgave 5.*/

public class Opg6_SQLError {
    private static final Logger logger = Logger.getLogger(Opg6_SQLError.class.getName());

    public static void main(String[] args) {
        try {
            System.out.println("Opret tidsregistrering");
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Indtast Start tid: ");
            String startTid = inLine.readLine();
            System.out.println("Indtast slut tid: ");
            String slutTid = inLine.readLine();
            System.out.println("Indtast beskrivelse af arbejde");
            String beskrivelseAfArbejde = inLine.readLine();
            System.out.println("Indtast dato registrering: ");
            String datoRegistering = inLine.readLine();
            System.out.println("Indtast medarbejderNr: ");
            String medarbejderNr = inLine.readLine();
            System.out.println("Indtast opgaveNr: ");
            String opgaveNr = inLine.readLine();

            Connection minConnection;
            minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=master;user=sa;password=reallyStrongPwd123;");

            String sql = "INSERT INTO tidsregistrering (startTid, slutTid, beskrivelseAfArbejde, datoRegistering, medarbejderNr, opgaveNr) VALUES (?, ?, ?, ?, ?, ?)";


            PreparedStatement prestmt = minConnection.prepareStatement(sql);

            prestmt.setTime(1, java.sql.Time.valueOf(startTid.trim()));
            prestmt.setTime(2, java.sql.Time.valueOf(slutTid.trim()));
            prestmt.setString(3, beskrivelseAfArbejde);
            prestmt.setDate(4, java.sql.Date.valueOf(datoRegistering.trim()));
            prestmt.setInt(5, Integer.parseInt(medarbejderNr.trim()));
            prestmt.setInt(6, Integer.parseInt(opgaveNr.trim()));

            prestmt.executeUpdate();
            System.out.println("Tidsregistrering oprettet");

            if (prestmt != null)
                prestmt.close();
            if (minConnection != null)
                minConnection.close();
        } catch (SQLException e) {
            System.out.println("fejl:  " + e.getMessage());
            System.out.println("fejl:  " + e.getErrorCode());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred:", e);
        }
    }
}
