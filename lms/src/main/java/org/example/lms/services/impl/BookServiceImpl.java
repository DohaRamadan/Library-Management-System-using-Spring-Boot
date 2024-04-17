package org.example.lms.services.impl;

import java.util.List;
import java.util.Optional;

import org.example.lms.dto.inbound.book.BookAddRequest;
import org.example.lms.dto.inbound.book.BookDeleteRequest;
import org.example.lms.dto.inbound.book.BookGetRequest;
import org.example.lms.dto.inbound.book.BookUpdateRequest;
import org.example.lms.dto.inbound.book.BooksGetRequest;
import org.example.lms.dto.outbound.book.BookAddResponse;
import org.example.lms.dto.outbound.book.BookDeleteResponse;
import org.example.lms.dto.outbound.book.BookGetResponse;
import org.example.lms.dto.outbound.book.BookResponse;
import org.example.lms.dto.outbound.book.BookUpdateResponse;
import org.example.lms.dto.outbound.book.BooksGetResponse;
import org.example.lms.exceptions.book.BookAlreadyExistsException;
import org.example.lms.exceptions.book.BookNotFoundException;
import org.example.lms.exceptions.book.NoBooksExistException;
import org.example.lms.models.Book;
import org.example.lms.repositories.BookRepository;
import org.example.lms.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookAddResponse addBook(BookAddRequest bookAddRequest) {
        Optional<Book> bookEntity = bookRepository.findByIsbn(bookAddRequest.getIsbn());
        if(bookEntity.isPresent()){
            throw new BookAlreadyExistsException();
        }
        Book book = createBook(bookAddRequest);
        bookRepository.save(book);
        return new BookAddResponse();
    }

    @Override
    public BookUpdateResponse updateBook(BookUpdateRequest bookUpdateRequest) {
        Optional<Book> bookEntity = bookRepository.findBookById(bookUpdateRequest.getId());
        if(bookEntity.isEmpty()){
            throw new BookNotFoundException();
        }
        Book book = bookEntity.get();
        book.setPublicationYear(bookUpdateRequest.getPublicationYear());
        book.setAuthor(bookUpdateRequest.getAuthor());
        book.setStatus(bookUpdateRequest.getStatus());
        book.setTitle(bookUpdateRequest.getTitle());
        book.setIsbn(bookUpdateRequest.getIsbn());
        bookRepository.save(book);
        return new BookUpdateResponse(new BookResponse(book));
    }

    @Override
    public BookDeleteResponse deleteBook(BookDeleteRequest bookDeleteRequest) {
        Long bookId = bookDeleteRequest.getBookId();
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
        } else {
            throw new BookNotFoundException();
        }
        return new BookDeleteResponse();
    }

    @Override
    public BooksGetResponse getAllBooks(BooksGetRequest booksGetRequest) throws Exception {
        List<Book> books = bookRepository.findAll();
        if(books.isEmpty()){
            throw new NoBooksExistException();
        }
        return new BooksGetResponse(books);
    }

    @Override
    public BookGetResponse getBookById(BookGetRequest bookGetRequest) throws Exception {
        Optional<Book> bookEntity = bookRepository.findBookById(bookGetRequest.getBookId());
        if(bookEntity.isEmpty()){
            throw new BookNotFoundException();
        }
        Book book = bookEntity.get();
        return new BookGetResponse(book);
    }

    private Book createBook(BookAddRequest request){
        Book book = new Book();
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setStatus(request.getStatus());
        book.setTitle(request.getTitle());
        book.setPublicationYear(request.getPublicationYear());
        return book;
    }
}
