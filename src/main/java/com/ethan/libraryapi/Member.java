package com.ethan.libraryapi;

import java.util.List;

import java.io.*;

import java.util.ArrayList;


public class Member implements Serializable{
   
    private String username; 
    private int memberID ;
    private List<Book>  borrowedBooks;

    public Member(){}

    public Member(String username, int memberID )
    {
        this.username = username;
        this.memberID = memberID;
        this.borrowedBooks = new ArrayList<>(); 

    }

    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public boolean borrowBook(Book book){

        if(borrowedBooks.size() < 6 && !borrowedBooks.contains(book)){
        
            borrowedBooks.add(book);
            return true;
        }
       return false ; 
    }

    public void returnBorrowedBook(Book book){
        
        borrowedBooks.remove(book);
    }

    public int getMemberID()
    {
        return memberID;
    }

    public String getUserName()
    {
        return username;
    }

    @Override 
    public  String toString()
    {
        StringBuilder titles = new StringBuilder();
        for(Book book : borrowedBooks){
            titles.append(book.getTitle()).append(", ");
        }
        if(titles.length() > 0){
            titles.setLength(titles.length() -2 );
        }
        return String.format("Member Name: %-15s\t ID: %d\t Number of Borrowed Books: %d Books Borrowed: %s", 
                username , memberID ,borrowedBooks.size(), titles.toString());
    }


}
