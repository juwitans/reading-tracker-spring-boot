package app.readingtracker.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookReviewResponse {
    private String name;
    private String bookTitle;
    private String bookAuthor;
    private double rating;
    private String review;
}
