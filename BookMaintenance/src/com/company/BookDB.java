package com.company;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by chiyang on 12/21/16.
 */
public class BookDB {
    private Connection client;
    private Statement statement;
    private ResultSet set;
    private String query;


    // store all the books in the database
    public ArrayList<Book> books;
    // current Book show on the window
    public Book currentBook;
    // index of the currentBook in books
    public int index;

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
            statement = client.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDB() {
        set = Query("SELECT * FROM BOOK");
        books = ParseSet(set);
    }

    public void deleteRecord(){
        set = Query("DELETE FROM Book WHERE EXISTS (SELECT * FROM Book WHERE Book.code =" + currentBook.code +")");
        // sync
        // TODO: change the currentBook to be next one, if successfully delete current book
        loadDB();
    }

    public void addRecord(Book aBook){
        set = Query("INSERT INTO Book VALUES (" +aBook.code + ", " + aBook.title + ", " + aBook.price + ")");
        loadDB();
    }

    private ResultSet Query(String str) {
        try {
            query = str;
            set = statement.executeQuery(query);
            return set;
        } catch (Exception e) {
            System.out.println(query);
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Book> ParseSet(ResultSet aSet) {
        ArrayList<Book> BookArrayList = new ArrayList<Book>();
        try {
            while (aSet.next()) {
                String code = aSet.getString("code");
                String title = aSet.getString("title");
                double price = aSet.getDouble("price");
                Book aBook = Book.createBook(code, title, price);
                BookArrayList.add(aBook);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BookArrayList;
    }
}

