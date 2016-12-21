package com.company;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by chiyang on 12/21/16.
 */
public class BookDB {
    Connection client;
    Statement statement;
    ResultSet set;

    public BookDB() {
        connect();
    }

    public void connect() {
        try {
            String driverName = "com.mysql.jdbc.Driver";
            String db = "jdbc:mysql://localhost:3306/BookDB";
            // default user with configured password literally
            String user = "root";
            String pwd = "password";

            Class.forName(driverName);

            // get connection to db; init statement
            client = DriverManager.getConnection(db, user, pwd);
            statement = client.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            set = statement.executeQuery("SELECT * FROM BOOK");

//            while (set.next()){
//                String code = set.getString("code");
//                String title = set.getString("title");
//                double price = set.getDouble("price");
//                System.out.println(code + " "+title+" "+price);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String args[]) {
        //new BookDB();
        new BookFrame();
    }
}

