package app.readingtracker.controller;

import app.readingtracker.model.request.BookReviewRequest;
import app.readingtracker.model.response.BookReviewResponse;
import app.readingtracker.model.response.WebResponse;
import app.readingtracker.service.BookReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/reviews")
public class BookReviewController {
    private final BookReviewService bookReviewService;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody BookReviewRequest request) {
        BookReviewResponse bookReview = bookReviewService.createOrUpdate(request);
        WebResponse<BookReviewResponse> response = WebResponse.<BookReviewResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfully posting review")
                .data(bookReview)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/:{userId}")
    public ResponseEntity<?> getAllReviews(@PathVariable String userId) {
        List<BookReviewResponse> bookReviews = bookReviewService.getAllByUserId(userId);
        WebResponse<List<BookReviewResponse>> response = WebResponse.<List<BookReviewResponse>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully get all reviews")
                .data(bookReviews)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getReview(@PathVariable String id) {
        BookReviewResponse bookReview = bookReviewService.getById(id);
        WebResponse<BookReviewResponse> response = WebResponse.<BookReviewResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully get review")
                .data(bookReview)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable String id) {
        bookReviewService.delete(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully delete review")
                .data("deleted")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<?> editReview(@RequestBody BookReviewRequest request) {
        BookReviewResponse bookReview = bookReviewService.createOrUpdate(request);
        WebResponse<BookReviewResponse> response = WebResponse.<BookReviewResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully edit review")
                .data(bookReview)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
