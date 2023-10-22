import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*Opgave 7:
        Lav en klasse Medarbejder. Klassen skal have de samme attributter, som Medarbejder
        tabellen har. Lav en java applikation, der henter alle medarbejder fra databasen Tidsregistrering.
        Alle medarbejderne skal indsættes i en Arrayliste. Gennemløb listen og udskriv alle medarbejderne.*/

public class Opg7_Medarbejder {
    private int medarbejderNr;
    private String navn;
    private String stillingsbetegnelse;
    private String mobil;

    public Opg7_Medarbejder(int medarbejderNr, String navn, String stillingsbetegnelse, String mobil) {
        this.medarbejderNr = medarbejderNr;
        this.navn = navn;
        this.stillingsbetegnelse = stillingsbetegnelse;
        this.mobil = mobil;
    }

    public int getMedarbejderNr() {
        return medarbejderNr;
    }

    public void setMedarbejderNr(int medarbejderNr) {
        this.medarbejderNr = medarbejderNr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getStillingsbetegnelse() {
        return stillingsbetegnelse;
    }

    public void setStillingsbetegnelse(String stillingsbetegnelse) {
        this.stillingsbetegnelse = stillingsbetegnelse;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    private static final Logger logger = Logger.getLogger(Opg7_Medarbejder.class.getName());

    public static void main(String[] args) {

        ArrayList<Opg7_Medarbejder> medarbejdere = new ArrayList<>();

        try {
            Connection minConnection;
            minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=master;user=sa;password=reallyStrongPwd123;");

            Statement stmt = minConnection.createStatement();

            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Medarbejder");

            while (resultSet.next()) {
                int medarbejderNr = resultSet.getInt("medarbejderNr");
                String navn = resultSet.getString("navn");
                String stillingsbetegnelse = resultSet.getString("stillingsbetegnelse");
                String mobil = resultSet.getString("mobil");

                Opg7_Medarbejder medarbejder = new Opg7_Medarbejder(medarbejderNr, navn, stillingsbetegnelse, mobil);
                medarbejdere.add(medarbejder);
            }

            resultSet.close();
            stmt.close();
            minConnection.close();

            for (Opg7_Medarbejder medarbejder : medarbejdere) {
                System.out.println("MedarbejderNr: " + medarbejder.getMedarbejderNr());
                System.out.println("Navn: " + medarbejder.getNavn());
                System.out.println("Stillingsbetegnelse: " + medarbejder.getStillingsbetegnelse());
                System.out.println("Mobil: " + medarbejder.getMobil());
                System.out.println();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred:", e);
        }
    }
}
