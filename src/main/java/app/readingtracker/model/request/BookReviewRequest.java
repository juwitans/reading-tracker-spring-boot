package app.readingtracker.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookReviewRequest {
    private String id;
    private String userId;
    private String bookId;
    private double rating;
    private String review;
}
