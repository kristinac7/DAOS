import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class SelectMedlemmer {

    public static void main(String[] args) {

        try {
            Connection minConnection;
            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://localhost;databaseName=KlubEksempel;user=sa;password=reallyStrongPwd123;");

            Statement stmt = minConnection.createStatement();

            ResultSet res = stmt.executeQuery("select * from Medlem");
            while (res.next()) {
                System.out.println(res.getString(1) + "\t" + res.getString(2) + " \t " + res.getString(3));
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
