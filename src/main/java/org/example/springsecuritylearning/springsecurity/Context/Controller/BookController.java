package org.example.springsecuritylearning.springsecurity.Context.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.springsecuritylearning.springsecurity.Context.service.BookService;

@RestController


public class BookController {
    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService=bookService;

    }

    @GetMapping("/book")
    public String book(){
        return bookService.provideName();

    }


}
