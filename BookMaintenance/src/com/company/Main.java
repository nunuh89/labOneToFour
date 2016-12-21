package com.company;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try{
            Class.forName("com.mysql.jdbc.Driver");

            // get connection to db
            Connection client = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookDB", "root", "password");
            Statement statement = client.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM BOOK");

            while (set.next()){
                System.out.println(set.getString("code") + set.getString("title") + set.getString("price"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
