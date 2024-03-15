package app.readingtracker.controller;

import app.readingtracker.entity.Book;
import app.readingtracker.model.response.BookShortDetailResponse;
import app.readingtracker.model.response.WebResponse;
import app.readingtracker.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/api/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        Book newBook = bookService.create(book);
        WebResponse<Book> response = WebResponse.<Book>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfully add book record")
                .data(newBook)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookService.getAll();
        WebResponse<List<Book>> response = WebResponse.<List<Book>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully get all books")
                .data(books)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> searchBooks(@RequestParam String book) {
        List<BookShortDetailResponse> books = bookService.searchAuthorOrTitle(book);
        WebResponse<List<BookShortDetailResponse>> response = WebResponse.<List<BookShortDetailResponse>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully get all books with given title/author")
                .data(books)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id) {
        Book book = bookService.get(id);
        WebResponse<Book> response = WebResponse.<Book>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully get this book")
                .data(book)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        bookService.delete(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully deleted")
                .data("OK")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<?> editBook(@RequestBody Book book) {
        Book updated = bookService.update(book);
        WebResponse<Book> response = WebResponse.<Book>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully update book")
                .data(updated)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
