package org.example.lms.services;

import org.example.lms.dto.inbound.book.BookAddRequest;
import org.example.lms.dto.inbound.book.BookDeleteRequest;
import org.example.lms.dto.inbound.book.BookGetRequest;
import org.example.lms.dto.inbound.book.BookUpdateRequest;
import org.example.lms.dto.inbound.book.BooksGetRequest;
import org.example.lms.dto.outbound.book.BookAddResponse;
import org.example.lms.dto.outbound.book.BookDeleteResponse;
import org.example.lms.dto.outbound.book.BookGetResponse;
import org.example.lms.dto.outbound.book.BookUpdateResponse;
import org.example.lms.dto.outbound.book.BooksGetResponse;

public interface BookService {
    BookAddResponse addBook(BookAddRequest bookAddRequest) throws Exception;
    BookUpdateResponse updateBook(BookUpdateRequest bookUpdateRequest) throws Exception;
    BookDeleteResponse deleteBook(BookDeleteRequest bookDeleteRequest);
    BooksGetResponse getAllBooks(BooksGetRequest booksGetRequest) throws Exception;
    BookGetResponse getBookById(BookGetRequest bookGetRequest) throws Exception;
}
