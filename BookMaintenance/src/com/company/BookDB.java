package com.company;
import java.sql.*;
/**
 * Created by chiyang on 12/21/16.
 */
public class BookDB {
    private Connection client;
    private Statement statement;
    private ResultSet set;
    private String query;

    public BookDB() {
        connect();
    }

    public  void  connect(){
        try{
            String driverName = "com.mysql.jdbc.Driver";
            String db = "jdbc:mysql://localhost:3306/BookDB";
            // default user with configured password literally
            String user = "root";
            String pwd = "password";

            Class.forName(driverName);

            // get connection to db; init statement
            client = DriverManager.getConnection(db, user, pwd);
            statement = client.createStatement();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadDB(){
        set = Query("SELECT * FROM BOOK");

    }

    private ResultSet Query(String str){
        try{
            query = str;
            set = statement.executeQuery(query);
            return set;
        }catch (Exception e){
            System.out.println(query);
            e.printStackTrace();
        }
        return null;
    }
}

