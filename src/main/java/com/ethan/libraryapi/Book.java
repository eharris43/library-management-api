
package com.ethan.libraryapi;

import java.io.Serializable;


public class Book implements Serializable {
    
    private  String title; 
    private  String author ; 
    private  int id ;
    private boolean available = true ;

    public Book(){

    }
    
    public Book(String title , String author , int id ){
        
        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Title cannot be empty");
        }

        if(author == null || author.isBlank()){
            throw new IllegalArgumentException("Author cannot be empty");
        }

        if(id < 0){
            throw new IllegalArgumentException("ID cannot be 0 or negative.");
        }
        
        this.title = title;
        this.author = author;
        this.id = id;
    }

    public String getTitle(){
        return title ;
    }

    public String getAuthor()
    {
        return author;
    
    }

    public int getId()
    {
        return id;
    }
    
    public boolean isAvailable(){
        return available;
    }

    public void setAvailable( boolean available){
        this.available = available;
    }
    @Override

    public String toString()
    {
        return String.format(
            "Book ID: %-3d  |Title: %-30s | Author: %-15s\n", id, title , author );
    }

  
 
}
