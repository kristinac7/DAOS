package Karakterregistreringssystem_Opg8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Karakterregistreringssytem_c_Select {


    public static void main(String[] args) {

        try {
            Connection minConnection;
            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://localHost\\SQLExpress;databaseName=ObligatoriskProjekt;user=sa;password=Lortekode1234;");

            Statement stmt = minConnection.createStatement();

            ResultSet res = stmt.executeQuery("SELECT eksamens_navn, termin FROM eksamen, afholdelse");
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
            System.out.println("Fejl:  " + e.getMessage());
        }
    }
}
