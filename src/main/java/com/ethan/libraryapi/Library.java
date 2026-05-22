package com.ethan.libraryapi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Library implements Serializable {
    
    private List<Book> books;
    private List<Member> members;
    
    

    public Library(){

        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        
    }

    public static Library loadFromFile(String filename) {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
        return (Library) in.readObject();
    } catch (Exception e) {
        System.out.println("No saved data found. Starting fresh.");
        return new Library();
    }
    }   


   
    public void addBook(Book book ){
        
        books.add(book);

    }
    public boolean removeBookByID(int id){
        Book foundBook = findBookByID(id);

        if(foundBook == null){
            return false;
        }
        returnBorrowedBookFromMember(foundBook);
        books.remove(foundBook);
        return true;
        
    }
    public void removeBook(Book book){
        books.remove(book);
    }

    public void registerMember(Member member){
    
        members.add(member);
    }

    public Book findBookByID(int id){
        
        for(Book book : books){
            
            if(book.getId() == id){

                return book;

            }
        }
        return null;
    
    }

    public Member findMemberByID(int id){
        
        for(Member member : members){
            if(member.getMemberID() == id){
                return member;
            }
        }
        
        return null;
    }

    public boolean removeMemberByID(int id){
        Member member = findMemberByID(id);

        if(member == null){
            return false;       
        }
        
        members.remove(member);
        return true;
    }

    public Member returnBorrowedBookFromMember(Book book1)
    {
        if(book1.isAvailable()){
            return null;
        }else {
            for(Member member : members){   
                for(Book book : member.getBorrowedBooks()){
                    if(book == book1)
                    {
                        member.returnBorrowedBook(book1);
                        return member;
                    }
                }
            }
        } return null;
    }

    public boolean borrowBook(int memberId , int bookId){
       
        Book foundBook = findBookByID(bookId);
        Member foundMember = findMemberByID(memberId);
        
        
         if(foundMember == null || foundBook == null){
            return false;   
        }
        
        if(!foundBook.isAvailable()){
            return false;
        }
        boolean success = foundMember.borrowBook(foundBook);
        
        if(success){
            foundBook.setAvailable(false);
        }
        return success;
    }

    public void returnBook(int memberID , int bookID){
        Book foundBook = findBookByID(bookID);
       
        Member foundMember = findMemberByID(memberID);
       
        if(foundBook == null || foundMember == null ) {return;} 
        
        if(foundMember.getBorrowedBooks().contains(foundBook)){
            foundMember.returnBorrowedBook(foundBook);
            foundBook.setAvailable(true);
        }
     }

     public List<Book> getAllBooks(){
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(Comparator.comparingInt(Book::getId));
        return sortedBooks;
    }

     public List<Book> getAllAvailableBooks(){
        List<Book> availableBooks = new ArrayList<>();
        for(Book book: books){
            if(book.isAvailable()){
                availableBooks.add(book);
            }
        }
        availableBooks.sort(Comparator.comparingInt(Book::getId));
        return availableBooks;
    }


     public List<Member> getAllMembers(){
       
       return new ArrayList<>(members);
     }

     public void saveToFile(String filename){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
            out.writeObject(this);
            System.out.println("Library saved Successfully");

      
        } catch (IOException e){
            System.out.println("Error saving Library.");
            e.printStackTrace();
        }


     } 

    public boolean updateBook(int id, Book updatedBook){
        Book existingBook = findBookByID(id);

        if(existingBook == null ) {
            return false;
        }

        boolean wasAvailable = existingBook.isAvailable();

        books.remove(existingBook);

        updatedBook.setAvailable(wasAvailable);
        books.add(updatedBook);

        return true;
    }




}
