package com.ethan.libraryapi;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
public class BookController {

    private Library library;

    public BookController() {

        library = Library.loadFromFile("library.dat");

        // temporary sample data if library is empty
        if (library.getAllBooks().isEmpty()) {

            library.addBook(new Book("Dune", "Frank Herbert", 1));
            library.addBook(new Book("1984", "George Orwell", 2));

            library.saveToFile("library.dat");
        }
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return library.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBooksById(@PathVariable int id){
        return library.findBookByID(id);
    }    

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        library.addBook(book);
        library.saveToFile("library.dat");
        return book;
    }

    @PutMapping("/books/{id}")
    public String updateBook(@PathVariable int id, @RequestBody Book updatedBook){
        boolean success = library.updateBook(id, updatedBook);
        
        if(success){
            library.saveToFile("Library.dat");
            return "Book updated successfully.";
        }

        return "Book not found.";
    }

    @DeleteMapping("/books/{id}")
        public String delelteBook(@PathVariable int id){
            boolean success = library.removeBookByID(id);
            
            if(success){
                library.saveToFile("Library.dat");
                return "Book deleted successfully.";

            }

            return "Book not found.";
        }
    }
    


