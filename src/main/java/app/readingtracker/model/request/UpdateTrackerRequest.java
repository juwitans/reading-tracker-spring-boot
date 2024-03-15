package app.readingtracker.model.request;

import app.readingtracker.entity.Book;
import app.readingtracker.entity.Quote;
import app.readingtracker.entity.ReadingStatus;
import app.readingtracker.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTrackerRequest {
    private String id;
    private String bookId;
    private String userId;
    private Duration timeSpent;
    private Integer progress;
    private List<Quote> quotes;
    private String notes;
    private ReadingStatus status;
}
