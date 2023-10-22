package Opgaver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*Opgave 1:
        Skriv et Java program, der forbinder til databasen Tidsregistrering, udfører en query på
        tabellen Opgave og som resultat udskriver alle kolonner og alle rækker, der er i Opgave
        tabellen*/

public class Opg1_Opgave {

    public static void main(String[] args) {

        try {
            Connection minConnection;
            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://localhost;databaseName=master;user=sa;password=reallyStrongPwd123;");

            Statement stmt = minConnection.createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM Opgave");

            while (res.next()) {
                System.out.println(res.getString(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t" + res.getString(4) +
                        "\t" + res.getString(5) + "\t" + res.getString(6) + "\t" + res.getString(7) + "\t" + res.getString(8));
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
