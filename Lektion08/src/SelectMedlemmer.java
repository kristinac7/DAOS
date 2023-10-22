import java.sql.*;

public class SelectMedlemmer {

    public static void main(String[] args) {

        try {
            Connection minConnection;
            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://localHost\\SQLExpress;databaseName=KlubEks;user=sa;password=Lortekode1234;");

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

        } catch (SQLException e) {
            System.out.println("fejl:  " + e.getMessage());
            System.out.println("fejl:  " + e.getErrorCode());

            if (e.getErrorCode() == 2627) {
                System.out.println("Medlemsid er brugt, find et andet");
            }
            if (e.getErrorCode() == 8152) {
                System.out.println("Dit navn er for langt");
            }

        } catch (Exception e) {
            System.out.println("fejl:  " + e.getMessage());
        }
    }
}
