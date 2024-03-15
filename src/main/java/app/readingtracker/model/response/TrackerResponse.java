package app.readingtracker.model.response;

import app.readingtracker.entity.Quote;
import app.readingtracker.entity.ReadingStatus;
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
public class TrackerResponse {
    private String bookTitle;
    private String bookAuthor;
    private String bookDescription;
    private Duration timeSpent;
    private Integer progress;
    private String notes;
    private List<String> quotes;
    private ReadingStatus status;
}
