package Opgaver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Opg6_PreparedStatement {

    private static final Logger logger = Logger.getLogger(Opg6_PreparedStatement.class.getName());

    public static void main(String[] args) {
        try {
            System.out.println("Opret medarbejder");
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Indtast navn: ");
            String navn = inLine.readLine();
            System.out.println("Indtast stillingsbetegnelse: ");
            String stillingsBetegnelse = inLine.readLine();
            System.out.println("Indtast telefonnummer:");
            String telefonnummer = inLine.readLine();

            Connection minConnection;
            minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=master;user=sa;password=reallyStrongPwd123;");

            // Retrieve the maximum medarbejderNr from the database
            String maxMedarbejderNrSQL = "SELECT MAX(medarbejderNr) FROM Medarbejder";
            PreparedStatement maxMedarbejderNrStmt = minConnection.prepareStatement(maxMedarbejderNrSQL);
            ResultSet maxMedarbejderNrResult = maxMedarbejderNrStmt.executeQuery();

            int medarbejderNr = 1; // Default value if no records are found
            if (maxMedarbejderNrResult.next()) {
                medarbejderNr = maxMedarbejderNrResult.getInt(1) + 1;
            }

            String insertSQL = "INSERT INTO Medarbejder (medarbejderNr, navn, stillingsBetegnelse, mobil) VALUES (?, ?, ?, ?)";
            PreparedStatement prestmt = minConnection.prepareStatement(insertSQL);

            prestmt.setInt(1, medarbejderNr);
            prestmt.setString(2, navn);
            prestmt.setString(3, stillingsBetegnelse);
            prestmt.setString(4, telefonnummer);

            prestmt.executeUpdate();
            System.out.println("Medarbejder oprettet");

            if (maxMedarbejderNrResult != null)
                maxMedarbejderNrResult.close();
            if (maxMedarbejderNrStmt != null)
                maxMedarbejderNrStmt.close();
            if (prestmt != null)
                prestmt.close();
            if (minConnection != null)
                minConnection.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred:", e);
        }
    }
}
