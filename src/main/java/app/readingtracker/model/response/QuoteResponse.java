package app.readingtracker.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuoteResponse {
    private String bookTitle;
    private String bookAuthor;
    private String quote;
}
