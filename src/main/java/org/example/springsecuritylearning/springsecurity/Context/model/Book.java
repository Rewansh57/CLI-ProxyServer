package org.example.springsecuritylearning.springsecurity.Context.model;

public class Book {
    String name;
    int bookId;
    public Book(String name,int bookId){
        this.name=name;
        this.bookId=bookId;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
