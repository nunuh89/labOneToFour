package com.company;

/**
 * Created by chiyang on 12/21/16.
 */
public class Book {
    public String code;
    public String title;
    public double price;

    public Book createBook(String bookCode, String booktitle, double bookPrice){
        Book aBook = new Book();
        aBook.code = bookCode;
        aBook.title = booktitle;
        aBook.price = bookPrice;
        return aBook;
    }
    // getter methods
    public String getCode(){
        return this.code;
    }

    public String getTitle(){
        return this.title;
    }

    public double getPrice(){
        return this.price;
    }
}
