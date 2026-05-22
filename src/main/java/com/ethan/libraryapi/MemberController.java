package com.ethan.libraryapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {
    
    private Library library;

    public MemberController(){
        library = Library.loadFromFile("library.dat");

    }

    @GetMapping("/members")
    public List<Member> getMembers(){
        return library.getAllMembers();
    }

    @GetMapping("/members/{id}")
    public Member getMemberByID(@PathVariable int id){
        return library.findMemberByID(id);
    }

    @PostMapping("/members")
    public Member addMember(@RequestBody Member member){
        
        library.registerMember(member);
        library.saveToFile("library.dat");
    
        return member;
    }
    @DeleteMapping("/members/{id}")
    public String deleteMember(@PathVariable int id){
        boolean success = library.removeMemberByID(id);

        if(success){
            library.saveToFile("library.dat");
            return "Member deleted successfully.";
        }
        
        return "Member not found";
    }

    @PostMapping("/members/{memberId}/borrow/{bookId}")
    public String borrowBook(@PathVariable int memberId, @PathVariable int bookId){

        Member member = library.findMemberByID(memberId);
        Book book = library.findBookByID(bookId);

        if(member == null){
            return "Member not found.";
        }
        
        if(book == null){
            return "Book not found.";
        }

        if(!book.isAvailable()){
            return "Book is not avaiable.";
        }
        
        boolean success = library.borrowBook(memberId, bookId);

        if(success){
            library.saveToFile("library.dat");
            return "Book borrowed successfully";
        }

        return "Borrowing failed";
    }
    @PostMapping("/members/{memberId}/return/{bookId}")
    public String returnBook(@PathVariable int memberId, @PathVariable int bookId){

        Member member = library.findMemberByID(memberId);
        Book book = library.findBookByID(bookId);

        if(member == null){
            return "Member not found";
        }

        if(book == null){
            return "Book not found";
        }

        if(!member.getBorrowedBooks().contains(book)){
            return "This member did not borrow this book.";
        }

        library.returnBook(memberId, bookId);
        library.saveToFile("library.dat");

        return "Book returned successfully";
    }
}
