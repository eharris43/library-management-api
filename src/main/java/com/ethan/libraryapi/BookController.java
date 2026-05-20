package com.ethan.libraryapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
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
    
    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {

    library.addBook(book);
    library.saveToFile("library.dat");

    return book;
}

}