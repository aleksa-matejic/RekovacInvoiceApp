package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection
{
    public static Connection getConnection() throws SQLException
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:rekovacInvoiceDatabase.sqlite");
            return conn;
        } catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
