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

    public void deleteRecord() {
        set = Query("DELETE FROM Book WHERE EXISTS (SELECT * FROM Book WHERE Book.code =" + currentBook.code + ")");
        // sync
        // TODO: change the currentBook to be next one, if successfully delete current book
        loadDB();
        int count = books.size();
        // when the currentBook is the previous last one
        if (index > count - 1) {
            index = count - 1;
        }
        currentBook = books.get(index);
    }

    public void addRecord(Book aBook) {
        set = Query("INSERT INTO Book VALUES (" + aBook.code + ", " + aBook.title + ", " + aBook.price + ")");
        loadDB();
        int count = books.size();
        // add to the last of the table successfully
        if (index < count - 1) {
            index = count - 1;
        }
        currentBook = books.get(index);
    }

    public void moveFirst() {
        if (books.size() > 0) {
            index = 0;
            currentBook = books.get(index);
        }
    }

    public void moveLast() {
        int bookCount = books.size();
        if (bookCount > 0) {
            index = bookCount - 1;
            currentBook = books.get(index);
        }
    }

    public void movePrevious() {
        if (index > 0) {
            index--;
            currentBook = books.get(index);
        }
    }

    public void moveNext() {

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

