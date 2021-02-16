package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleConnection {
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1522:xe";
    static final String USER = "C##SPL2";
    static final String PASS = "spl2";
    public Connection conn = null;
    Statement stmt = null;

    public OracleConnection() {

        try {
            Class.forName(JDBC_DRIVER);
            //    System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //      System.out.println("Creating statement...");
            stmt = conn.createStatement();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //    System.out.println("Thank you");
    }


}
