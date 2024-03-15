package app.readingtracker.service;

import app.readingtracker.entity.BookReview;
import app.readingtracker.model.request.BookReviewRequest;
import app.readingtracker.model.response.BookReviewResponse;

import java.util.List;

public interface BookReviewService {
    BookReviewResponse createOrUpdate(BookReviewRequest request);
    List<BookReviewResponse> getAllByUserId(String id);
    BookReviewResponse getById(String id);
    void delete(String id);
}
