package Opgaver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*Opgave 7:
        Udbyg opgave 1 med afprøvning af:
        • previous()
        • absolute(int nummer)
        • relative(int nummer)
        • first()
        • last()*/

public class Opg7_ModifyOpg1 {

    public static void main(String[] args) {
        try {
            Connection minConnection;
            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://localhost;databaseName=master;user=sa;password=reallyStrongPwd123;");

            Statement stmt = minConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet res = stmt.executeQuery("SELECT medarbejderNr, navn, stillingsbetegnelse, mobil FROM Medarbejder");

            while (res.next()) {
                System.out.println(res.getString(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t" + res.getString(4));
            }

            System.out.println("previous()" + "\t" + res.previous());
            System.out.println("absolute(2)" + "\t" + res.absolute(2));
            System.out.println("relative(2)" + "\t" + res.relative(2));
            System.out.println("first()" + "\t" + res.first());
            System.out.println("last()" + "\t" + res.last());


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

