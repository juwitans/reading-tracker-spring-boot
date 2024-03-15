package app.readingtracker.service.impl;

import app.readingtracker.entity.BookReview;
import app.readingtracker.model.request.BookReviewRequest;
import app.readingtracker.model.response.BookReviewResponse;
import app.readingtracker.repository.BookReviewRepository;
import app.readingtracker.service.BookReviewService;
import app.readingtracker.service.BookService;
import app.readingtracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookReviewServiceImpl implements BookReviewService {
    private final BookReviewRepository bookReviewRepository;
    private final BookService bookService;
    private final UserService userService;

    @Override
    public BookReviewResponse createOrUpdate(BookReviewRequest request) {
            BookReview bookReview = BookReview.builder()
                    .id(request.getId().isEmpty() ? null : request.getId())
                    .user(userService.get(request.getUserId()))
                    .book(bookService.get(request.getBookId()))
                    .rating(request.getRating())
                    .review(request.getReview())
                    .build();
            bookReviewRepository.saveAndFlush(bookReview);

        return toBookReviewResponse(bookReview);
    }

    @Override
    public List<BookReviewResponse> getAllByUserId(String id) {
        List<BookReview> bookReviews = bookReviewRepository.findAllByUserId(id);
        return bookReviews.stream().map(review -> toBookReviewResponse(review))
                .collect(Collectors.toList());
    }

    @Override
    public BookReviewResponse getById(String id) {
        Optional<BookReview> optionalBookReview = bookReviewRepository.findById(id);
        if (optionalBookReview.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found");
        return toBookReviewResponse(optionalBookReview.get());
    }

    @Override
    public void delete(String id) {
        bookReviewRepository.deleteById(id);
    }

    private static BookReviewResponse toBookReviewResponse(BookReview bookReview) {
        return BookReviewResponse.builder()
                .name(bookReview.getUser().getName())
                .bookAuthor(bookReview.getBook().getAuthor())
                .bookTitle(bookReview.getBook().getTitle())
                .rating(bookReview.getRating())
                .review(bookReview.getReview())
                .build();
    }
}
