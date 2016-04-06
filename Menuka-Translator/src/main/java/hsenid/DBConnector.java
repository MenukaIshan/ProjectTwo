package hsenid;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnector {

    /**
     * Task of this class is to create database connection using servlet context listener.
     *
     */
    public static Connection conn; // This static so we can change value in any method


    public DBConnector(String url, String username, String password) throws IOException, SQLException {
        /**
         * @param url
         * This is the database url of the connection
         * @param username
         * Username of the database
         * @param password
         * password of particular database user given above
         */

        try {
            PropertyHandle data = new PropertyHandle();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(data.getUrl(), data.getDbuser(), data.getPassword()); // creating the db connection

        } catch (ClassNotFoundException e) { //Handling the exceptions
            throw new ClassCastException();
        } catch (IOException e) {
            throw new IOException();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public Connection getConn() {
        /**
         * returning the created connection
         */
        return conn;
    }


/*
    public static void main(String[] args) throws IOException, SQLException {
        DBConnector test = new DBConnector("jsjk", "fhsdl", "fggfgrw");
        if (test.getConn()){
            System.out.println("worked");
        }
    }*/
}
