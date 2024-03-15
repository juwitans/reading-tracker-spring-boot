package app.readingtracker.controller;

import app.readingtracker.entity.BookList;
import app.readingtracker.model.request.BookListRequest;
import app.readingtracker.model.response.BookListResponse;
import app.readingtracker.model.response.BookReviewResponse;
import app.readingtracker.model.response.WebResponse;
import app.readingtracker.service.BookListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/booklist")
public class BookListController {
    private final BookListService bookListService;

    @PostMapping
    public ResponseEntity<?> createList(@RequestBody BookListRequest request) {
        BookList bookList = bookListService.create(request);
        WebResponse<BookList> response = WebResponse.<BookList>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfully create book list")
                .data(bookList)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllLists() {
        List<BookListResponse> bookLists = bookListService.getAll();
        WebResponse<List<BookListResponse>> response = WebResponse.<List<BookListResponse>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully get all lists")
                .data(bookLists)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getList(@PathVariable String id) {
        BookListResponse bookList = bookListService.get(id);
        WebResponse<BookListResponse> response = WebResponse.<BookListResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully get book list")
                .data(bookList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> editList(@PathVariable String id, @RequestBody BookListRequest request) {
        BookListResponse updated = bookListService.update(id, request);
        WebResponse<BookListResponse> response = WebResponse.<BookListResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully update book list")
                .data(updated)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
