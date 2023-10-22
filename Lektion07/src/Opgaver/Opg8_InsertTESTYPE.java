package Opgaver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*Opgave 8:
        Opret en ny tabel TESTTYPE på SQL-serveren. Tabellen skal indeholde en attribut af hver
        af følgende typer: CHAR, VARCHAR, INT, DECIMAL, DATE og BIT.
        Skriv et Java-program der indsætter en række i tabellen TESTTYPE. Værdierne der skal
        indsættes i tabellen indlæses fra tastaturet.*/

public class Opg8_InsertTESTYPE {

    private static final Logger logger = Logger.getLogger(Opg8_InsertTESTYPE.class.getName());

    public static void main(String[] args) {
        try {
            Connection minConnection;
            minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=master;user=sa;password=reallyStrongPwd123;");

            // SQL-indsætningskommando
            String sql = "INSERT INTO TESTTYPE (char_column, varchar_column, int_column, decimal_column, date_column, bit_column) VALUES (?, ?, ?, ?, ?, ?)";

            // Forbered en PreparedStatement til at udføre kommandoen
            PreparedStatement preparedStatement = minConnection.prepareStatement(sql);

            // Indlæs værdier fra tastaturet
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Indtast char-værdi: ");
            String charValue = inLine.readLine();
            System.out.print("Indtast varchar-værdi: ");
            String varcharValue = inLine.readLine();
            System.out.print("Indtast int-værdi: ");
            String intValue = inLine.readLine();
            System.out.print("Indtast decimal-værdi: ");
            String decimalValue = inLine.readLine();
            System.out.print("Indtast date-værdi (YYYY-MM-DD): ");
            String dateValue = inLine.readLine();
            System.out.print("Indtast bit-værdi (0 eller 1): ");
            String bitValue = inLine.readLine();

            // Sæt parameterne i PreparedStatement
            preparedStatement.setString(1, charValue);
            preparedStatement.setString(2, varcharValue);
            preparedStatement.setInt(3, Integer.parseInt(intValue));
            preparedStatement.setDouble(4, Double.parseDouble(decimalValue));
            preparedStatement.setDate(5, Date.valueOf(dateValue));
            preparedStatement.setBoolean(6, Boolean.parseBoolean(bitValue));

            // Udfør SQL-indsætning
            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println(rowsAffected + " rækker er blevet indsat.");

            // Luk forbindelsen og ressourcerne
            preparedStatement.close();
            minConnection.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred:", e);
        }
    }
}
