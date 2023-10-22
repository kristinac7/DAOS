package Karakterregistreringssystem_Opg8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;


public class Karakterregistreringssystem_c {

    /*
    c. Lav et java-program, der anvender JDBC.
          Via programmet skal du indtaste navnet på en given eksamen og en given termin.
          Programmet skal som resultat vise en liste af de studerende, der har deltaget i denne afvikling af denne eksamen i denne termin.
          Udover den studerendes navn og id, skal karakteren også være med i resultatet.
    */

    public static void main(String[] args) {
        try {
            Connection minConnection;
            minConnection = DriverManager.getConnection(
                    "jdbc:sqlserver://localHost\\SQLExpress;databaseName=ObligatoriskProjekt;user=sa;password=Lortekode1234;");

            System.out.println("Find studerendes navn, ID og karakter udfra given eksamen og given termin");
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Indtast eksamens navn: ");
            String eksamens_navn = inLine.readLine();
            System.out.println("Indtast termin: ");
            String termin = inLine.readLine();


            String sql = "SELECT S.studerende_navn, S.studerende_id, EF.karakter FROM studerende S INNER JOIN eksamensforsøg EF ON S.studerende_id = EF.studerende_id INNER JOIN afholdelse A ON EF.afholdelses_id = A.afholdelses_id INNER JOIN eksamen E ON A.eksamens_nr = E.eksamens_nr WHERE E.eksamens_navn = '" + eksamens_navn + "' AND A.termin = '" + termin + "'";

            // preparedStatement
            Statement prestmt = minConnection.createStatement();
//            prestmt.clearParameters();
//
//            prestmt.setString(1, eksamens_navn);
//            prestmt.setString(2, termin);

            ResultSet resultSet = prestmt.executeQuery(sql);

            if (resultSet.next()) {
                String studerende_navn = resultSet.getString(1);
                String studerende_id = resultSet.getString(2);
                String karakter = resultSet.getString(3);
                System.out.println("Studerende fundet: navn " + studerende_navn + ", ID " + studerende_id + "og karakter " + karakter);
                while (resultSet.next()) {
                    studerende_navn = resultSet.getString(1);
                    studerende_id = resultSet.getString(2);
                    karakter = resultSet.getString(3);
                    System.out.println("Studerende fundet: navn " + studerende_navn + ", ID " + studerende_id + "og karakter " + karakter);
                }
            }

            if (prestmt != null)
                prestmt.close();
            if (minConnection != null)
                minConnection.close();


        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
            System.out.println("Fejl: " + e.getErrorCode());
        } catch (Exception e) {
            System.out.println("Fejl:  " + e.getMessage());
        }
    }
}