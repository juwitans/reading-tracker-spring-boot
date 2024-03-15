package app.readingtracker.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookListRequest {
    private String name;
    private String description;
    private List<String> bookId;
    private String userId;
}
