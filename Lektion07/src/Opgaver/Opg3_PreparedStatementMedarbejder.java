package Opgaver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class Opg3_PreparedStatementMedarbejder {

    /*Opgave 3:
    Skriv et Java program, der forbinder til databasen Tidsregistrering. Programmet skal finde
    telefonnummer på en medarbejder, hvis navn bliver indtastet i konsollen. Programmet skal
    laves ved brug af en prepared statement.*/

    public static void main(String[] args) {
        try {
            System.out.println("Indtast navn: ");
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            String navn = inLine.readLine();

            Connection minConnection;
            minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=master;user=sa;password=reallyStrongPwd123;");

            // Modifies your SQL query to use a placeholder "?"
            String sql = "SELECT mobil FROM Medarbejder WHERE navn=?";
            PreparedStatement prestmt = minConnection.prepareStatement(sql);

            // Sets the parameter value
            prestmt.setString(1, navn);

            ResultSet resultSet = prestmt.executeQuery();

            if (resultSet.next()) {
                String telefonNr = resultSet.getString("mobil");
                System.out.println("TelefonNr fundet: " + telefonNr);
            } else {
                System.out.println("Ingen resultater fundet for navnet: " + navn);
            }

            if (prestmt != null)
                prestmt.close();
            if (minConnection != null)
                minConnection.close();
        } catch (Exception e) {
            System.out.println("fejl:  " + e.getMessage());
        }
    }
}
