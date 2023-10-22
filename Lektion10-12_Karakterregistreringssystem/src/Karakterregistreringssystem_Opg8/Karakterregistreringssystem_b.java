package Karakterregistreringssystem_Opg8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class Karakterregistreringssystem_b {

    /*
     b. Lav et java-program, der anvender JDBC. Programmet skal oprette en eksamensafvikling. Via programmet skal du indtaste data for en eksamensafvikling.
        Brugeren skal indtaste termin, startdato, slutdato og hvilken eksamen afviklingen hører til. Der forventes rimelige SQL- fejlreaktioner.
    */

    public static void main(String[] args) {

        try {
            System.out.println("Opret eksamensafvikling:");
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Indtast AfholdelsesID: ");
            String afholdelsesId = inLine.readLine();
            System.out.println("Indtast Start Dato: ");
            String startDato = inLine.readLine();
            System.out.println("Indtast Slut Dato: ");
            String slutDato = inLine.readLine();
            System.out.println("Indtast Termin: ");
            String termin = inLine.readLine();
            System.out.println("Indtast Eksamens nr: ");
            String eksamensNr = inLine.readLine();


            Connection minConnection;
            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://localhost;databaseName=Miniprojekt_Karakterregistreringssystem_Opg2_CreateTables;user=sa;password=reallyStrongPwd123;");


            String sql = "INSERT INTO afholdelse (afholdelses_id, start_dato, slut_dato, termin, eksamens_nr) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement prestmt = minConnection.prepareStatement(sql);

            prestmt.setString(1, afholdelsesId);
            prestmt.setDate(2, java.sql.Date.valueOf(startDato));
            prestmt.setDate(3, java.sql.Date.valueOf(slutDato));
            prestmt.setString(4, termin);
            prestmt.setString(5, eksamensNr);

            int rowsAffected = prestmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Eksamensafvikling oprettet");
            } else {
                System.out.println("Fejl under oprettelse");
            }

            if (prestmt != null)
                prestmt.close();
            if (minConnection != null)
                minConnection.close();

        } catch (SQLException e) {
            System.out.println("Fejl:  " + e.getMessage());
            System.out.println("Fejl:  " + e.getErrorCode());

            if (e.getErrorCode() == 2627) {
                System.out.println("AfholdelsesId er brugt, find et andet");
            }
            if (e.getErrorCode() == 547) {
                System.out.println("EksamensId findes ikke, brug et eksisterende");
            }
        } catch (Exception e) {
            System.out.println("Fejl:  " + e.getMessage());
        }
    }
}

