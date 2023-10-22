package Opgaver;

import java.sql.*;

public class Opg4_StoredProcedureMedarbejder {

    /*Opgave 4:
    Skriv en stored procedure i Management Studio, der giver alle medarbejders navn og
    telefonnummer, når den bliver udført.
    Skriv et Java program, der forbinder til databasen Tidsregistrering. Programmet skal kalde
    overstående stored procedure og udskrive resultatet i konsollen.*/

    public static void main(String[] args) {

        try {
            Connection minConnection;
            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://localhost;databaseName=master;user=sa;password=reallyStrongPwd123;");

            Statement stmt = minConnection.createStatement();

            String sql = "EXECUTE medarbejder_info";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println(res.getString(1) + "\t" + res.getString(2));
            }

            if (stmt != null)
                stmt.close();
            if (minConnection != null)
                minConnection.close();
        } catch (Exception e) {
            System.out.println("fejl:  " + e.getMessage());
        }
    }
}
