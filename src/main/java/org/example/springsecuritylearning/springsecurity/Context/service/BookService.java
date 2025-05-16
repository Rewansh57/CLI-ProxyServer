package org.example.springsecuritylearning.springsecurity.Context.service;

import org.example.springsecuritylearning.springsecurity.Context.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    public String provideName(){
        Book book = new Book("random",101);
        return book.getName();


    }
}
