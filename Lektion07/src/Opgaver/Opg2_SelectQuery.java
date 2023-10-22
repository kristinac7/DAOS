package Opgaver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Opg2_SelectQuery {

    /*Opgave 2:
    Skriv et Java program, der forbinder til databasen Tidsregistrering, udfører en query på
    tabellen Medarbejder og udskriver navn, telefonnummer på medarbejderne i tabellen.*/

    public static void main(String[] args) {

        try {
            Connection minConnection;
            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://localhost;databaseName=master;user=sa;password=reallyStrongPwd123;");

            Statement stmt = minConnection.createStatement();

            ResultSet res = stmt.executeQuery("SELECT navn, mobil FROM Medarbejder");
            while (res.next()) {
                System.out.println(res.getString(1) + "\t" + res.getString(2));
            }

            if (res != null)
                res.close();
            if (stmt != null)
                stmt.close();
            if (minConnection != null)
                minConnection.close();
        } catch (Exception e) {
            System.out.println("fejl:  " + e.getMessage());
        }
    }
}
