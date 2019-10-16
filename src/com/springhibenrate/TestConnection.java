package com.springhibenrate;


import java.sql.Connection;
import java.sql.DriverManager;


public class TestConnection {

    public static void main (String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
        String user = "root";
        String password = "pass";

        try{
            System.out.println("Connecting to databese: " + jdbcUrl);

            Connection connection =
                    DriverManager.getConnection(jdbcUrl, user, password);

            System.out.println("Connection successful !!!");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
