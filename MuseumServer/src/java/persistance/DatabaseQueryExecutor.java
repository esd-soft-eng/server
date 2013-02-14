package persistance;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseQueryExecutor {

    Connection con;
    Statement state;

    public DatabaseQueryExecutor(String url, String user, String password) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex1) {
            System.err.println("Can't find the mysql driver: " + ex1);
            System.exit(1);
        }
        catch (SQLException ex) {
            System.err.println("Can't connect to DB: " + ex);
            System.exit(1);
        }
    }

    public synchronized ResultSet executeStatement(String sql) {

        ResultSet rs = null;
        try {
            state = con.createStatement();
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseQueryExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public synchronized boolean executeUpdate(String sql) {

        boolean success;

        try {
            this.state = this.con.createStatement();
            this.state.executeUpdate(sql);
            success = true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseQueryExecutor.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        }
        return success;
    }
}