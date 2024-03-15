package app.readingtracker.model.request;

import app.readingtracker.constant.EReadingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchTrackerRequest {
    private String userId;
    private EReadingStatus readingStatus;
}
