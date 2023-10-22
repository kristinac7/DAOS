package Karakterregistreringssystem_Opg8;

import javax.print.attribute.standard.PresentationDirection;
import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Karakterregistreringssystem_a {

    /*
    a. Lav et Java-program, der anvendes JDBC. Programmet skal oprette et eksamensforsøg for en
       given studerende for en given afvikling af en eksamen. Via programmet skal du kunne indtaste data for et eksamensforsøg for én studerende.
       Brugeren skal selv indtaste identifikation på eksamensafviklingen og på den studerende udover karakteren/administrative bedømmelse.
       Der forventes rimelige SQL-fejlreaktioner.
    */
    public static void main(String[] args) {
        opretEksamensforsoeg();
    }

    static Connection connection = null;

    public static Connection getConnection() throws SQLException {

        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=DAOS_projekt_skoleAdmin;user=sa;password=reallyStrongPwd123;");
        }

        return connection;
    }

    // Oprettelse af ny afholdelse, som kan bruges til det nye eksamensforsøg.
    // Kan bruges, eller allerede eksisterende afholdelse kan anvendes.
    public static void opretAfholdelse() {
        try {
            Connection minConnection = getConnection();

            System.out.println("Opret afholdelse");
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Hvilket ID skal afholdelsen have?");
            String afholdelseID = inLine.readLine();


            System.out.println("Start dato (yyyy-MM-dd): ");
            String startDateStr = inLine.readLine();
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());

            System.out.println("Slut dato (yyyy-MM-dd): ");
            String slutDateStr = inLine.readLine();
            Date slutDate = new SimpleDateFormat("yyyy-MM-dd").parse(slutDateStr);
            java.sql.Date sqlSlutDate = new java.sql.Date(slutDate.getTime());

            System.out.println("Termin: indtast enten 'sommer' eller 'efterår': ");
            String termin = inLine.readLine();

            System.out.println("Indtast eksamensnummer: ");
            String eksamenNr = inLine.readLine();

            String sqlAfholdelse = "INSERT INTO afholdelse (afholdelses_id, start_dato, slut_dato, termin, eksamens_nr) VALUES (?,?,?,?,?)";
            PreparedStatement prestmt = minConnection.prepareStatement(sqlAfholdelse);
            prestmt.clearParameters();
            prestmt.setString(1, afholdelseID);
            prestmt.setDate(2, sqlStartDate);
            prestmt.setDate(3, sqlSlutDate);
            prestmt.setString(4, termin);
            prestmt.setString(5, eksamenNr);

            prestmt.executeUpdate(); // This line is missing in your code
            System.out.println("Afholdelse oprettet"); // Optionally, you can print a success message

        } catch (SQLException | IOException e) {
            System.out.println("SQL fejl:  " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("ParseException fejl: " + e.getMessage());
        }
    }

    public static void opretEksamensforsoeg() {

        try {
            System.out.println("Opret eksamensforsøg");
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Indtast et ikke eksisterende ID til eksamensforsøget: ");
            String eksamenForsoegID = inLine.readLine();
            System.out.println("Indtast ID fra afholdelse: ");
            String afholdelsesID = inLine.readLine();
            System.out.println("Indtast id på den studerende der skal til eksamen: ");
            String studerendeID = inLine.readLine();

            String kode = null;
            String karakter = null;

            Connection minConnection = getConnection();

            String sqlEksamenForsoeg = "INSERT INTO eksamensforsøg (eksamensforsøg_id, kode, karakter, afholdelses_id, studerende_id) VALUES(?,?,?,?,?)";
            PreparedStatement prestmt = minConnection.prepareStatement(sqlEksamenForsoeg);
            prestmt.clearParameters();

            prestmt.setString(1, eksamenForsoegID);
            prestmt.setString(2, kode);
            prestmt.setString(3, karakter);
            prestmt.setString(4, afholdelsesID);
            prestmt.setString(5, studerendeID);

            prestmt.executeUpdate();
            System.out.println("Eksamensforsøg oprettet");

            if (prestmt != null)
                prestmt.close();
            if (minConnection != null)
                minConnection.close();

        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
            System.out.println("Fejl: " + e.getErrorCode());

            if (e.getErrorCode() == 2627) {
                System.out.println("Primær nøgle er allerede brugt. Indsæt ikke eksisterende nøgle");
            }
            if (e.getErrorCode() == 547) {
                System.out.println("Foreign key er allerede brugt");
            }
        } catch (Exception e) {
            System.out.println("Fejl:  " + e.getMessage());
        }
    }
}