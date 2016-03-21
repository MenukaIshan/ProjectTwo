package hsenid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnector {

    /**
     * Task of this class is to create database connection using servlet context listener.
     *
     */
    public static Connection conn; // This static so we can change value in any method


    public DBConnector(String url, String username, String password) {
        /**
         * @param url
         * This is the database url of the connection
         * @param username
         * Username of the database
         * @param password
         * password of particular database user given above
         */

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password); // creating the db connection

        } catch (ClassNotFoundException e) { //Handling the exceptions
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        /**
         * returning the created connection
         */
        return conn;
    }
}